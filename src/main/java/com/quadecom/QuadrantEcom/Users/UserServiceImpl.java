package com.quadecom.QuadrantEcom.Users;

import com.quadecom.QuadrantEcom.Exception.ApiException;
import com.quadecom.QuadrantEcom.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse createUser(UpdatedUserEntity user) {
        ApiResponse response = new ApiResponse();

        // Check if the email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            response.setSuccess(false);
            response.setMessage("Email already exists");
            response.setStatus(HttpStatus.CONFLICT);
            throw new ApiException(response);
        }

        // Check if the phone already exists
        if (userRepository.existsByPhone(user.getPhone())) {
            response.setSuccess(false);
            response.setMessage("Phone number already exists");
            response.setStatus(HttpStatus.CONFLICT);
            throw new ApiException(response);
        }

        // Save the user entity
        try {
            userRepository.save(user);
            response.setSuccess(true);
            response.setMessage("User Saved Successfully");
            response.setStatus(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
        }

        return response;
    }

    @Override
    public List<UpdatedUserEntity> getAllUsers() {
        // Retrieve all users from the userRepository
        return userRepository.findAll();
    }

    @Override
    public UpdatedUserEntity getUserById(Long id) {
        // Retrieve a user by its id from the userRepository
        return userRepository.findById(id).orElse(null);
    }

    // Add more method implementations as needed

}
