package com.inspirecamp7.myspringbootproject4.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(2)
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    String myBootName;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("환경변수 myboot.name = " + myBootName);
        System.out.println("server.port 환경변수 확인 " + args.containsOption("server.port"));
        System.out.println("myboot.name 환경변수 확인 " + args.containsOption("myboot.name"));

        // 환경변수 목록을 확인하기
        Set<String> optionNames = args.getOptionNames();
        for (String name : optionNames) {
            System.out.println("name = " + name);
        }

        optionNames.forEach(name -> System.out.println("name = " + name));
        
    }
}
