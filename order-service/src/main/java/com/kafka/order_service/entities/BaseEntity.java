package com.kafka.order_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_on")
    private Instant createdDate;

    @Column(name="updated_on")
    private Instant updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by")
    private UserEntity createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="updated_by")
    private UserEntity updatedBy;

    @PrePersist
    public void  prepersist(){
        this.createdDate = Instant.now();
        this.updatedDate = Instant.now();
        this.createdBy = new UserEntity(1L);
    }

    public BaseEntity(Long id){
        this.id =id;
    }
}
