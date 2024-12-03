package com.example.usersmanagement.Service;

import com.example.usersmanagement.ApiResponse.ApiException;
import com.example.usersmanagement.Model.User;
import com.example.usersmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser(){
        if(userRepository.findAll().isEmpty())throw new ApiException("no user registered");
        return userRepository.findAll();
    }

    public void addUser(User user){
       userRepository.save(user);
    }

    public void updateUser(Integer id,User user){
        User originUser=userRepository.findUserById(id);

        if(originUser==null) throw new ApiException("user id not found");

        originUser.setName(user.getName());
       originUser.setUsername(user.getUsername());
        originUser.setPassword(user.getPassword());
        originUser.setEmail(user.getEmail());
        originUser.setRole(user.getRole());
        originUser.setAge(user.getAge());

        userRepository.save(originUser);
    }

    public void delete(Integer id){
        User user=userRepository.findUserById(id);
        if(user==null) throw new ApiException("user id not found");

        userRepository.delete(user);
    }

    public void logIn(String username,String password){
        User user=userRepository.findUserByUsernameAndPassword(username,password);
        if(user==null) throw new ApiException("username or password incorrect!");
    }

    public User getUserByEmail(String email){
        User user=userRepository.getUserByEmail(email);
        if(user==null) throw new ApiException("there is no user registered with this email");

        //else
        return user;
    }

    public List<User> getAllUsersByRole(String role){
        List<User>users=userRepository.getAllUsersByRole(role);
        if(users.isEmpty()) throw new ApiException("there is no users registered with "+role+" role");
        //else
        return users;
    }

    public List<User> getAllUsersByAgeRange(Integer age){
        List<User>users=userRepository.getAllUsersByAgeRange(age);
        if(users.isEmpty()) throw new ApiException("there is no users within this age range");
        //else
        return users;
    }
}
