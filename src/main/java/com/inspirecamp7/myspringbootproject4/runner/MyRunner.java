package com.inspirecamp7.myspringbootproject4.runner;

import com.inspirecamp7.myspringbootproject4.config.CustomVO;
import com.inspirecamp7.myspringbootproject4.properties.MyBootProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Consumer;

@Component
@Order(2)
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    String myBootName;

    @Value("${myboot.age}")
    int mybootAge;

    @Autowired
    Environment environment;

    @Autowired
    MyBootProperties properties;

    @Autowired
    CustomVO customVO;

    //Logger 생성
    Logger logger = LoggerFactory.getLogger(MyRunner.class);


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Logger 구현 클래스명 = " + logger.getClass().getName());
        logger.debug("CustomerVO = {}", customVO);
        logger.debug("환경변수 myboot.name = {}", myBootName);
        logger.debug("환경변수 myboot.age = {}", mybootAge);
        logger.debug("환경변수 myboot.fullName = {}", environment.getProperty("myboot.fullName"));

        logger.info("MyBootProperties getName = {}", properties.getFullName());
        logger.info("MyBootProperties getAge = {}", properties.getAge());
        logger.info("server.port 환경변수 확인 {}", args.containsOption("server.port"));
        logger.info("myboot.name 환경변수 확인 {}", args.containsOption("myboot.name"));

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
