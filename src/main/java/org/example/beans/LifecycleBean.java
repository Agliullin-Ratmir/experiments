package org.example.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifecycleBean {

    private String status;

    public LifecycleBean() {
        System.out.println("New lifecycle bean has been constructed");
        this.status = "Constructor";
        System.out.println("Current status is: " + this.status);
    }

    @PostConstruct
    public void init() {
        this.status = "PostConstruct";
        System.out.println("Current status is: " + this.status);
    }

    @PreDestroy
    public void destroy() {
        this.status = "Destroy";
        System.out.println("Current status is: " + this.status);
    }
}
