package com.example.usersmanagement.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity

@Check(constraints = "role='admin' or role='user'")
@Check(constraints = "age>0")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Size(min=5,message = "name Length more than 4")
    @Column(columnDefinition ="varchar(20) not null")
    private String name;

    @NotEmpty(message = "user name should not be empty")
    @Size(min=5,message = "name Length more than 4")
    @Column(columnDefinition ="varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password should not be empty")
//    @Pattern(regexp ="(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",message ="Password must contain at least one digit [0-9].\n" +
//            "Password must contain at least one lowercase Latin character [a-z].\n" +
//            "Password must contain at least one uppercase Latin character [A-Z].\n" +
//            "Password must contain at least one special character like ! @ # & ( ).\n" +
//            "Password must contain a length of at least 8 characters and a maximum of 20 characters." )
    @Column(columnDefinition ="varchar(20) not null")
    private String password;

    @NotEmpty(message = "email should not be empty")
    @Email(message = "enter valid email")
    @Column(columnDefinition ="varchar(30) not null unique")
    private String email;

    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "user|admin",message = "role should be admin or user")
    @Column(columnDefinition ="varchar(5) not null")
    private String role;

    @NotNull(message = "age should not be null")
    @Positive(message = "enter valid age")
    @Column(columnDefinition = "int not null")
    private Integer age;

}
