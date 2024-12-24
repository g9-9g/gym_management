package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.MembershipService;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.role.Member;

import java.time.LocalDate;

public class RegisterMembershipCommand implements ActionCommand<MembershipCard> {
    private final User user;
    private final MembershipService membershipService;
    private final MembershipCardType cardType;

    public RegisterMembershipCommand(User user, MembershipService membershipService, MembershipCardType cardType) {
        this.user = user;
        this.membershipService = membershipService;
        this.cardType = cardType;
    }

    @Override
    public MembershipCard execute() {
        if (!(user instanceof Member member)) {
            throw new IllegalStateException("User must be of type Member to register a membership.");
        }

        // Check if the member already has an active membership
        if (member.hasActiveMembership()) {
            throw new IllegalStateException("You already have an active membership.");
        }

        // Define default duration: 3 months
        int monthsToAdd = 3;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(monthsToAdd);

        // Create new MembershipCard and assign to member
        MembershipCard newCard = new MembershipCard(cardType, startDate, endDate, member);
        membershipService.addMembershipCard(newCard);
        member.setMembershipCard(newCard);

        return newCard;
    }
}
