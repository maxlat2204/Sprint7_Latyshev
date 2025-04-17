package model.courier;

import praktikum.EnvConfig;

import static praktikum.EnvConfig.*;
import static praktikum.EnvConfig.COURIER_FIRST_NAME;

public class CourierModel {
    //создали поля ручки post api/v1/courier
    private String login;
    private String password;
    private String firstName;

    public CourierModel(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static CourierModel random(){
        return new CourierModel(COURIER_LOGIN, COURIER_PASSWORD, COURIER_FIRST_NAME);
    }

    public CourierModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
