package ru.javawebinar.topjava.model;

public interface CRUDDao<ENTITY, KEY> {

    KEY save(ENTITY entity);

    boolean update(ENTITY entity);

    boolean delete(KEY id);

    ENTITY getById(KEY id);


}
