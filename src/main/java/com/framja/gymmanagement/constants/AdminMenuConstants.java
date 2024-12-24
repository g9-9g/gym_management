package com.framja.gymmanagement.constants;

/**
 * Hằng số đại diện cho các tùy chọn menu của vai trò Admin.
 * Các hằng số này được sử dụng với phương thức `performAction` để thực thi các lệnh cụ thể cho Admin.
 */
public class AdminMenuConstants {

    // Equipment Management
    public static final int ADD_EQUIPMENT = 1;
    public static final int UPDATE_EQUIPMENT = 2;
    public static final int REMOVE_EQUIPMENT = 3;
    public static final int VIEW_ALL_EQUIPMENT = 4;

    // User Management
    public static final int ADD_USER = 5;
    public static final int UPDATE_MEMBER = 6;
    public static final int UPDATE_TRAINER = 7;
    public static final int REMOVE_USER = 8;
    public static final int FIND_ALL_USERS = 9;
    public static final int FIND_USER_BY_USERNAME = 10;

    // Statistics
    public static final int GET_STATISTICS = 11;

    // Course Management
    public static final int CREATE_COURSE = 12;
    public static final int READ_ALL_COURSES = 13;
    public static final int UPDATE_COURSE = 14;
    public static final int DELETE_COURSE = 15;

    // GymClass Management
    public static final int CREATE_GYMCLASS = 16;
    public static final int READ_ALL_GYMCLASSES = 17;
    public static final int UPDATE_GYMCLASS = 18;
    public static final int DELETE_GYMCLASS = 19;
    public static final int VIEW_CLASSES_FROM_COURSE = 20;

    private AdminMenuConstants() {
    }
}
