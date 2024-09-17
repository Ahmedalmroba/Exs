package com.example.exame.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = " position cannot be null")
    @Column(columnDefinition = "varchar(50)not null ")
    private String phoneNumber;
    @OneToOne
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Account> accounts;
}
