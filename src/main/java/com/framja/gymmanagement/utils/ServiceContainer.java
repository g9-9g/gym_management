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
        instance = new ServiceContainer();

        System.out.println("Initializing services...");

        MembershipService membershipService = new MembershipServiceImpl();
        System.out.println("MembershipService initialized.");
        instance.addService(MembershipService.class, membershipService);

        PaymentService paymentService = new PaymentServiceImpl();
        System.out.println("PaymentService initialized.");
        instance.addService(PaymentService.class, paymentService);

        ClassService classService = new ClassServiceImpl();
        System.out.println("ClassService initialized.");
        instance.addService(ClassService.class, classService);

        CourseService courseService = new CourseServiceImpl();
        System.out.println("CourseService initialized.");
        instance.addService(CourseService.class, courseService);

        UserService userService = new UserServiceImpl();
        System.out.println("UserService initialized.");
        instance.addService(UserService.class, userService);

        AuthService authService = new AuthServiceImpl(userService);
        System.out.println("AuthService initialized.");
        instance.addService(AuthService.class, authService);

        EquipmentService equipmentService = new EquipmentServiceImpl();
        System.out.println("EquipmentService initialized.");
        instance.addService(EquipmentService.class, equipmentService);

        System.out.println("All services initialized.");
    }


}
