package scam.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class RandomAuthorNameGenerator {
    
    private final List<String> authors = new ArrayList<>(Arrays.asList(
            "Димитър Грудев",
            "Иван Карабоников",
            "Тони Герджиков",
            "Ани Василева",
            "Пролет Димитрова",
            "Павлина Гиновска",
            "Янчо Тодев",
            "Ивайло Ганев",
            "Христина Маринова",
            "Пламен Вълчанов",
            "Красимир Москов",
            "Петко Петров",
            "Албена Димитрова",
            "Милица Дончева",
            "Салим Хусейн "));
    
    private final Random random;
    
    public RandomAuthorNameGenerator() {
        this.random = new Random();
    }

    public String getRandomAuthor(){
        
        return authors.get(random.nextInt(authors.size()));
    }
    
    
}
