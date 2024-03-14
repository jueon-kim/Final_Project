package com.hwagae.market.report;

import com.hwagae.market.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {
    List<ReportEntity> findAllByUserEntity(UserEntity userEntity);

    Page<ReportEntity> findByUserEntity(UserEntity userEntity, Pageable pageable);
}
