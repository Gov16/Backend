package com.quadecom.QuadrantEcom.Users;

import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    ApiResponse createUser(UpdatedUserEntity user);

    List<UpdatedUserEntity> getAllUsers();

    UpdatedUserEntity getUserById(Long id);
}
