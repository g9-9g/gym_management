package com.framja.gymmanagement.model;

import com.framja.gymmanagement.constants.MembershipCardType;

import java.time.LocalDate;
import java.util.List;

public class MembershipCard {
    private final MembershipCardType type; // Loại membership
    private final LocalDate startDate; // Ngày bắt đầu
    private final LocalDate endDate; // Ngày kết thúc
    private final List<String> permissions; // Các tài nguyên được phép truy cập

    public MembershipCard(MembershipCardType type, LocalDate startDate, LocalDate endDate, List<String> permissions) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.permissions = permissions;
    }

    public MembershipCardType getType() {
        return type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public boolean isActive() {
        return LocalDate.now().isBefore(endDate);
    }

    @Override
    public String toString() {
        return "MembershipCard{" +
                "type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", permissions=" + permissions +
                '}';
    }
}
