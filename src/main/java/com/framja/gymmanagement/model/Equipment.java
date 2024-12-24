package com.framja.gymmanagement.model;

import java.time.LocalDate;
import java.util.Objects;

public class Equipment {
    private int id;                     // ID của thiết bị
    private String name;                // Tên thiết bị
    private String description;         // Mô tả thiết bị
    private String category;            // Loại thiết bị (e.g., Cardio, Strength)
    private String brand;               // Thương hiệu
    private double price;               // Giá thiết bị
    private int quantity;               // Số lượng thiết bị
    private String location;            // Vị trí trong phòng gym
    private String status;              // Trạng thái (Available, Maintenance)
    private LocalDate purchaseDate;     // Ngày mua
    private LocalDate warrantyExpiry;   // Ngày hết hạn bảo hành

    public Equipment(int id, String name, String description, String category, String brand, double price, int quantity, String location, String status, LocalDate purchaseDate, LocalDate warrantyExpiry) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.location = location;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.warrantyExpiry = warrantyExpiry;
    }

    public Equipment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getWarrantyExpiry() {
        return warrantyExpiry;
    }

    public void setWarrantyExpiry(LocalDate warrantyExpiry) {
        this.warrantyExpiry = warrantyExpiry;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Equipment equipment = (Equipment) object;
        return id == equipment.id && Double.compare(price, equipment.price) == 0 && quantity == equipment.quantity && Objects.equals(name, equipment.name) && Objects.equals(description, equipment.description) && Objects.equals(category, equipment.category) && Objects.equals(brand, equipment.brand) && Objects.equals(location, equipment.location) && Objects.equals(status, equipment.status) && Objects.equals(purchaseDate, equipment.purchaseDate) && Objects.equals(warrantyExpiry, equipment.warrantyExpiry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, brand, price, quantity, location, status, purchaseDate, warrantyExpiry);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", warrantyExpiry=" + warrantyExpiry +
                '}';
    }
}
