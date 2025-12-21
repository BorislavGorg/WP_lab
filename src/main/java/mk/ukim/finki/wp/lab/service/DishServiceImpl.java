package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.Rank;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findAll()
                .stream()
                .filter(d -> d.getDishId().equals(dishId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish create(String dishId,
                       String name,
                       String cuisine,
                       int preparationTime,
                       Rank rank,
                       Long chefId) {

        Chef chef = chefRepository.findById(chefId).orElse(null);

        Dish dish = new Dish(dishId, name, cuisine, preparationTime, rank);
        dish.setChef(chef);

        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id,
                       String dishId,
                       String name,
                       String cuisine,
                       int preparationTime,
                       Rank rank,
                       Long chefId) {

        Dish dish = findById(id);
        Chef chef = chefRepository.findById(chefId).orElse(null);

        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);
        dish.setRank(rank);
        dish.setChef(chef);

        return dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> filter(String name,
                             String cuisine,
                             Integer maxPrepTime,
                             Rank rank) {

        return dishRepository.findAll()
                .stream()
                .filter(d -> name == null || name.isEmpty()
                        || d.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(d -> cuisine == null || cuisine.isEmpty()
                        || d.getCuisine().toLowerCase().contains(cuisine.toLowerCase()))
                .filter(d -> maxPrepTime == null
                        || d.getPreparationTime() < maxPrepTime)
                .filter(d -> rank == null || d.getRank() == rank)
                .collect(Collectors.toList());
    }
}
