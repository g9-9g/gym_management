package com.framja.gymmanagement.constants;

/**
 * Enum đại diện cho các loại vai trò trong hệ thống.
 */
public enum RoleType {
    MEMBER("Member"),
    TRAINER("Trainer"),
    ADMIN("Admin");

    private final String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
