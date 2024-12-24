package com.framja.gymmanagement.command.admin;

import com.framja.gymmanagement.interfaces.ActionCommand;
import com.framja.gymmanagement.interfaces.EquipmentService;
import com.framja.gymmanagement.model.Equipment;

import java.util.List;

public class ViewAllEquipmentCommand implements ActionCommand<List<Equipment>> {
    private final EquipmentService equipmentService;

    public ViewAllEquipmentCommand(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @Override
    public List<Equipment> execute() {
        return equipmentService.getAllEquipment();
    }
}
