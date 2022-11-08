package pl.aminoacidswebapp.szelakamil.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.aminoacidswebapp.szelakamil.model.Food;
import pl.aminoacidswebapp.szelakamil.model.FoodRepository;

import java.util.List;

@Controller
@RequestMapping("/commonIngredients")
public class CommonIngredientsController {

    FoodRepository repository;

    public CommonIngredientsController(FoodRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Food>> readAllFoods(){
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Food> findFoodById(@PathVariable Integer id){
        return repository.findByid(id).map( food -> ResponseEntity.ok(food)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    String showPage(){
        return "commonIngredients";
    }
}
