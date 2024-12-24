package com.framja.gymmanagement.interfaces;

import com.framja.gymmanagement.model.User;
import com.framja.gymmanagement.role.Member;
import com.framja.gymmanagement.role.Trainer;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);
    Optional<User> findUserByUsername(String username);
    boolean removeUser(String username);
    List<User> findAllUsers();
    <T> List<T> getAllUsersByRole(Class<T> roleType);
    boolean updateMember(Member updatedMember); // Cập nhật thông tin Member
    boolean updateTrainer(Trainer updatedTrainer); // Cập nhật thông tin Trainer
}
