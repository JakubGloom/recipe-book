package com.gluma.recepiebook.controllers;

import com.gluma.recepiebook.commands.RecipeCommand;
import com.gluma.recepiebook.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    /*suppose you want to write a url to fetch some order, you can say

    www.mydomain.com/order/123
    where 123 is orderId.

    So now the url you will use in spring mvc controller would look like

    /order/{orderId}
    Now order id can be declared a path variable

    @RequestMapping(value = " /order/{orderId}", method=RequestMethod.GET)
    public String getOrder(@PathVariable String orderId){
    //fetch order
    }
    if you use url www.mydomain.com/order/123, then orderId variable will
    be populated by value 123 by spring

    Also note that PathVariable differs from requestParam
    as pathVariable is part of URL. The same url using request param would
        look like  www.mydomain.com/order?orderId=123*/

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeform";
    }

    //@ModelAttribute this tells the spring to bind the form post parameters to RecipeCommand object
    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }
}
