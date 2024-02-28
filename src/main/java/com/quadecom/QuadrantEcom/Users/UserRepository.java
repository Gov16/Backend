package com.quadecom.QuadrantEcom.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface UserRepository extends JpaRepository<UpdatedUserEntity, Long> {

    UserEntity findByUsername(String username);

    List<UserEntity> findByEmailContaining(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(BigInteger phone);
}
