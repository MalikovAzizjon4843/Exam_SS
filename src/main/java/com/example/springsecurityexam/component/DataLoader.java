package com.example.springsecurityexam.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.springsecurityexam.entity.User;
import com.example.springsecurityexam.entity.enums.RoleEnum;
import com.example.springsecurityexam.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;


    final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

//        User user = new User();
//        user.setUsername("user");
//        user.setPassword(passwordEncoder.encode("user"));
//        user.setRoleEnum(RoleEnum.ADMIN);
//        userRepository.save(user);
//
//        User admin = new User();
//        admin.setRoleEnum(RoleEnum.ADMIN);
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("admin"));
//        userRepository.save(admin);

        User user=new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRoleEnum(RoleEnum.ADMIN);

        userRepository.save(user);

        User admin=new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("1212"));
        admin.setRoleEnum(RoleEnum.ADMIN1);
        userRepository.save(admin);



    }
}
