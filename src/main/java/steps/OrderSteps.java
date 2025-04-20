package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.order.OrderModel;
import praktikum.EndPoints;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    private static RequestSpecification spec() {
        return given()
                .log().all()
                .contentType(ContentType.JSON);
    }

    @Step("Создать заказ")
    public static ValidatableResponse createOrder(OrderModel order) {
        return spec()
                .body(order)
                .when()
                .post(EndPoints.ORDER_CREATE_PATH)
                .then().log().all();
    }

    @Step("Отменить заказ")
    public static Response cancelOrder(int trackOrder) {
        return spec()
                .body(Map.of("track", trackOrder))
                .when()
                .put(EndPoints.ORDER_CANCEL_PATH)
                .then().log().all()
                .extract().response();
    }

    @Step("Получить лист заказов")
    public static ValidatableResponse getListOrder() {
        return spec()
                .when()
                .get(EndPoints.ORDER_CREATE_PATH)
                .then().log().all();
    }
}
