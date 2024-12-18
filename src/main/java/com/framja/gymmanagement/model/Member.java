package com.framja.gymmanagement.model;

// import java.sql.Date;
import com.framja.gymmanagement.interfaces.IManageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member extends User {
    private MembershipCard membershipCard;
    private List<GymClass> enrolledClasses;

    public Member(String username, String email, String password) {
        super(username, email, password);
        super.setRole(Role.MEMBER);
        this.enrolledClasses = new ArrayList<>();
    }

    public MembershipCard getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
    }

    public boolean enrollInClass(GymClass classroom) {
        if (classroom.addMember(this.getId())) {
            enrolledClasses.add(classroom);
            // System.out.println(name + " enrolled in " + classroom.getName());
            return true;
        }
        return false;
    }

    // Remove the member from a class
    public boolean leaveClass(GymClass classroom) {
        if (classroom.removeMember(this.getId())) {
            enrolledClasses.remove(classroom);
            // System.out.println(name + " left " + classroom.getName());
            return true;
        }
        return false;
    }

    public List<GymClass> getEnrolledClasses() {
        return enrolledClasses;
    }

}
