package cuisines;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cuisines.Ingredient;
import cuisines.Ingredient.Type;
import cuisines.data.IngredientRepository;

@SpringBootApplication
public class CuisineAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuisineAppApplication.class, args);
	}
	@Bean
	  public CommandLineRunner dataLoader(IngredientRepository repo) {
	    return new CommandLineRunner() {
	      @Override
	      public void run(String... args) throws Exception {
	        repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
	        repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
	        repo.save(new Ingredient("BRD", "Bread", Type.PROTEIN));
	        repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
	        repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
	        repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
	        repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
	        repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
	        repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
	        repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
	      }
	    };
	  }
}
