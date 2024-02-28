package com.quadecom.QuadrantEcom.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<NewOrderEntity , Integer> {

    NewOrderEntity findBySuccessIndicator(String successIndicator);

    NewOrderEntity findByOrderId(String orderId);

    @Query("SELECT no.orderId, no.orderDate, no.sessionID, no.user.id, uu.email, uu.fName, uu.phone " +
            "FROM NewOrderEntity no " +
            "JOIN UpdatedUserEntity uu ON no.user.id = uu.id " +
            "WHERE no.successIndicator = :successIndicator")
    List<Object[]> findOrdersWithUserDetailsBySuccessIndicator(@Param("successIndicator") String successIndicator);


}
