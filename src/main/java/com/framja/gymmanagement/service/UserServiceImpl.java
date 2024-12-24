package com.framja.gymmanagement.service;

import com.framja.gymmanagement.interfaces.UserService;
import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        if (users.stream().anyMatch(existingUser -> existingUser.getUsername().equals(user.getUsername()))) {
            throw new IllegalArgumentException("User with username " + user.getUsername() + " already exists.");
        }
        users.add(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    @Override
    public boolean removeUser(String username) {
        return users.removeIf(user -> user.getUsername().equals(username));
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public <T> List<T> getAllUsersByRole(Class<T> roleType) {
        return users.stream()
                .filter(roleType::isInstance)
                .map(roleType::cast)
                .toList();
    }

    @Override
    public boolean updateMember(Member updatedMember) {
        Optional<User> existingUserOptional = findUserByUsername(updatedMember.getUsername());
        if (existingUserOptional.isPresent() && existingUserOptional.get() instanceof Member existingMember) {
            existingMember.setPassword(updatedMember.getPassword());
            existingMember.setPhoneNumber(updatedMember.getPhoneNumber());
            existingMember.setAddress(updatedMember.getAddress());
            existingMember.setGender(updatedMember.getGender());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTrainer(Trainer updatedTrainer) {
        Optional<User> existingUserOptional = findUserByUsername(updatedTrainer.getUsername());
        if (existingUserOptional.isPresent() && existingUserOptional.get() instanceof Trainer existingTrainer) {
            // Cập nhật từng thuộc tính
            existingTrainer.setPassword(updatedTrainer.getPassword());
            existingTrainer.setPhoneNumber(updatedTrainer.getPhoneNumber());
            existingTrainer.setAddress(updatedTrainer.getAddress());
            existingTrainer.setGender(updatedTrainer.getGender());
            existingTrainer.setSalary(updatedTrainer.getSalary());
            existingTrainer.setSpecialization(updatedTrainer.getSpecialization());
            return true;
        }
        return false;
    }

}
