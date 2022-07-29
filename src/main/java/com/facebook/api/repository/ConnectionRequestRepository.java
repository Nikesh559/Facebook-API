package com.facebook.api.repository;

import com.facebook.api.constant.RequestStatus;
import com.facebook.api.model.ConnectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long> {

    @Query("SELECT r.status FROM Request r WHERE r.requestId = :requestId")
    RequestStatus findRequestStatus(@Param("requestId") Long requestId);

}
