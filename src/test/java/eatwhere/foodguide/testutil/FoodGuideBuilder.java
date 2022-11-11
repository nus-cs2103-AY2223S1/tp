package eatwhere.foodguide.testutil;

import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.eatery.Eatery;

/**
 * A utility class to help with building FoodGuide objects.
 * Example usage: <br>
 *     {@code FoodGuide ab = new FoodGuideBuilder().withEatery("John", "Doe").build();}
 */
public class FoodGuideBuilder {

    private FoodGuide foodGuide;

    public FoodGuideBuilder() {
        foodGuide = new FoodGuide();
    }

    public FoodGuideBuilder(FoodGuide foodGuide) {
        this.foodGuide = foodGuide;
    }

    /**
     * Adds a new {@code Eatery} to the {@code FoodGuide} that we are building.
     */
    public FoodGuideBuilder withEatery(Eatery eatery) {
        foodGuide.addEatery(eatery);
        return this;
    }

    public FoodGuide build() {
        return foodGuide;
    }
}
