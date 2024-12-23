package com.framja.gymmanagement.service;

import com.framja.gymmanagement.interfaces.MembershipService;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MembershipServiceImpl implements MembershipService {
    private final Map<User, MembershipCard> membershipCards = new HashMap<>();

    @Override
    public Optional<MembershipCard> getMembershipCard(User user) {
        return Optional.ofNullable(membershipCards.get(user));
    }

    @Override
    public void assignMembershipCard(User user, MembershipCard membershipCard) {
        membershipCards.put(user, membershipCard);
    }

    @Override
    public boolean hasActiveMembership(User user) {
        return membershipCards.containsKey(user) &&
                membershipCards.get(user).isActive();
    }
}
