package config;

public class Config {

    public static final String PETSTORE_BASE_URL = "https://petstore.swagger.io/v2";

    public static final String GET_INVENTORY = "/store/inventory";

    public static final String POST_ORDER = "/store/order";

    public static final String ORDER_BY_ID = "/store/order/{orderId}";

    public static final String DELETE_BY_ID = "/store/order/{orderId}";

    public static final String CREATE_PET = "/pet";

    public static final String PET_BY_ID = "/pet/{petId}";

    public static final String FIND_BY_STATUS = "/pet/findByStatus";
}
