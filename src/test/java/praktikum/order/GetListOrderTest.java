package praktikum.order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import praktikum.BaseApiTest;
import steps.OrderSteps;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetListOrderTest extends BaseApiTest {

    @Test
    @DisplayName("Позитивный тест на Получение списка заказов")
    public void getListOrderTest(){

        OrderSteps.getListOrder()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue());

    }
}
