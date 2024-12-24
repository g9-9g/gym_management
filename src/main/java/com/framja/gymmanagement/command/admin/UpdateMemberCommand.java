package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.role.Member;

public class UpdateMemberCommand implements ActionCommand<Boolean> {
    private final UserService userService;
    private final Member updatedMember;

    public UpdateMemberCommand(UserService userService, Member updatedMember) {
        this.userService = userService;
        this.updatedMember = updatedMember;
    }

    @Override
    public Boolean execute() {
        return userService.updateMember(updatedMember);
    }
}
