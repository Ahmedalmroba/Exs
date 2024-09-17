package com.example.exame.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CustomerDTO {
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
    @NotEmpty(message = " position cannot be null")
    @Column(columnDefinition = "varchar(50)not null ")
    private String phoneNumber;
}
