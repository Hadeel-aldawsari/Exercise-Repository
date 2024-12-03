package com.example.usersmanagement.Controller;

import com.example.usersmanagement.ApiResponse.ApiException;
import com.example.usersmanagement.ApiResponse.ApiResponse;
import com.example.usersmanagement.Model.User;
import com.example.usersmanagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity getAllUser(){
    return ResponseEntity.status(200).body(userService.getAllUser());
    }

    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody @Valid User user,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }

    @PostMapping("/login/username/{username}/password/{password}")
    public ResponseEntity logIn(@PathVariable String username,@PathVariable String password){
        userService.logIn(username,password);
     return ResponseEntity.status(200).body(new ApiResponse("user loged in successfully"));
    }

    @GetMapping("/get-user-email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    @GetMapping("/get-users/role/{role}")
    public ResponseEntity getAllUsersByRole(@PathVariable String role){
        return ResponseEntity.status(200).body(userService.getAllUsersByRole(role));
    }

    @GetMapping("/get-users/age-range/{age}")
    public ResponseEntity getAllUsersByAgeRange(@PathVariable Integer age){
        return ResponseEntity.status(200).body(userService.getAllUsersByAgeRange(age));
    }
}
