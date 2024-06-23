package com.bookstore.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "khong qua 50")
    @NotBlank(message = "khong duoc de trong")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Size(max = 250, message =  "toi da 250")
    @Column(name = "description", length = 250)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", 
                joinColumns = @JoinColumn(name = "role_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
