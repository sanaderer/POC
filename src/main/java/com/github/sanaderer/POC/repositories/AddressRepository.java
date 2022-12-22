package com.github.sanaderer.POC.repositories;

import com.github.sanaderer.POC.entities.AddressEntity;
import com.github.sanaderer.POC.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
    List<AddressEntity> findByUserOrderByDateCreated(UserEntity userEntity);
}
