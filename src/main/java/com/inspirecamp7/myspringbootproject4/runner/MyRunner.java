package com.inspirecamp7.myspringbootproject4.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Consumer;

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

        // 1단계 Annoymous Inner Class (익명 내부 클래스)
        optionNames.forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                System.out.println("Inner Class인 경우 name = " + name);
            }
        });

        // 2단계 람다 표현식
        optionNames.forEach(name -> System.out.println("name = " + name));


        // 3단계 Method Reference
        optionNames.forEach(System.out::println);

    }
}
