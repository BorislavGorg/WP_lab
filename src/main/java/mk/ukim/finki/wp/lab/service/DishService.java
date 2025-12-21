package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.Rank;

import java.util.List;

public interface DishService {

    List<Dish> listDishes();

    Dish findByDishId(String dishId);

    Dish findById(Long id);

    Dish create(String dishId,
                String name,
                String cuisine,
                int preparationTime,
                Rank rank,
                Long chefId);

    Dish update(Long id,
                String dishId,
                String name,
                String cuisine,
                int preparationTime,
                Rank rank,
                Long chefId);

    void delete(Long id);

    List<Dish> filter(String name,
                      String cuisine,
                      Integer maxPrepTime,
                      Rank rank);
}
