package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.MembershipService;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;

public class ViewMembershipCommand implements ActionCommand<MembershipCard> {
    private final User user;
    private final MembershipService membershipService;

    public ViewMembershipCommand(User user, MembershipService membershipService) {
        this.user = user;
        this.membershipService = membershipService;
    }


    @Override
    public MembershipCard execute() {
        return membershipService.getMembershipCard(user)
                .orElse(null);
    }
}
