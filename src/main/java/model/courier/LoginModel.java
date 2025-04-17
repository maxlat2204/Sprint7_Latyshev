package model.courier;

public class LoginModel {
    private String login;
    private String password;

    public LoginModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginModel fromCourier(CourierModel courier){
        return new LoginModel(courier.getLogin(), courier.getPassword());
    }

    public LoginModel() {
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
}
