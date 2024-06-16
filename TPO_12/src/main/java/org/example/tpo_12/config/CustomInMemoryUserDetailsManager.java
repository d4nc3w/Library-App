//package org.example.tpo_12.config;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@Service
//public class CustomInMemoryUserDetailsManager extends InMemoryUserDetailsManager {
//
//    public CustomInMemoryUserDetailsManager() {
//        User.UserBuilder builder = User.builder();
//        UserDetails user1 = builder.username("Piotr")
//                .password("{bcrypt}" + new BCryptPasswordEncoder().encode("Pjatk123"))
//                .roles("ADMIN").build();
//        UserDetails user2 = builder.username("Maciej")
//                .password("{MD5}" + new MessageDigestPasswordEncoder("MD5").encode("TPOtask12"))
//                .roles("LIBRARIAN").build();
//        UserDetails user3 = builder.username("Szymon")
//                .password("{noop}Pjwstk0112?")
//                .roles("PUBLISHER").build();
//        UserDetails user4 = builder.username("Wojciech")
//                .password("{SHA-256}" + new MessageDigestPasswordEncoder("SHA-256").encode("Euro2024"))
//                .roles("READER").build();
//        UserDetails user5 = User.builder()
//                .username("Anna")
//                .password("{pbkdf2}" + new Pbkdf2PasswordEncoder("secret", 5, 100, 10).encode("Pjatk2021"))
//                .roles("GUEST")
//                .build();
//        createUser(user1);
//        createUser(user2);
//        createUser(user3);
//        createUser(user4);
//        createUser(user5);
//
//        System.out.println(user1.getPassword());
//        System.out.println(user2.getPassword());
//        System.out.println(user3.getPassword());
//        System.out.println(user4.getPassword());
//        System.out.println(user5.getPassword());
//    }
//
//}
