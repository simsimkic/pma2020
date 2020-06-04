package com.pma.running.service;

import com.pma.running.config.Password;
import com.pma.running.dto.EditUserRequest;
import com.pma.running.dto.LoginRequest;
import com.pma.running.model.User;
import com.pma.running.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) throws Exception {

        User newClient = user;
        newClient.setPassword(Password.getSaltedHash(user.getPassword()));

        try {
            newClient = userRepository.save(newClient);
        } catch (Exception e) {
            newClient = null;
        }
        return newClient;
    }

    public User login(LoginRequest request) throws Exception {
        if(userRepository.findByUsername(request.getUsername()) != null){
            User newClient = userRepository.findByUsername(request.getUsername());
            System.out.println(newClient.getEmail());
            boolean temp = false;
            try {
                temp = Password.check(request.getPassword(),newClient.getPassword());

            }catch (Exception e){

            }

            if(temp){
                return newClient;
            }else{
                return null;
            }
        }else{
            return null;
        }

    }

    public User editUser(EditUserRequest editUserRequest) throws Exception {
        if(userRepository.findByUsername(editUserRequest.getUsername()) != null){
            User newClient = userRepository.findByUsername(editUserRequest.getUsername());
            if(editUserRequest.getName() != ""){
                newClient.setName(editUserRequest.getName());
            }
            if(editUserRequest.getEmail() != ""){
                newClient.setEmail(editUserRequest.getEmail());
            }
            if(editUserRequest.getAddress() != ""){
                newClient.setAddress(editUserRequest.getAddress());
            }
            newClient.setDateOfBirth(editUserRequest.getDateOfBirth());
            if(editUserRequest.getBiography() != ""){
                newClient.setBiography(editUserRequest.getBiography());
            }
            if(editUserRequest.getHeight() != ""){
                newClient.setHeight(editUserRequest.getHeight());
            }
            if(editUserRequest.getWeight() != ""){
                newClient.setWeight(editUserRequest.getWeight());
            }



            try {
                newClient = userRepository.save(newClient);
            } catch (Exception e) {
                newClient = null;
            }
            return newClient;
        }else{
            return null;
        }



    }



}
