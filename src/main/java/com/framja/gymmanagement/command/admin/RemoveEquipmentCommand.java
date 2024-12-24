package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.EquipmentService;

public class RemoveEquipmentCommand implements ActionCommand<Boolean> {
    private final EquipmentService equipmentService;
    private final int equipmentId;

    public RemoveEquipmentCommand(EquipmentService equipmentService, int equipmentId) {
        this.equipmentService = equipmentService;
        this.equipmentId = equipmentId;
    }

    @Override
    public Boolean execute() {
        equipmentService.removeEquipment(equipmentId);
        return true;
    }
}
