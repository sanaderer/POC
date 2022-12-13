package com.github.sanaderer.POC.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "Address")
@Entity(name = "Address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "usergit branch_id")
    private UserEntity user;

    private Integer cep;

    private String street;

    private String number;

    private String city;

    private String state;

    @JsonIgnore
    private Boolean mainAddress;

    @CreatedDate
    @Column(name = "dt_created")
    private LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(name = "dt_updated")
    private LocalDateTime dateUpdated;

}
