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
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()]).{8,20}$", message = "Password must be between 8 and 20 characters long, and include at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#&()).")
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
