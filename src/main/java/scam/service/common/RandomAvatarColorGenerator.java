package scam.service.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomAvatarColorGenerator {
    private final List<String> colors = new ArrayList<>(Arrays.asList(
            "#a6e4ff", "#ff867d", "#d6d6d6", "#f9ffba"));

    private final Random random;

    public RandomAvatarColorGenerator() {
        this.random = new Random();
    }

    public String getRandomColor(){

        return colors.get(random.nextInt(colors.size()));
    }
}
