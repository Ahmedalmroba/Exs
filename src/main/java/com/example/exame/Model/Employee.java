package com.example.exame.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = " position cannot be null")
    @Column(columnDefinition = "varchar(50)not null ")
    private String position;
    @Positive
    @NotEmpty(message = " salary cannot be null")
    private double salary;

    @OneToOne
    @JsonIgnore
    private User user;

}
