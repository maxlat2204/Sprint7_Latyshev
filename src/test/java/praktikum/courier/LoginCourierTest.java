package praktikum.courier;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.courier.CourierModel;
import model.courier.LoginModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.BaseApiTest;
import steps.CourierSteps;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static praktikum.EnvConfig.*;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.loginCourier;

public class LoginCourierTest extends BaseApiTest {

//    private int courierId;

    @Before
    //Создаем курьера
    public void toCreateCourier(){
        createCourier(courierStatic);
    }

    @After
    //Удаляем курьера
    public void delCourier(){
        //Авторизируемся чтобы подчистить за собой
        var login = LoginModel.fromCourier(courierStatic);
        courierId = loginCourier(login)
                .path("id");

//        if (courierId > 0){
//            CourierSteps.deleteCourier(courierId);
//        }
    }

    CourierModel courierStatic = CourierModel.random();


    @Test
    @DisplayName("Позитивный тест на авторизацию курьера")
    public void loginCourierSuccessTest(){

        var login = LoginModel.fromCourier(courierStatic);
        courierId = loginCourier(login)
                .then()
                .statusCode(HTTP_OK)
                .body("id", notNullValue())
                .extract()
                .path("id");

//        assertNotNull(courierId);
    }


    @Test
    @DisplayName("Негативный тест на авторизацию c несуществующим логином")
    //Запрос c несуществующим логином:
    public void loginCourierNegativeTest(){

        //Логинимся с некоректным логином
        var loginNotСorrect = new LoginModel(COURIER_LOGIN+2, COURIER_PASSWORD);

        loginCourier(loginNotСorrect)
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Негативный тест на авторизацию c несуществующим паролем")
    //Запрос c несуществующей парой логин-пароль:
    public void passwordCourierNegativeTest(){

        //Логинимся с несуществующим паролем
        var loginNotСorrect = new LoginModel(COURIER_LOGIN, COURIER_PASSWORD+2);

        loginCourier(loginNotСorrect)
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));

    }


    @Test
    @DisplayName("Негативный тест на авторизацию без логина")
    //Запрос без логина:
    public void loginMissingLoginCourierNegativeTest(){

        //Логинимся с некоректным логином
        var loginNotLogin = new LoginModel(null, COURIER_PASSWORD);
        loginCourier(loginNotLogin)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));

    }


    @Test
    @DisplayName("Негативный тест на авторизацию без пароля")
    //Запрос без пароля:
    public void passwordMissingLoginCourierNegativeTest(){

        //Логинимся с некоректным логином
        var loginNotLogin = new LoginModel(COURIER_LOGIN, null);
        loginCourier(loginNotLogin)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

}
