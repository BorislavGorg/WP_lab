package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init(){


        chefs.add(new Chef(1L,"Rene","Redzepi","nordic",new ArrayList<>()));
        chefs.add(new Chef(2L,"Massimo","Bottura","italian",new ArrayList<>()));
        chefs.add(new Chef(3L,"Heston","Blumenthal","experimental",new ArrayList<>()));
        chefs.add(new Chef(4L,"Ana","Roš","slovenian",new ArrayList<>()));
        chefs.add(new Chef(5L,"Enrique","Olvera","mexican",new ArrayList<>()));

        
        dishes.add(new Dish("risotto","Mushroom Risotto","Italian",14));
        dishes.add(new Dish("ramen","Tonkotsu Ramen","Japanese",12));
        dishes.add(new Dish("tacos","Beef Tacos","Mexican",11));
        dishes.add(new Dish("paella","Seafood Paella","Spanish",18));
        dishes.add(new Dish("falafel","Falafel Bowl","Middle Eastern",9));
    }
}
