package com.example.backend.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long id;

    @Column(name = "username", length = 255, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "is_activate", nullable = false)
    private Boolean is_activate;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false, updatable = false)
    private Instant created_at;

    @UpdateTimestamp
    @Column(name = "update_at",nullable = false)
    private Instant update_at;

    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoles = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        is_activate = true;
    }
}
