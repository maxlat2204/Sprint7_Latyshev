package praktikum;

import io.restassured.RestAssured;
import model.courier.CourierModel;
import model.courier.LoginModel;
import org.junit.After;
import org.junit.Before;
import steps.CourierSteps;

import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.loginCourier;

public class BaseApiTest {
    protected int courierId;
//    protected boolean addCourier;


    @Before
    public void setUp(){
        //базовый URL на который будем отправлять запрос
        RestAssured.baseURI = EnvConfig.BASE_URL;

    }

    @After
    public void cleanUp(){
//        CourierModel courier = CourierModel.random();
//        if (addCourier == true) {
//            var login = LoginModel.fromCourier(courier);
//            courierId = loginCourier(login)
//                    .path("id");
//        } НЕ ЗНАЮ КАК ПРАВИЛЬНО ПЕРЕМЕСТИТЬ ФАЗУ ЛОГИН В
//        МЕТОД АФТЕР ЧТОБЫ loginMissingCreateCourierNegativeTest() НЕ ВЫДАВАЛ ОШИБКУ


        if (courierId > 0){
            CourierSteps.deleteCourier(courierId);
        }
    }
}
