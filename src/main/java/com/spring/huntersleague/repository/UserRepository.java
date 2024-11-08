package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
    void deleteById(UUID id);
    boolean existsById(UUID id);
    Optional<Object> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE "
            + "(:username IS NULL OR u.username LIKE %:username%) AND "
            + "(:cin IS NULL OR u.cin = :cin)")
    List<User> searchByCriteria(@Param("username") String username,
                                @Param("cin") String cin);

}
