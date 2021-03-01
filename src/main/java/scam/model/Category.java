package scam.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static scam.model.SubCategory.*;

public enum Category {

    POLITIC(new HashSet<>(Arrays.asList(BULGARIA, WORLD))),

    CORONA_VIRUS(new HashSet<>()),

    HEALTH(new HashSet<>(Arrays.asList(WOMAN, MAN, FITNESS, EXERCISES, DRUGS))),

    SPORT(new HashSet<>(Arrays.asList(FOOTBALL, TENNIS, FORMULA, VOLLEYBALL, BASKETBALL, SKI, GAMBLING))),

    SCANDAL(new HashSet<>()),

    CULTURE(new HashSet<>(Arrays.asList(FOOD, MUSIC, VEGAN, HISTORY, MOVIES, HOLIDAYS, BOOKS, FASHION, NATURE))),

    CARS(new HashSet<>(Arrays.asList( TRAFFIC, CRASHES))),

    REVIEWS(new HashSet<>(Arrays.asList(HARDWARE, GAMES, HOME, GIFTS, SMART_PHONE, CAMERAS, CONSOLES, GARDEN, SKI_REVIEW, COSMETIC, SCHOOL)));

    private Set<SubCategory> subCategories;

    Category(Set<SubCategory> categories) {
        this.subCategories = categories;
    }

    public Set<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
