package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findAllByChef_Id(Long chefId);

    List<Dish> findAllByNameContainingIgnoreCaseAndCuisineContainingIgnoreCase(
            String name, String cuisine
    );

    List<Dish> findAllByPreparationTimeLessThan(int prepTime);

    List<Dish> findAllByRank(Rank rank);
}
