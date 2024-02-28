package com.quadecom.QuadrantEcom.Users;

import com.quadecom.QuadrantEcom.Exception.ApiException;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register-user")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UpdatedUserEntity user) {
        try {
            userService.createUser(user);
            ApiResponse response = new ApiResponse();
            response.setSuccess(true);
            response.setMessage("User created successfully");
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            ApiResponse response = ex.getResponse();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @GetMapping("/get-all-user")
    public List<UpdatedUserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get-user/{id}")
    public UpdatedUserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
