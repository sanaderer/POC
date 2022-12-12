package com.github.sanaderer.POC.entities;

import com.github.sanaderer.POC.enums.UserEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "Users")
@Entity(name = "Users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @NotBlank
    private String email;

    @NotBlank
    private String document;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private UserEnum documentType;

    @NotBlank
    private String telephone;

    @CreatedDate
    @Column(name = "dt_created")
    private LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(name = "dt_updated")
    private LocalDateTime dateUpdated;

}
