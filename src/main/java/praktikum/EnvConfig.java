package praktikum;

public class EnvConfig {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";

    //генерируем уникальные данные для тестового курьера(System.currentTimeMillis() для уникальности)
    public static final String COURIER_LOGIN = "Maksim" + System.currentTimeMillis();
    public static final String COURIER_PASSWORD = "1234";
    public static final String  COURIER_FIRST_NAME = "skiman";

    //данные для Order
    public static final String ORDER_FIRST_NAME = "Максим";
    public static final String ORDER_LAST_NAME = "Лат";
    public static final String ORDER_ADDRESS = "Усачева 3";
    public static final String ORDER_METRO_STATION = "Сокольники";
    public static final String ORDER_PHONE = "+79001112233";
    public static final int ORDER_RENT_TIME = 5;
    public static final String ORDER_DATE = "2025-06-06";
    public static final String ORDER_COMMENT = "Saske, come back to Konoha";
}
