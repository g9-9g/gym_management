package com.framja.gymmanagement.model;

import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.role.Member;

import java.time.LocalDate;

public class MembershipCard {
    private MembershipCardType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Member owner;

    public MembershipCard(MembershipCardType type, LocalDate startDate, LocalDate endDate, Member owner) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.owner = owner;
    }

    public MembershipCardType getType() {
        return type;
    }

    public void setType(MembershipCardType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
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
                ", owner=" + (owner != null ? owner.getUsername() : "null") +
                '}';
    }
}
