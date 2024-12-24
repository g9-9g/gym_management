package com.framja.gymmanagement.role;

import com.framja.gymmanagement.constants.RoleType;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;

public class Member extends User {

    private MembershipCard membershipCard;
    private String imageUrl;

    public Member(String username, String password, String phoneNumber, String gender, String address, MembershipCard membershipCard) {
        super(username, password, RoleType.MEMBER, phoneNumber, gender, address);
        this.membershipCard = membershipCard;
        if (membershipCard != null) {
            this.membershipCard.setOwner(this);
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MembershipCard getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
        if (membershipCard != null) {
            this.membershipCard.setOwner(this);
        }
    }

    public boolean hasActiveMembership() {
        return membershipCard != null && membershipCard.isActive();
    }
}
