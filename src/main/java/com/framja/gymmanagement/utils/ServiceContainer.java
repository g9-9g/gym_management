package com.framja.gymmanagement.utils;

import com.framja.gymmanagement.interfaces.*;
import com.framja.gymmanagement.service.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceContainer {
    private static ServiceContainer instance;

    public static ServiceContainer getInstance() {
        if (instance == null) {
            init();
        }

        return instance;
    }

    private final Map<Class<?>, Object> services = new HashMap<>();

    public <T> void addService(Class<T> serviceClass, T serviceInstance) {
        services.put(serviceClass, serviceInstance);
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> serviceClass) {
        T service = (T) services.get(serviceClass);
        if (service == null) {
            throw new RuntimeException("Service not found: " + serviceClass.getName());
        }
        return service;
    }

    private static void init() {
        MembershipService membershipService = new MembershipServiceImpl();
        UserService userService = new UserServiceImpl();
        ClassService classService = new ClassServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        PaymentService paymentService = new PaymentServiceImpl();
        AuthService authService = new AuthServiceImpl(userService);
        instance = new ServiceContainer();
        instance.addService(MembershipService.class, membershipService);
        instance.addService(ClassService.class, classService);
        instance.addService(UserService.class, userService);
        instance.addService(CourseService.class, courseService);
        instance.addService(PaymentService.class, paymentService);
        instance.addService(AuthService.class, authService);
    }
}
