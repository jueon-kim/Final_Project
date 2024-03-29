package com.hwagae.market.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findByUserName(String userName);

    @Transactional
    void deleteByUserId(String userId);

    Optional<UserEntity> findByUserNick(String userNick);
}
