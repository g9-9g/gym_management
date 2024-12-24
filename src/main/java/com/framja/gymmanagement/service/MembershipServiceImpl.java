package com.framja.gymmanagement.service;

import com.framja.gymmanagement.interfaces.MembershipService;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.role.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MembershipServiceImpl implements MembershipService {
    private final List<MembershipCard> membershipCards = new ArrayList<>();

    @Override
    public Optional<MembershipCard> getMembershipCard(User user) {
        return membershipCards.stream()
                .filter(card -> card.getOwner().equals(user))
                .findFirst();
    }

    @Override
    public void addMembershipCard(MembershipCard membershipCard) {
        membershipCards.removeIf(mc -> mc.getOwner().equals(membershipCard.getOwner()));
        membershipCards.add(membershipCard);
    }


}
