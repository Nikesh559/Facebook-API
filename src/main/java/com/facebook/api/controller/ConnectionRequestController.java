package com.facebook.api.controller;

import com.facebook.api.constant.RequestStatus;
import com.facebook.api.dto.RequestDTO;
import com.facebook.api.exceptionhandling.ErrorMessage;
import com.facebook.api.model.ConnectionRequest;
import com.facebook.api.model.Message;
import com.facebook.api.service.ConnectionRequestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@ApiOperation("ConnectionRequestController")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConnectionRequestController {

    @Autowired
    ConnectionRequestService requestService;

    @GetMapping(value = "/profile/{profileId}/requests")
    public List<ConnectionRequest> getRequests(@PathVariable("profileId")String profileId,
                                               @Valid @RequestParam(value = "status",required = false)RequestStatus requestStatus) {

        return requestService.getRequests(profileId, requestStatus);
    }


    @GetMapping("/profile/{profileId}/request/{requestId}")
    public ConnectionRequest getRequestById(@PathVariable("profileId")String profileId, @PathVariable("requestId")String requestId) {
        return requestService.getRequestById(requestId);
    }

    @PatchMapping(value = "/profile/{profileId}/request/{requestId}")
    public ResponseEntity changeStatus(@PathVariable("profileId") String profileId,
               @PathVariable("requestId") String requestId,
            @RequestBody RequestDTO requestDTO) {
        if(requestService.changeStatus(profileId, requestId, requestDTO)) {
            return new ResponseEntity(new Message(HttpStatus.OK, "Status Updated "), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(new ErrorMessage(HttpStatus.BAD_REQUEST, "Cannot perform the operation "), HttpStatus.BAD_REQUEST);
        }
    }
}



