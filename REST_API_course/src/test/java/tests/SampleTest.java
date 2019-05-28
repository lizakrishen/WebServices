package tests;

import com.google.common.collect.ImmutableList;
import endPoint.PetStorePetEndPoint;
import endPoint.StoreEndPoint;
import io.restassured.response.Response;
import model.Category;
import model.Pet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class SampleTest {


    public static final StoreEndPoint STORE_END_POINT = new StoreEndPoint();

    public static final PetStorePetEndPoint PET_STORE_PET_END_POINT = new PetStorePetEndPoint();

    @Before
    public void cleanup() {
        PET_STORE_PET_END_POINT
            .getPetByStatus("available")
            .body()
            .jsonPath().getList("findAll {item -> item.name == 'Barsik'}", Pet.class)
                .stream()
                .forEach(pet -> PET_STORE_PET_END_POINT.deletePetById(pet.getId()));
    }

    @Test
    public void verifyOrderIdByStatus() {
        STORE_END_POINT.getOrderById("1")
                .then()
                .statusCode(200);
    }

    @Test
    public void verifyStatusCode() {
        PET_STORE_PET_END_POINT
                .getPetByStatus("available")
                .then()
                .statusCode(200);
    }

    @Test
    public void verifyBody() {
        PET_STORE_PET_END_POINT
                .getPetByStatus("available")
                .then()
                .assertThat()
                .body(is(not(isEmptyOrNullString())));
    }

    @Test
    public void verifyPetCanBeCreated() {
        Category category = new Category();
        category.setId(123123);
        category.setName("Cats");

        Pet pet = new Pet();
        pet.setName("Barsik");
        pet.setCategory(category);
        pet.setphotoUrls(ImmutableList.of("SomeURL"));
        pet.setStatus("available");

        PET_STORE_PET_END_POINT
                .createPet(pet)
                .then().statusCode(200);
    }

    @Test
    public void verifyNotExistingPetReturns404() {
        PET_STORE_PET_END_POINT
                .getPetById("78745852")
                .then()
                .statusCode(404);
    }

    @Test
    public void verifyBarsikHasIDAfterCreation() {
        Pet barsik = Pet.createBarsik();

        Response petResponse = PET_STORE_PET_END_POINT
                .createPet(barsik);

        Pet petFromService = petResponse.body().as(Pet.class);
        Assert.assertNotNull(petFromService.getId());
    }
}
