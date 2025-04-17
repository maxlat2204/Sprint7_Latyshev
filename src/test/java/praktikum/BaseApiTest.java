package praktikum;

import io.restassured.RestAssured;
import org.junit.Before;

public class BaseApiTest {

    @Before
    public void setUp(){
        //базовый URL на который будем отправлять запрос
        RestAssured.baseURI = EnvConfig.BASE_URL;

    }

//    @After
//    public void cleanUp(){
//
//        if (courierTest.getCourierId() > 0){
//            CourierSteps.deleteCourier(courierTest.getCourierId());
//        }
//    }
}
