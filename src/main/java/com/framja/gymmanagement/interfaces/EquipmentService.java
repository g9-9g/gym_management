package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    List<Equipment> getAllEquipment(); // Lấy danh sách tất cả thiết bị
    Optional<Equipment> getEquipmentById(int id); // Lấy thiết bị theo ID
    void addEquipment(Equipment equipment); // Thêm thiết bị mới
    void updateEquipment(Equipment equipment); // Cập nhật thiết bị
    void removeEquipment(int id); // Xóa thiết bị
    int getTotalEquipmentCount(); // Tổng số lượng tất cả thiết bị
}
