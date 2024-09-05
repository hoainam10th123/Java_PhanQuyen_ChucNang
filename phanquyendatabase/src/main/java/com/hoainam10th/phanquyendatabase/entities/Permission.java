package com.hoainam10th.phanquyendatabase.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name; //TODO_ADD luu tu bang function

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    public Permission(String name){
        this.name = name;
    }
}
