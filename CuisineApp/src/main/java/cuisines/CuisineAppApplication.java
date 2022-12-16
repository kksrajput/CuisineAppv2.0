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
	        repo.save(new Ingredient("NAN", "NAN", Type.BREAD));
	        repo.save(new Ingredient("CHAPATTI", "CHAPPATI", Type.BREAD));
	        repo.save(new Ingredient("PANEER", "PANEER TIKKA", Type.VEGGIES));
//	        repo.save(new Ingredient("PANEER", "PANNER BUTTER MASALA", Type.VEGGIES));
	        repo.save(new Ingredient("SMB", "SAMBHAR", Type.VEGGIES));
	        repo.save(new Ingredient("RASHM", "RASHM", Type.VEGGIES));
	        repo.save(new Ingredient("DRINK", "WATER", Type.DRINK));
	        repo.save(new Ingredient("BRF", "BARFI", Type.SWEET));
//	        repo.save(new Ingredient("CHUTNEY", "PUDINA", Type.CHUTNEY));
	        repo.save(new Ingredient("CHUTNEY", "AANVLA", Type.CHUTNEY));
	      }
	    };
	  }
}
