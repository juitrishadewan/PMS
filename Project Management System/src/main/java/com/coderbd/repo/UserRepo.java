package com.coderbd.repo;

import com.coderbd.entity.Role;
import com.coderbd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserNameOrEmail(String userName, String email);
    User findByUserName(String username);
    Optional<User> findByEmail(String email);
     List<User> findAllByRoles(Set<Role> roles);
     boolean existsByEmail(String email);
     User findByConfirmationTokenAndTokenExpired(String token,boolean tokenExpired);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1, u.tokenExpired=?2 where u.email = ?3")
    int setPasswordAndTokenExpiredByEmail(String password,boolean tokenExpired, String email);

    @Transactional
    @Modifying
    @Query("update User u set u.tokenExpired=?1 where u.email = ?2")
    int setTokenExpiredByEmail(boolean tokenExpired, String email);
}
