package ua.training.dao;

import ua.training.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLInteraction {

    private final static String URL_DEFAULT = "jdbc:mysql://localhost/mysql";
    private final static String URL_CUSTOM = "jdbc:mysql://localhost/training?characterEncoding=utf8";
    private final static String NAME = "admin";
    private final static String PASSWORD = "admin";
    private Connection connection;
    private final static String CREATE_DB_STATEMENT =
            "CREATE DATABASE training CHARACTER SET utf8 COLLATE utf8_general_ci";
    private final static String DROP_DB_STATEMENT = "drop database `training`";
    private final static String CREATE_TABLE_STATEMENT = "CREATE TABLE `users` (" +
            "  `id` int(10) NOT NULL auto_increment," +
            "  `name` varchar(50) NOT NULL," +
            "  `surname` varchar(50) NOT NULL," +
            "  `patronymic` varchar(50) NULL," +
            "  `login` varchar(50) NOT NULL , UNIQUE INDEX `idnew_table_UNIQUE` (`login` ASC)," +
            "  `password` varchar(50) NOT NULL," +
            "  `comment` varchar(100) NULL," +
            "  `home_tel_number` varchar(12) NULL," +
            "  `mobile_tel_number` varchar(12) NOT NULL," +
            "  `email` varchar(100) NOT NULL," +
            "  `role` VARCHAR(45) NOT NULL DEFAULT 'user'," +
            "  PRIMARY KEY  (`id`)" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    private final static String INSERT_RECORD_STATEMENT = "INSERT INTO `users` (`name`,`surname`,`patronymic`,`login`,`password`,`comment`,`home_tel_number`,`mobile_tel_number`,`email`)";
    private final static String INSERT_RECORD1_VALUE = " VALUES ('Petr', 'Petrov','Petrovich','Petrov112','Petrov_the_best','Petrovich is the best!', '380441234567', '380441234567', 'petrovich@ua.ua');";
    private final static String INSERT_RECORD2_VALUE = " VALUES ('Ivan', 'Ivanov', '','Ivanych','12345','nothing to comment','380501234567', '380631234567', 'callToIvanych@i.ua');";
    private final static String UPDATE_RECORD2_STATEMENT = "UPDATE `users` SET `role`='admin' WHERE `id`='1';";
    private final static String QUERY_RECORD_STATEMENT = "SELECT * FROM `users` where ";




    public static String getUrlDefault() {
        return URL_DEFAULT;
    }

    public static String getUrlCustom() {
        return URL_CUSTOM;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public Connection getDefaultConnection() throws SQLException{
        setDefaultConnectionToDB();
        return connection;
    }

    public Connection getCustomConnection() throws SQLException{
        setCustomConnectionToDB();
        return connection;
    }

    public Connection getValueOfConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    /**
     * Emulates working with DB with some data already written in.
     * To do so write several records into user before actual program activities.
     * Does check for unique logins of users.
     *
     */
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
        statement.executeUpdate(CREATE_DB_STATEMENT);
    }

    private void dropDB() throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(DROP_DB_STATEMENT);
    }

    private void createTable() throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_TABLE_STATEMENT);
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
        return resultSet.getString("role");
    }

    public boolean executeIsUserExistQuery(String login, String password) throws SQLException{
        Statement statement = connection.createStatement();
        String query = concatIsUserExistQuery(login, password);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        if (resultSet.getString("id") != null) {
            return true;
        }
        return false;
    }

    private String concatIsUserExistQuery(String login, String password){
        String query = QUERY_RECORD_STATEMENT + "login = '" + login + "' and password = '" + password + "';";
        return query;
    }

    private void insertRecord(String record)throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(record);
    }

    public void initializeDB() throws SQLException{
        getDefaultConnection();
        createDB();
        closeConnectionToDB();
        getCustomConnection();
        createTable();
        insertRecord(INSERT_RECORD_STATEMENT + INSERT_RECORD1_VALUE);
        insertRecord(INSERT_RECORD_STATEMENT + INSERT_RECORD2_VALUE);
        insertRecord(UPDATE_RECORD2_STATEMENT);
    }

    public void deInitializeDB() throws SQLException{
        getDefaultConnection();
        dropDB();
        closeConnectionToDB();
    }

    public void insertRecord(Map<String, String> userData) throws NotUniqueLoginException {
        String preparedData = prepareValueForRecordInsertion(userData);
        try {
            insertRecord(INSERT_RECORD_STATEMENT + preparedData);
        }
        catch(SQLException e){
            throw new NotUniqueLoginException(e.getMessage());
        }
    }

    /*private List<String> convertUserDataToList(Map<String, String> userData){
        List<String> userDataInList = new ArrayList<>();
        userDataInList.add(userData.get("name"));
        userDataInList.add(userData.get("surname"));
        userDataInList.add(userData.get("patronymic"));
        userDataInList.add(userData.get("login"));
    }*/

    private String prepareValueForRecordInsertion(Map<String, String> userData) {
        StringBuilder builder = new StringBuilder();
        builder.append("VALUES (");
        int counter = 0;
        for (Map.Entry<String, String> field : userData.entrySet()) {
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
}
