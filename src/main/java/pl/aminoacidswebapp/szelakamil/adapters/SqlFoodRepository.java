package pl.aminoacidswebapp.szelakamil.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aminoacidswebapp.szelakamil.model.Food;
import pl.aminoacidswebapp.szelakamil.model.FoodRepository;

@Repository
public interface SqlFoodRepository extends FoodRepository, JpaRepository<Food, Integer> {
}
