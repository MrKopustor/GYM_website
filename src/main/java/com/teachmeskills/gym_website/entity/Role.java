package com.teachmeskills.gym_website.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role", nullable = false)
    private long id;

    @Column(name = "role_title", nullable = false)
    private String role;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> userList = new ArrayList<>();

}
