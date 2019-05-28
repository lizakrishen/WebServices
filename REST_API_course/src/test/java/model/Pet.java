package model;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class Pet {

    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getphotoUrls() {
        return photoUrls;
    }

    public void setphotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Pet createBarsik() {
        Category category = new Category();
        category.setId(123123);
        category.setName("Cats");

        Pet pet = new Pet();
        pet.setName("Barsik");
        pet.setphotoUrls(ImmutableList.of("SomeURL"));
        pet.setCategory(category);
        pet.setStatus("available");
        return pet;
    }
}
