package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.order.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    public static final String ORDER_CREATE_PATH = "/api/v1/orders";

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
                .post(ORDER_CREATE_PATH)
                .then().log().all();
    }

    @Step("Получить лист заказов")
    public static ValidatableResponse getListOrder() {
        return spec()
                .when()
                .get(ORDER_CREATE_PATH)
                .then().log().all();
    }
}
