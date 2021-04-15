package com.Lanja.finnancial.services;

import com.Lanja.finnancial.entity.ApplicationUserDetails;
import com.Lanja.finnancial.exception.ApiBadRequestException;
import com.Lanja.finnancial.exception.ApiNotFoundRequestException;
import com.Lanja.finnancial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository,
                                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUserDetails userDetails = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new ApiNotFoundRequestException("User Not Found"));
        System.out.println(userDetails);
        userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        return userDetails;
    }

    public void registrationUser(ApplicationUserDetails applicationUserDetails) {
        boolean userExists = userRepository.findUserByUserName(applicationUserDetails.getUsername())
                .isPresent();
        if(userExists) {
            throw new ApiBadRequestException("Username has been used");
        }
        String password = bCryptPasswordEncoder.encode(applicationUserDetails.getPassword());
        applicationUserDetails.setPassword(password);
        userRepository.save(applicationUserDetails);
    }

    @Transactional
    public void updateUser(String usernameAsKey, String userName, String password, String role, boolean locked, boolean enable) {

    }

    public void deleteUserByUsername(String userName) {
        ApplicationUserDetails userDetails = userRepository.findUserByUserName(userName)
                .orElseThrow(() -> new ApiNotFoundRequestException("Username not found for delete"));
        userRepository.delete(userDetails);
    }
}
