package ua.training.model.utils;


import ua.training.constants.Constants;
import ua.training.model.exception.NotUniqueLoginException;

import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;

public class SQLInteraction {

    private final static SQLInteraction instance = new SQLInteraction();
    private Connection connection;
    private static ResourceBundle bundle = ResourceBundle.getBundle(Constants.DB_PROPERTY_NAME);




    public static String getUrlDefault() {
        return bundle.getString("URL_DEFAULT");
    }

    public static String getUrlCustom() {
        return bundle.getString("URL_CUSTOM");
    }

    public static String getNAME() {
        return bundle.getString("NAME");
    }

    public static String getPASSWORD() {
        return bundle.getString("PASSWORD");
    }

    public Connection getDefaultConnection() throws SQLException{
        setDefaultConnectionToDB();
        return connection;
    }

    public Connection getCustomConnection(){
        try {
            setCustomConnectionToDB();
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return connection;
    }

    public Connection getValueOfConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDefaultConnectionToDB() throws SQLException {
        setConnection(DriverManager.getConnection(getUrlDefault(), getNAME(), getPASSWORD()));
    }

    public void setCustomConnectionToDB() throws SQLException{
        setConnection(DriverManager.getConnection(getUrlCustom(), getNAME(), getPASSWORD()));
    }

    public void closeConnectionToDB() throws SQLException{
        getValueOfConnection().close();
    }

    private void createDB() throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(Constants.CREATE_DB_STATEMENT);
    }

    private void dropDB() throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(Constants.DROP_DB_STATEMENT);
    }

    private void createTables() throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(Constants.CREATE_USER_TABLE_STATEMENT);
        statement.executeUpdate(Constants.CREATE_PREMADE_ORDER_TABLE_STATEMENT);
    }

    public void executeSelectQuery(String query) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }

    public String executeGetRoleQuery(String login, String password) throws SQLException{
        Statement statement = connection.createStatement();
        String query = concatIsUserExistQuery(login, password);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getString(Constants.ROLE);
    }

    public String executeGetFullNameQuery(String login, String password) throws SQLException{
        Statement statement = connection.createStatement();
        String query = concatIsUserExistQuery(login, password);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getString(Constants.NAME) + " " + resultSet.getString(Constants.SURNAME);
    }

    public boolean executeIsUserExistQuery(String login, String password) throws SQLException{
        Statement statement = connection.createStatement();
        String query = concatIsUserExistQuery(login, password);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getString(Constants.ID) != null;
    }

    private String concatIsUserExistQuery(String login, String password){
        String query = Constants.QUERY_USER_RECORD_STATEMENT + "login = '" + login + "' and password = '" + password + "';";
        return query;
    }

    private void insertUserRecord(String record)throws SQLException{
        setCustomConnectionToDB();
        Statement statement = connection.createStatement();
        statement.executeUpdate(record);
    }

    public void initializeDB() throws SQLException{
        getDefaultConnection();
        createDB();
        closeConnectionToDB();
        getCustomConnection();
        createTables();
        insertUserRecord(Constants.INSERT_USER_RECORD_STATEMENT + Constants.INSERT_USER_RECORD1_VALUE);
        insertUserRecord(Constants.INSERT_USER_RECORD_STATEMENT + Constants.INSERT_USER_RECORD2_VALUE);
        insertUserRecord(Constants.UPDATE_USER_RECORD2_STATEMENT);
    }

    public void deInitializeDB() throws SQLException{
        getDefaultConnection();
        System.out.println("done");
        dropDB();
        closeConnectionToDB();
        System.out.println("done");
    }

    public void insertUserRecord(Map<String, String> userData) throws NotUniqueLoginException {
        String preparedData = prepareValueForRecordInsertion(userData);
        try {
            insertUserRecord(Constants.INSERT_USER_RECORD_STATEMENT + preparedData);
        }
        catch(SQLException e){
            throw new NotUniqueLoginException(e.getMessage());
        }
    }

    public void insertOrderRecord(Map<String, String> userData){
        String preparedData = prepareValueForRecordInsertion(userData);
        try {
            insertUserRecord(Constants.INSERT_PREMADE_ORDER_RECORD_STATEMENT + preparedData);
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private String prepareValueForRecordInsertion(Map<String, String> data) {
        StringBuilder builder = new StringBuilder();
        builder.append("VALUES (");
        int counter = 0;
        for (Map.Entry<String, String> field : data.entrySet()) {
            if (counter == 0) {
                builder.append("\'" + field.getValue() + "\'");
                counter++;
            }
            else {
                builder.append(", \'" + field.getValue() + "\'");
            }
        }
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    public static SQLInteraction getInstance(){
        return instance;
    }
}
