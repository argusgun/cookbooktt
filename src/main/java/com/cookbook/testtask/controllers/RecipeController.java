package com.cookbook.testtask.controllers;

import com.cookbook.testtask.models.Recipe;
import com.cookbook.testtask.models.RecipeCategory;
import com.cookbook.testtask.repositories.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class RecipeController {
    @Autowired
    private RecipeRepo recipeRepo;

    private List<String> ingredients=new ArrayList<>();
    private List<String> algorithm=new ArrayList<>();
    private Recipe recipe;

    @GetMapping("/addrecipe")
    public String addRecipe(Model model){
        model.addAttribute("category", RecipeCategory.values());
        return "/addrecipe";
    }

    @PostMapping("/addrecipe")
    public String createRecipe(@RequestParam String name,
                               @RequestParam String description,
                               @RequestParam Map<String, String> form,
                               Model model){
        recipe = new Recipe(name, description);
        Set<String> categories = Arrays.stream(RecipeCategory.values())
                .map(RecipeCategory::name)
                .collect(Collectors.toSet());
        recipe.setCategories(new HashSet<>());
        for (String key : form.keySet()) {
            if (categories.contains(key)) {
                recipe.getCategories().add(RecipeCategory.valueOf(key));
            }
        }
        return "/addingredients";
    }

    @GetMapping("/addingredients")
    public String addIngredients(Model model){
        model.addAttribute("ingredients",ingredients);
        model.addAttribute("algorithm", algorithm);
        model.addAttribute("recipe", recipe);
        return "/addingredients";
    }

    @PostMapping("/addingredients")
    public String get(@RequestParam(required = false, defaultValue = "") String ingredient,
                      @RequestParam(required = false, defaultValue = "") String cookingalgorithm,
                      Model model){
        if(ingredient!=null && !ingredient.isEmpty()){
            ingredients.add(ingredients.size()+1+") "+ingredient);
        }
        if(cookingalgorithm!=null && !cookingalgorithm.isEmpty()){
            algorithm.add(algorithm.size()+1+") "+cookingalgorithm);
        }
        model.addAttribute("recipe",recipe);
        model.addAttribute("ingredients",ingredients);
        model.addAttribute("algorithm", algorithm);
        return "/addingredients";
    }

    @PostMapping("addRecipe")
    public  String addRecipeInBD(){
        StringBuffer in= new StringBuffer();
        StringBuffer alg = new StringBuffer();
        for(int i=0;i<ingredients.size();i++){
            if(i!=ingredients.size()-1){
                in.append(ingredients.get(i)+"<-regex->");
            }else in.append(ingredients.get(i));
        }
        for(int i=0;i<algorithm.size();i++){
            if(i!=algorithm.size()-1){
                alg.append(algorithm.get(i)+"<-regex->");
            }else alg.append(algorithm.get(i));
        }
        recipe.setIngredients(in.toString());
        recipe.setCookingAlgorithm(alg.toString());
        recipeRepo.save(recipe);
        algorithm=new ArrayList<>();
        ingredients=new ArrayList<>();
        return "redirect:/";
    }

}
