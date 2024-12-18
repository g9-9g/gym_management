package com.framja.gymmanagement.interfaces;

public interface IManageable<T> {
    void create(T entity);
    T read(int id);
    void update(T entity);
    void delete(int id);
}
