package com.kafka.order_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {



    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "login_counter", nullable = false)
    private Integer loginCounter;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "account_non_expired", nullable = false)
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    private Boolean accountNonLocked;

//    @Column(name = "created_date", nullable = false)
//    private Instant createdDate;
//
//    @Column(name = "updated_date", nullable = false)
//    private Instant updatedDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="created_by")
//    private UserEntity createdBy;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="updated_by")
//    private UserEntity updatedBy;



public UserEntity (Long id)
{
    super(id);
}

}
