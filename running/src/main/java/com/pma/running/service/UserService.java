package com.pma.running.service;

import com.pma.running.config.Password;
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

//        Submission submission = submissionRepository.findByCompanySecretId(client.getCompanyID()).orElseThrow(() -> (new BadRequestException("Bad company secret id")));
        System.out.println("EVO USAO SAM JEBEM TI PLEME");
        User newClient = user;
        newClient.setPassword(Password.getSaltedHash(user.getPassword()));

        try {
            newClient = userRepository.save(newClient);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return newClient;
    }

    public Boolean login(LoginRequest request) throws Exception {

        User newClient = userRepository.findByUsername(request.getUsername());
        boolean temp = false;
        try {
            temp = Password.check(request.getPassword(),newClient.getPassword());

        }catch (Exception e){

        }

        if(temp){
            return true;
        }else{
            return false;
        }
    }

}
