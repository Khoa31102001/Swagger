package com.example.backend.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;
    @Column(name = "role_name", length = 255, nullable = false, unique = true)
    private String name;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false, updatable = false)
    private Instant created_at;

    @UpdateTimestamp
    @Column(name = "update_at",nullable = false)
    private Instant update_at;

    @OneToMany(mappedBy = "role")
    private List<AccountRole> accountRoles = new ArrayList<>();

}
