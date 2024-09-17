package com.example.exame.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = " username cannot be null")
    @Column(columnDefinition = "varchar(50)not null unique")
    @Size(min = 6)
    private String username;
    @NotEmpty(message = " name cannot be null")
    @Column(columnDefinition = "varchar(50)not null unique")
    @Size(min = 2,max = 20)
    private String name;
    @NotEmpty(message = " password cannot be null")
    @Column(columnDefinition = "varchar(50)not null unique")
    @Size(min = 4,max = 10)
    private String password;
    @Email
    private String email;
@Pattern(regexp = "^CUSTOMER|EMPLOYEE|ADMIN")
    public String role;

@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Employee employee;
}
