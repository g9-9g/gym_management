package com.framja.gymmanagement.command.member;

import com.framja.gymmanagement.constants.MembershipCardType;
import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.MembershipService;
import com.framja.gymmanagement.model.MembershipCard;
import com.framja.gymmanagement.model.User;

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
        // Kiểm tra membership hiện tại
        if (membershipService.hasActiveMembership(user)) {
            throw new IllegalStateException("You already have an active membership.");
        }

        // Thời hạn mặc định: 3 tháng
        int monthsToAdd = 3;

        // Tạo thẻ membership mới
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(monthsToAdd);
        MembershipCard newCard = new MembershipCard(cardType, startDate, endDate, cardType.getPermissions());

        // Gán thẻ membership mới cho user
        membershipService.assignMembershipCard(user, newCard);

        return newCard;
    }
}
