package seedu.address.model;

import static java.util.Objects.requireNonNull;

/**
 *
 */
public class Calorie {
    private final Integer calorie;

    /**
     *
     */
    public Calorie() {
        this.calorie = 2000;
    }

    /**
     * @param calorie
     */
    public Calorie(Integer calorie) {
        requireNonNull(calorie);
        this.calorie = calorie;
    }
}
