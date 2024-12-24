package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.MembershipService;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.role.Member;

public class ViewMembershipCommand implements ActionCommand<MembershipCard> {
    private final User user;
    private final MembershipService membershipService;

    public ViewMembershipCommand(User user, MembershipService membershipService) {
        this.user = user;
        this.membershipService = membershipService;
    }


    @Override
    public MembershipCard execute() {
        if (!(user instanceof Member member)) {
            throw new IllegalStateException("User must be of type Member to register a membership.");
        }

        return membershipService.getMembershipCard(member)
                .orElse(null);
    }
}
