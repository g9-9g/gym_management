package com.framja.gymmanagement.constants;

import java.util.List;

public enum MembershipCardType {
    BASIC("Thành viên Cơ bản", "Truy cập vào các tiện ích cơ bản như thiết bị gym và khu vực chung.",
            List.of("Gym Equipment", "Locker Room"),
            ""),
    PREMIUM("Thành viên Cao cấp", "Truy cập vào tất cả tiện ích, bao gồm hồ bơi và các thiết bị nâng cao.",
            List.of("Gym Equipment", "Swimming Pool", "Advanced Classes"),
            ""),
    VIP("Thành viên VIP", "Bao gồm mọi quyền lợi của Cao cấp, cộng thêm huấn luyện viên cá nhân và phòng chờ VIP.",
            List.of("Gym Equipment", "Swimming Pool", "Advanced Classes", "Personal Trainer", "VIP Lounge"),
            "");

    private final String name;
    private final String description;
    private final List<String> permissions;
    private final String imageUrl;
    MembershipCardType(String name, String description, List<String> permissions, String imageUrl) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return name + ": " + description + ", Permissions: " + permissions;
    }
}
