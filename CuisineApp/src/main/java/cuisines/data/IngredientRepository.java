package cuisines.data;

import org.springframework.data.repository.CrudRepository;

import cuisines.Ingredient;

public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {
  
}
