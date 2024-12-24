package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.EquipmentService;
import com.framja.gymmanagement.model.Equipment;

public class AddEquipmentCommand implements ActionCommand<Boolean> {
    private final EquipmentService equipmentService;
    private final Equipment equipment;

    public AddEquipmentCommand(EquipmentService equipmentService, Equipment equipment) {
        this.equipmentService = equipmentService;
        this.equipment = equipment;
    }

    @Override
    public Boolean execute() {
        equipmentService.addEquipment(equipment);
        return true;
    }
}
