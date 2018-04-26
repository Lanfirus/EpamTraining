package ua.training.model.dao;

import java.util.List;
import java.util.Map;

public interface GeneralDAO {
    void create(Map<String, String> map);
    Map<String, String> findById(int id);
    List<Map<String, String>> findAll();
    void update(Map<String, String> map);
    void delete(int id);
}

