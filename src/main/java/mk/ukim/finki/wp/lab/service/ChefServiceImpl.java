package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElseThrow();
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = findById(chefId);
        Dish dish = dishRepository.findAll()
                .stream()
                .filter(d -> d.getDishId().equals(dishId))
                .findFirst()
                .orElseThrow();
        dish.setChef(chef);
        dishRepository.save(dish);
        return chef;
    }

    @Override
    public Chef create(String firstName, String lastName, String bio) {
        Chef chef = new Chef(firstName, lastName, bio, null);
        return chefRepository.save(chef);
    }

    @Override
    public Chef update(Long id, String firstName, String lastName, String bio) {
        Chef chef = findById(id);
        chef.setFirstName(firstName);
        chef.setLastName(lastName);
        chef.setBio(bio);
        return chefRepository.save(chef);
    }

    @Override
    public void delete(Long id) {
        chefRepository.deleteById(id);
    }
}
