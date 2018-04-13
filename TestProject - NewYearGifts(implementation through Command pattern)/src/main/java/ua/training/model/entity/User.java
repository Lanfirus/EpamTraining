package ua.training.model.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class User {

    private Map<String, String> userData;

    public User(){
        this.userData = new LinkedHashMap<>();
    }

    public User(Map<String, String> userData){
        this.userData = userData;
    }

    public Map<String, String> getUserData() {
        return userData;
    }

    public String getName(){
        return userData.get("name");
    }

    public String getSurame(){
        return userData.get("surname");
    }

    public String getPatronymic(){
        return userData.get("patronymic");
    }

    public String getLogin(){
        return userData.get("login");
    }

    public String getPassword(){
        return userData.get("password");
    }

    public String getComment(){
        return userData.get("comment");
    }

    public String getHomePhoneNumber(){
        return userData.get("homePhoneNumber");
    }

    public String getMobilePhoneNumber(){
        return userData.get("mobilePhoneNumber");
    }

    public String getEmail(){
        return userData.get("email");
    }

    public void setUserField(String userField, String value){
        userData.put(userField, value);
    }
}
