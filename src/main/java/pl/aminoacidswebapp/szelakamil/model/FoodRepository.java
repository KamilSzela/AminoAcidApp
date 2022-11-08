package pl.aminoacidswebapp.szelakamil.model;

import java.util.List;
import java.util.Optional;

public interface FoodRepository {
    List<Food> findAll();
    Optional<Food> findByid(Integer id);
}
