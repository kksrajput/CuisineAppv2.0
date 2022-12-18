package cuisines.web;

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
import cuisines.CuisineOrder;
import cuisines.Cuisine;
import cuisines.data.IngredientRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("cuisineOrder")
public class CuisineController {

  private final IngredientRepository ingredientRepo;

  @Autowired
  public CuisineController(
        IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
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

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  @PostMapping
  public String processCuisine(
      @Valid Cuisine cuisine, Errors errors,
      @ModelAttribute CuisineOrder cuisineOrder) {

    if (errors.hasErrors()) {
      return "design";
    }

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
