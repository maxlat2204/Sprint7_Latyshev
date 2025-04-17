package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.courier.CourierModel;
import model.courier.LoginModel;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CourierSteps {

    public static final String COURIER_CREATE_PATH = "/api/v1/courier";
    public static final String COURIER_LOGIN_PATH = "/api/v1/courier/login";
    public static final String COURIER_DELETE_PATH = "/api/v1/courier/";

    private static RequestSpecification spec() {
        return given()
                .log().all()
                .contentType(ContentType.JSON);
    }

    @Step("Создать курьера")
    public static Response createCourier(CourierModel courier){
        return spec()
                .body(courier)
                .when()
                .post(COURIER_CREATE_PATH)
                .then().log().all()
                .extract().response();
    }

    @Step("Авторизоваться(залогинить) курьера")
    public static Response loginCourier(LoginModel login){
        return spec()
                .body(login)
                .when()
                .post(COURIER_LOGIN_PATH)
                .then().log().all()
                .extract().response();
    }

    @Step("Удалить курьера")
    public static Response deleteCourier(int id){
        return spec()
                .body(Map.of("id", id))
                .when()
                .delete(COURIER_DELETE_PATH + id)
                .then().log().all()
                .extract().response();
    }
}
