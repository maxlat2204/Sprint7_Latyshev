package praktikum.courier;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.courier.CourierModel;
import model.courier.LoginModel;
import org.junit.*;
import praktikum.BaseApiTest;
import steps.CourierSteps;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static praktikum.EnvConfig.COURIER_FIRST_NAME;
import static praktikum.EnvConfig.COURIER_LOGIN;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.loginCourier;


public class CreateCourierTest extends BaseApiTest {

    private int courierId;
    CourierModel courier = CourierModel.random();

    @After
    public void delCourier(){

        if (courierId > 0){
            CourierSteps.deleteCourier(courierId);
        }
    }

    //Позитивный тест на создание курьера
    @Test
    @DisplayName("Позитивный тест на создание курьера")
    public void createCourierSuccessTest(){

        createCourier(courier)
                .then()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));

        var login = LoginModel.fromCourier(courier);
        courierId = loginCourier(login)
                .path("id");

    }

    //Негативный тест на создание двух одинаковых курьеров
    @Test
    @DisplayName("Негативный тест на создание двух одинаковых курьеров")
    public void identicalCreateCourierNegativeTest(){

        createCourier(courier)
                .then()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));

        var login = LoginModel.fromCourier(courier);
        courierId = loginCourier(login)
                .path("id");

        //Попытались создать курьера с тем же логином
        createCourier(courier)
                .then()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));

    }


    //Негативный тест на создание курьера без пароля
    @Test
    @DisplayName("Негативный тест на создание курьера без пароля")
    public void parameterMissingCreateCourierNegativeTest(){
        CourierModel courierNoValid = new CourierModel(COURIER_LOGIN, null, COURIER_FIRST_NAME);
        createCourier(courierNoValid)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

}