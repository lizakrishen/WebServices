package tests;

import io.restassured.response.Response;
import model.Pet;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.assertj.core.api.SoftAssertions;

import static tests.SampleTest.PET_STORE_PET_END_POINT;

public class CrudTests {
    @BeforeClass
    public static void cleanup() {
        PET_STORE_PET_END_POINT
                .getPetByStatus("available")
                .body()
                .jsonPath().getList("findAll {item -> item.name == 'Barsik'}", Pet.class)
                .stream()
                .forEach(pet -> PET_STORE_PET_END_POINT.deletePetById(pet.getId()));
    }

    @Test
    public void createPet() {
        Pet barsik = Pet.createBarsik();

        Response petResponse = PET_STORE_PET_END_POINT.createPet(barsik);

        long createdPetId = petResponse.body().as(Pet.class).getId();
        Pet cteatedPetFromService = PET_STORE_PET_END_POINT.getPetById(String.valueOf(createdPetId)).as(Pet.class);

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(cteatedPetFromService.getName()).as("Name").isEqualTo(barsik.getName());
        assertions.assertThat(cteatedPetFromService.getStatus()).as("Name").isEqualTo(barsik.getStatus());
        assertions.assertAll();
    }

    @Test
    public void updatePet() {
        Pet barsik = Pet.createBarsik();
        PET_STORE_PET_END_POINT.createPet(barsik);
        barsik.setStatus("sold");

        Pet cteatedPetFromService = PET_STORE_PET_END_POINT.updatePet(barsik).as(Pet.class);

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(cteatedPetFromService.getName()).as("Name").isEqualTo(barsik.getName());
        assertions.assertThat(cteatedPetFromService.getStatus()).as("Name").isEqualTo(barsik.getStatus());
        assertions.assertAll();
    }

    @Test
    public void readPet() {
        Pet barsik = Pet.createBarsik();
        PET_STORE_PET_END_POINT.createPet(barsik);

        Pet cteatedPetFromService = PET_STORE_PET_END_POINT.updatePet(barsik).as(Pet.class);

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(cteatedPetFromService.getName()).as("Name").isEqualTo(barsik.getName());
        assertions.assertThat(cteatedPetFromService.getStatus()).as("Name").isEqualTo(barsik.getStatus());
        assertions.assertAll();
    }

    @Test
    public void deletePet() {
        Pet barsik = Pet.createBarsik();
        PET_STORE_PET_END_POINT.createPet(barsik);
        Pet createdPetFromService = PET_STORE_PET_END_POINT.updatePet(barsik).body().as(Pet.class);

        PET_STORE_PET_END_POINT.deletePetById(createdPetFromService.getId());

        Response petById = PET_STORE_PET_END_POINT.getPetById(String.valueOf(createdPetFromService.getId()));
        Assertions.assertThat(petById.getStatusCode()).isEqualTo(404);
    }
}
