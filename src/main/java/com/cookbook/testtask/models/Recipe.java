package com.cookbook.testtask.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String recipeName;
    private String description;
    private String ingredients;
    private String cookingAlgorithm;
    private Double rating= Double.valueOf(0);
    @ElementCollection(targetClass = RecipeCategory.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"))
    @Enumerated(EnumType.STRING)
    private Set<RecipeCategory> categories;

    public Recipe() {
    }

    public Recipe(String recipeName, String description) {
        this.recipeName = recipeName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCookingAlgorithm() {
        return cookingAlgorithm;
    }

    public void setCookingAlgorithm(String cookingAlgorithm) {
        this.cookingAlgorithm = cookingAlgorithm;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Set<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<RecipeCategory> categories) {
        this.categories = categories;
    }

    public List<String> ingredientsAsList(){
        return Arrays.asList(getIngredients().split("<-regex->"));
    }

    public List<String> algorithmAsList(){
        return Arrays.asList(getCookingAlgorithm().split("<-regex->"));
    }
}
