        package com.inspirecamp7.myspringbootproject4;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.WebApplicationType;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

        @SpringBootApplication
        public class MySpringBootProject4Application {

            public static void main(String[] args) {

    //            SpringApplication.run(MySpringBootProject4Application.class, args);
                SpringApplication application = new SpringApplication(MySpringBootProject4Application.class);
                application.setWebApplicationType(WebApplicationType.SERVLET);
                application.run(args);
            }

        }
