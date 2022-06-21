//package com.agosh.userservice.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.testcontainers.containers.MySQLContainer;
//
//@Profile("test")
//@Configuration
//@Slf4j
//public class TestContainer {
//    private static final MySQLContainer MY_SQL_CONTAINER = initContainer();
//
//    public static MySQLContainer initContainer() {
//
//        MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
//                .withDatabaseName("taxidb")
//                .withPassword("root")
//                .withUsername("root");
//        mySQLContainer.start();
//        return mySQLContainer;
//    }
//
//    @Bean
//    public MySQLContainer mySQLContainer(){
//        return MY_SQL_CONTAINER;
//    }
//
//}
