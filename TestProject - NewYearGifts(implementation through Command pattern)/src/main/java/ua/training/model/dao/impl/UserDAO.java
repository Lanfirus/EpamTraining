package ua.training.model.dao.impl;

import ua.training.constants.Constants;
import ua.training.model.dao.GeneralDAO;
import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueLoginException;
import ua.training.model.utils.SQLInteraction;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserDAO implements GeneralDAO {

    private SQLInteraction sqlInteraction = SQLInteraction.getInstance();
    private Connection connection = sqlInteraction.getCustomConnection();

    @Override
    public void create(Map<String, String> map) throws NotUniqueLoginException{
        sqlInteraction.insertUserRecord(map);
    }

    @Override
    public Map<String, String> findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            set.next();
            return extractDataFromResultSet(set);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> findByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM users WHERE login = ?")) {
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();
            set.next();
            return extractDataFromResultSet(set);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, String>> findAll() {
        List<Map<String, String>> resultList = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet set = statement.executeQuery("SELECT * FROM users");

            while ( set.next() ){
                Map<String, String> result = extractDataFromResultSet(set);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET name = ? , surname = ? , patronymic = ? , login = ? , password = ? , comment = ? ," +
                        "homePhoneNumber = ? , mobilePhoneNumber = ? , email = ? WHERE login = ?")){
            statement.setString(1 , user.getName());
            statement.setString(2 , user.getSurame());
            statement.setString(3, user.getPatronymic());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getComment());
            statement.setString(7, user.getHomePhoneNumber());
            statement.setString(8, user.getMobilePhoneNumber());
            statement.setString(9, user.getEmail());
            statement.setString(10, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users  WHERE id = ?")){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users  WHERE login = ? and password = ?")){
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> extractDataFromResultSet(ResultSet set) throws SQLException{
        Map<String, String> map = new LinkedHashMap<>();
        map.put(Constants.NAME, set.getString(Constants.NAME));
        map.put(Constants.SURNAME, set.getString(Constants.SURNAME));
        map.put(Constants.PATRONYMIC, set.getString(Constants.PATRONYMIC));
        map.put(Constants.LOGIN, set.getString(Constants.LOGIN));
        map.put(Constants.PASSWORD, set.getString(Constants.PASSWORD));
        map.put(Constants.COMMENT, set.getString(Constants.COMMENT));
        map.put(Constants.HOME_PHONE_NUMBER, set.getString(Constants.HOME_PHONE_NUMBER));
        map.put(Constants.MOBILE_PHONE_NUMBER, set.getString(Constants.MOBILE_PHONE_NUMBER));
        map.put(Constants.EMAIL, set.getString(Constants.EMAIL));
        return map;
    }
}
