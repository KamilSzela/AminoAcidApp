package pl.aminoacidswebapp.szelakamil.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.aminoacidswebapp.szelakamil.model.Requirement;
import pl.aminoacidswebapp.szelakamil.model.RequirementRepository;

@Controller
public class HomeController {

    RequirementRepository repository;

    HomeController (RequirementRepository repo) {
        this.repository = repo;
    }

    @RequestMapping("/")
    String returnHomePage() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/requirements", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Requirement> getRequirements() {
        return repository.findByid(1).map( food -> ResponseEntity.ok(food)).orElse(ResponseEntity.notFound().build());
    }
}
