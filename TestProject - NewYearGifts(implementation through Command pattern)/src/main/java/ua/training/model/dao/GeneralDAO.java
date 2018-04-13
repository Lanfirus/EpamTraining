package ua.training.model.dao;

import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueLoginException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GeneralDAO {
    void create(Map<String, String> map) throws NotUniqueLoginException;
    Map<String, String> findById(int id) throws SQLException;
    List<Map<String, String>> findAll();
    void update(User user);
    void delete(int id);
}

