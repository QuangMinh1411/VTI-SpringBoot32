package com.heaven.ss_2022_c2_1;

import com.heaven.ss_2022_c2_1.entity.User;
import com.heaven.ss_2022_c2_1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Ss2022C21Application {




    public static void main(String[] args) {
        SpringApplication.run(Ss2022C21Application.class, args);
    }


}
