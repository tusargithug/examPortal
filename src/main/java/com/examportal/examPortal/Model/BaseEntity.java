package com.examportal.examPortal.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, length = 50)
    private String id;

    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdDate;

    //@Version
    private LocalDateTime lastUpdatedDate = LocalDateTime.now();
}

