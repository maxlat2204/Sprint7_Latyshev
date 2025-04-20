package praktikum.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.order.OrderModel;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.BaseApiTest;
import steps.OrderSteps;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.cancelOrder;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseApiTest {

    private final List<String> color;
    private int trackOrder;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }


    @Parameterized.Parameters(name = "color = {0}")
    public static Object[][] data(){
        return new Object[][]{
                {List.of("GRAY", "BLACK")},
                {List.of("GRAY")},
                {List.of("BLACK")},
                {List.of()}
        };
    }


    @Test
    @DisplayName("Позитивный тест на создание заказа")
    @Description("Этот тест Параметризованный")
    public void createOrderSuccessTest(){

        OrderModel order = OrderModel.newOrder(color);

        trackOrder = OrderSteps.createOrder(order)
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
                .extract()
                .path("track");

    }

    @After
    public void canceOrder(){
        OrderSteps.cancelOrder(trackOrder);
    }

}
