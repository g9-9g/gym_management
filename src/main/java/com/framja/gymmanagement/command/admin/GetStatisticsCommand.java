package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.EquipmentService;
import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.interfaces.CourseService;
import com.framja.gymmanagement.model.StatisticsDTO;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;

public class GetStatisticsCommand implements ActionCommand<StatisticsDTO> {
    private final EquipmentService equipmentService;
    private final UserService userService;
    private final CourseService courseService;

    public GetStatisticsCommand(EquipmentService equipmentService, UserService userService, CourseService courseService) {
        this.equipmentService = equipmentService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @Override
    public StatisticsDTO execute() {
        int totalEquipment = equipmentService.getTotalEquipmentCount();
        int totalMembers = userService.getAllUsersByRole(Member.class).size();
        int totalTrainers = userService.getAllUsersByRole(Trainer.class).size();
        int totalCourses = courseService.findAllCourses().size();

        return new StatisticsDTO(totalEquipment, totalMembers, totalTrainers, totalCourses);
    }
}
