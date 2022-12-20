package com.github.sanaderer.POC.repository;

import com.github.sanaderer.POC.entity.UserEntity;
import com.github.sanaderer.POC.enums.UserEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Page<UserEntity> findByDocumentType(UserEnum userEnum, Pageable pageable);
}
