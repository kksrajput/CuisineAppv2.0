package cuisines.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cuisines.Ingredient;
import cuisines.Ingredient.Type;
import cuisines.Customer;
import cuisines.CuisineOrder;
import cuisines.Cuisine;
import cuisines.data.CuisineRepository;
import cuisines.data.IngredientRepository;
import cuisines.data.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/design")
@SessionAttributes("cuisineOrder")
@Slf4j
public class CuisineController {

  private final IngredientRepository ingredientRepo;
  
  private CuisineRepository cuisineRepo;
  
  private UserRepository userRepo;

  @Autowired
  public CuisineController(
        IngredientRepository ingredientRepo,CuisineRepository cuisineRepo,UserRepository userRepo) {
    this.ingredientRepo = ingredientRepo;
    this.cuisineRepo = cuisineRepo;
    this.userRepo = userRepo;
  }
  

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }
  }

  @ModelAttribute(name = "cuisineOrder")
  public CuisineOrder order() {
    return new CuisineOrder();
  }

  @ModelAttribute(name = "cuisine")
  public Cuisine cuisine() {
    return new Cuisine();
  }
  
  @ModelAttribute(name = "user")
  public Customer user(Principal principal) {
	  String username = principal.getName();
	  Customer user = userRepo.findByUsername(username);
	  return user;
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  @PostMapping
  public String processCuisine(
      @Valid Cuisine cuisine, Errors errors,
      @ModelAttribute CuisineOrder cuisineOrder) {
	
	log.info("Saving Cuisine");  
	  
    if (errors.hasErrors()) {
      return "design";
    }
    Cuisine saved = cuisineRepo.save(cuisine);
    cuisineOrder.addCuisine(cuisine);

    return "redirect:/orders/current";
  }

  private Iterable<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}