package com.sisdist.cinema.config;

import com.sisdist.cinema.rmi.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Configuration
public class RmiClientConfig {

//    @Bean("rmiUserService")  // Nome único para o bean
//    public UserService userService() throws Exception {
//        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
//        return (UserService) registry.lookup("UserService");
//    }
}
