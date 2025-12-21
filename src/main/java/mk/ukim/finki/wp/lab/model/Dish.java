package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;

    public Dish(String dishId,
                String name,
                String cuisine,
                int preparationTime,
                Rank rank) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.rank = rank;
    }
}
