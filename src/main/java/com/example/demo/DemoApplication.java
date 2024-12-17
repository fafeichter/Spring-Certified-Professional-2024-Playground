package com.example.demo;

import com.example.demo.TaskConfig.Task;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoApplication {

    Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

    private Bar foo3;

    public DemoApplication(Bar foo3, Task task, ApplicationContext applicationContext) {
        this.foo3 = foo3;
        LOG.info("Task: " + task);
        LOG.info("Task: " + applicationContext.getBean(Task.class));
        LOG.info("Task: " + applicationContext.getBean(Task.class));
    }

    @Bean
    @Profile({"p1", "!p2"})
    public String foo() {
        LOG.info("I am foo.");
        return "foo";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    public Bar getFoo3() {
        return foo3;
    }

    @Around("com.xyz.myapp.SystemArchitecture.businessService()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        Object retVal = pjp.proceed();
        // stop stopwatch
        return retVal;
    }

    @Component
    public class Person {
        @Autowired
        private IAnimal dog;
    }

    @Component
    public class PersonInject {
        @Inject
        private IAnimal dog;

        public PersonInject(IAnimal dog) {
            this.dog = dog;
        }
    }

    @Component
    public class PersonInject2 {

        private IAnimal dog;

        @Inject
        public void setDog(IAnimal dog) {
            this.dog = dog;
        }
    }

    @Component
    public class PersonResource {
        @Resource
        private IAnimal dog;

        public PersonResource(IAnimal dog) {
            this.dog = dog;
        }
    }
}
