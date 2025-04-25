package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.courier.CourierModel;
import model.courier.LoginModel;
import praktikum.EndPoints;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CourierSteps {

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
                .post(EndPoints.COURIER_CREATE_PATH)
                .then().log().all()
                .extract().response();
    }

    @Step("Авторизоваться(залогинить) курьера")
    public static Response loginCourier(LoginModel login){
        return spec()
                .body(login)
                .when()
                .post(EndPoints.COURIER_LOGIN_PATH)
                .then().log().all()
                .extract().response();
    }

    @Step("Удалить курьера")
    public static Response deleteCourier(int id){
        return spec()
                .body(Map.of("id", id))
                .when()
                .delete(EndPoints.COURIER_DELETE_PATH + id)
                .then().log().all()
                .extract().response();
    }
}
