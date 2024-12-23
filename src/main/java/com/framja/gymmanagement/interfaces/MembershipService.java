package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;

import java.util.Optional;

public interface MembershipService {
    Optional<MembershipCard> getMembershipCard(User user);
    void assignMembershipCard(User user, MembershipCard membershipCard);
    boolean hasActiveMembership(User user);
}
