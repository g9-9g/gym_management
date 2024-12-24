package com.framja.gymmanagement.service;

import com.framja.gymmanagement.interfaces.EquipmentService;
import com.framja.gymmanagement.model.Equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipmentServiceImpl implements EquipmentService {
    private final List<Equipment> equipmentList;

    public EquipmentServiceImpl() {
        this.equipmentList = new ArrayList<>();
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return new ArrayList<>(equipmentList);
    }

    @Override
    public Optional<Equipment> getEquipmentById(int id) {
        return equipmentList.stream()
                .filter(equipment -> equipment.getId() == id)
                .findFirst();
    }

    @Override
    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }

    @Override
    public void updateEquipment(Equipment equipment) {
        getEquipmentById(equipment.getId()).ifPresent(existingEquipment -> {
            equipmentList.remove(existingEquipment);
            equipmentList.add(equipment);
        });
    }

    @Override
    public void removeEquipment(int id) {
        getEquipmentById(id).ifPresent(equipmentList::remove);
    }

    @Override
    public int getTotalEquipmentCount() {
        return equipmentList.stream()
                .mapToInt(Equipment::getQuantity)
                .sum();
    }
}
