package com.cookbook.testtask.controllers;

import com.cookbook.testtask.models.Recipe;
import com.cookbook.testtask.repositories.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    @Autowired
    private RecipeRepo recipeRepo;

    @GetMapping("/")
    public String main(Model model){
        Iterable<Recipe> recipes = recipeRepo.findAll();
        model.addAttribute("recipes", recipes);
        return "main";
    }

    @GetMapping("{recipe}")
    public String recipe(@PathVariable Recipe recipe,
                         Model model){
        model.addAttribute("recipe", recipe);
        return "recipe";
    }

}
