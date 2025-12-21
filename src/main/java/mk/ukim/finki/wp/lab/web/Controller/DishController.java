package mk.ukim.finki.wp.lab.web.Controller;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.Rank;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishList(Model model) {
        model.addAttribute("dishes", dishService.listDishes());
        return "dishList";
    }

    @GetMapping("/add")
    public String addDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("ranks", Rank.values());
        return "dish-form";
    }

    @GetMapping("/edit/{id}")
    public String editDishPage(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);

        model.addAttribute("dish", dish);
        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("ranks", Rank.values());

        return "dish-form";
    }

    @PostMapping("/save")
    public String saveDish(
            @RequestParam(required = false) Long id,
            @RequestParam String dishId,
            @RequestParam String name,
            @RequestParam String cuisine,
            @RequestParam int preparationTime,
            @RequestParam Rank rank,
            @RequestParam Long chefId
    ) {
        if (id == null) {
            dishService.create(dishId, name, cuisine, preparationTime, rank, chefId);
        } else {
            dishService.update(id, dishId, name, cuisine, preparationTime, rank, chefId);
        }
        return "redirect:/dishes";
    }


    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }
}
