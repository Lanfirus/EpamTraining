package ua.training.constants;

public interface Constants {

    String DB_PROPERTY_NAME = "DB";
    String CREATE_DB_STATEMENT = "CREATE DATABASE training CHARACTER SET utf8 COLLATE utf8_general_ci";
    String DROP_DB_STATEMENT = "drop database `training`";
    String CREATE_USER_TABLE_STATEMENT = "CREATE TABLE `users` (" +
            "  `id` int(10) NOT NULL auto_increment," +
            "  `name` varchar(50) NOT NULL," +
            "  `surname` varchar(50) NOT NULL," +
            "  `patronymic` varchar(50) NULL," +
            "  `login` varchar(50) NOT NULL , UNIQUE INDEX `idnew_table_UNIQUE` (`login` ASC)," +
            "  `password` varchar(50) NOT NULL," +
            "  `comment` varchar(100) NULL," +
            "  `homePhoneNumber` varchar(12) NULL," +
            "  `mobilePhoneNumber` varchar(12) NOT NULL," +
            "  `email` varchar(100) NOT NULL," +
            "  `role` VARCHAR(45) NOT NULL DEFAULT 'user'," +
            "  PRIMARY KEY  (`id`)" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    String CREATE_PREMADE_ORDER_TABLE_STATEMENT = "CREATE TABLE `premade_order` (" +
            "  `id` int(10) NOT NULL auto_increment," +
            "  `login` varchar(50) NOT NULL, " +
            "  `small_box_number` varchar(100) NULL," +
            "  `medium_box_number` varchar(12) NULL," +
            "  `big_box_number` varchar(12) NOT NULL," +
            "  PRIMARY KEY  (`id`)" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    String INSERT_USER_RECORD_STATEMENT = "INSERT INTO `users` (`name`,`surname`,`patronymic`,`login`,`password`,`comment`,`homePhoneNumber`,`mobilePhoneNumber`,`email`)";
    String INSERT_USER_RECORD1_VALUE = " VALUES ('Petr', 'Petrov','Petrovich','Petrov112','Petrov_the_best','Petrovich is the best!', '380441234567', '380441234567', 'petrovich@ua.ua');";
    String INSERT_USER_RECORD2_VALUE = " VALUES ('Ivan', 'Ivanov', '','Ivanych','12345','nothing to comment','380501234567', '380631234567', 'callToIvanych@i.ua');";
    String UPDATE_USER_RECORD2_STATEMENT = "UPDATE `users` SET `role`='admin' WHERE `id`='1';";
    String QUERY_USER_RECORD_STATEMENT = "SELECT * FROM `users` where ";
    String INSERT_PREMADE_ORDER_RECORD_STATEMENT = "INSERT INTO `premade_order` (`login`,`small_box_number`,`medium_box_number`,`big_box_number`)";
    String ROLE = "role";
    String NAME = "name";
    String SURNAME = "surname";
    String PATRONYMIC = "patronymic";
    String LOGIN = "login";
    String PASSWORD = "password";
    String COMMENT = "comment";
    String HOME_PHONE_NUMBER = "homePhoneNumber";
    String MOBILE_PHONE_NUMBER = "mobilePhoneNumber";
    String EMAIL = "email";
    String ID = "id";
}
