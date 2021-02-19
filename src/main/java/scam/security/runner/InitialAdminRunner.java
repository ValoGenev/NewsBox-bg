package scam.security.runner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import scam.entity.UserEntity;
import scam.repository.IUserRepository;

import java.util.HashSet;

import static scam.model.UserRole.ADMIN;

@Component
public class InitialAdminRunner implements CommandLineRunner {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        String firstAdminUsername = "hipar";
        UserEntity firstAdminInDb = userRepository.findByUsername(firstAdminUsername).orElse(null);

        String secondAdminUsername = "manqk";
        UserEntity secondAdminInDb = userRepository.findByUsername(secondAdminUsername).orElse(null);

        if(firstAdminInDb==null){
            firstAdminInDb = new UserEntity();
            firstAdminInDb.setUsername(firstAdminUsername);
            String firstAdminPassword = "gei";
            firstAdminInDb.setAvatarColor("#ad1aa4");
            firstAdminInDb.setPassword(passwordEncoder.encode(firstAdminPassword));
            firstAdminInDb.setRole(ADMIN);
            firstAdminInDb.setPosts(new HashSet<>());
            firstAdminInDb.setComments(new HashSet<>());
            userRepository.save(firstAdminInDb);
        }

        if(secondAdminInDb==null){
            secondAdminInDb = new UserEntity();
            secondAdminInDb.setUsername(secondAdminUsername);
            String secondAdminPassword = "manqk";
            secondAdminInDb.setAvatarColor("#ad1aa4");
            secondAdminInDb.setPassword(passwordEncoder.encode(secondAdminPassword));
            secondAdminInDb.setRole(ADMIN);
            secondAdminInDb.setPosts(new HashSet<>());
            secondAdminInDb.setComments(new HashSet<>());
            userRepository.save(secondAdminInDb);
        }

    }
}
