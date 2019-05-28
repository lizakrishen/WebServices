package endPoint;

import config.Config;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class StoreEndPoint {

    private RequestSpecification givenBase() {
        return given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL);
    }
    public Response getOrderById(String id) {
        return givenBase()
                .pathParam("orderId", id)
                .when()
                .get(Config.ORDER_BY_ID)
                .then()
                .extract().response();
    }
}
