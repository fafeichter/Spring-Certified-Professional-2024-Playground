package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoApplication {

    private Bar foo3;

    public DemoApplication(Bar foo3) {
        this.foo3 = foo3;
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
}
