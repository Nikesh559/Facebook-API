package com.facebook.api.service;

import com.facebook.api.constant.RequestStatus;
import com.facebook.api.controller.ConnectionRequestController;
import com.facebook.api.controller.ProfileController;
import com.facebook.api.dto.RequestDTO;
import com.facebook.api.model.ConnectionRequest;
import com.facebook.api.model.Member;
import com.facebook.api.repository.ConnectionRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionRequestService {

    @Autowired
    private ConnectionRequestRepository requestRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ModelMapper modelMapper;



    public List<ConnectionRequest> getRequests(String profileId, RequestStatus status) {
        Member member = memberService.getMember(profileId);

        return member.getInvitations().stream().filter(connectionRequest -> {
            return status == null || (connectionRequest.getStatus() == status);
        }).map(connectionRequest -> {
            Link requesterProfile = linkTo(methodOn(ProfileController.class).getProfile(connectionRequest.getRequesterId())).withRel("requesterProfile");
            Link self = linkTo(methodOn(ConnectionRequestController.class).getRequestById(profileId,connectionRequest.getRequestId()+"")).withSelfRel();
            connectionRequest.add(requesterProfile);
            connectionRequest.add(self);
            return connectionRequest;
        }).collect(Collectors.toList());
    }

    public ConnectionRequest getRequestById(String requestId) {
        return requestRepository.findById(Long.valueOf(requestId)).get();
    }

    public boolean changeStatus(String profileId, String requestId, RequestDTO requestDTO) {
        try {
            if (getRequestStatus(Long.valueOf(requestId)) == RequestStatus.PENDING &&
                    (requestDTO.getStatus() == RequestStatus.ACCEPTED || requestDTO.getStatus() == RequestStatus.REJECTED)) {
                ConnectionRequest connectionRequest = requestRepository.findById(Long.valueOf(requestId)).get();
                connectionRequest.setStatus(requestDTO.getStatus());
                requestRepository.save(connectionRequest);
                return true;
            }
        }
        catch(Exception ex) {
        }
        return false;
    }

    public RequestStatus getRequestStatus(Long requestId) {
        return requestRepository.findRequestStatus(requestId);
    }

}
