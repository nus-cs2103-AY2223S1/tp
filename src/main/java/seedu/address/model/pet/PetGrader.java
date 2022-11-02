package seedu.address.model.pet;

import static seedu.address.model.order.PriceRange.LOWER_THAN_RANGE;
import static seedu.address.model.order.PriceRange.WITHIN_RANGE;

import seedu.address.model.order.Order;
import seedu.address.model.order.PriceRange;

/**
 * Grades how fit a pet is given an order.
 */
public class PetGrader {

    private static final double DEFAULT_AGE_SCORE_WEIGHT = 30;
    private static final double DEFAULT_COLOR_SCORE_WEIGHT = 100;
    private static final double DEFAULT_COLOR_PATTERN_SCORE_WEIGHT = 100;
    private static final double DEFAULT_SPECIES_SCORE_WEIGHT = 500;
    private static final double DEFAULT_PRICE_SCORE_WEIGHT = 5;

    private final Order order;
    private final double ageScoreWeight;
    private final double colorScoreWeight;
    private final double colorPatternScoreWeight;
    private final double speciesScoreWeight;
    private final double priceScoreWeight;

    /**
     * Constructs a PetGrader object.
     *
     * @param order The order to be compared with.
     */
    public PetGrader(Order order) {
        this.order = order;
        ageScoreWeight = DEFAULT_AGE_SCORE_WEIGHT;
        colorScoreWeight = DEFAULT_COLOR_SCORE_WEIGHT;
        colorPatternScoreWeight = DEFAULT_COLOR_PATTERN_SCORE_WEIGHT;
        speciesScoreWeight = DEFAULT_SPECIES_SCORE_WEIGHT;
        priceScoreWeight = DEFAULT_PRICE_SCORE_WEIGHT;
    }

    /**
     * Constructs a PetGrader object.
     *
     * @param order The order to be compared with.
     * @param ageScoreWeight The score weight for age.
     * @param colorScoreWeight The score weight for color.
     * @param colorPatternScoreWeight The score weight for color pattern.
     * @param speciesScoreWeight The score weight for species.
     * @param priceScoreWeight The score weight for price.
     */
    public PetGrader(Order order,
                     double ageScoreWeight,
                     double colorScoreWeight,
                     double colorPatternScoreWeight,
                     double speciesScoreWeight,
                     double priceScoreWeight) {
        this.order = order;
        this.ageScoreWeight = ageScoreWeight;
        this.colorScoreWeight = colorScoreWeight;
        this.colorPatternScoreWeight = colorPatternScoreWeight;
        this.speciesScoreWeight = speciesScoreWeight;
        this.priceScoreWeight = priceScoreWeight;
    }

    /**
     * Evaluates the score of a pet.
     *
     * @param pet The @code{Pet} object to be evaluated.
     */
    public double evaluate(Pet pet) {
        double ageScore = ageScoreWeight
                - ageScoreWeight * Math.abs(order.getRequest().getRequestedAge().getValue() - pet.getAge());
        double colorScore = (order.getRequest().getRequestedColor().equals(pet.getColor()) ? 1 : 0)
                * colorScoreWeight;
        double colorPatternScore = (order.getRequest().getRequestedColorPattern().equals(pet.getColorPattern()) ? 1 : 0)
                * colorPatternScoreWeight;
        double speciesScore = (order.getRequest().getRequestedSpecies().equals(pet.getSpecies()) ? 1 : 0)
                * speciesScoreWeight;

        double priceScore;
        PriceRange priceRange = order.getRequestedPriceRange();
        int priceRangeSituation = priceRange.comparePrice(pet.getPrice());

        if (priceRangeSituation == WITHIN_RANGE) {
            priceScore = priceScoreWeight;
        } else {
            priceScore = priceScoreWeight
                    - Math.abs((priceRangeSituation == LOWER_THAN_RANGE
                            ? priceRange.getLowerBound().getPrice()
                            : priceRange.getUpperBound().getPrice())
                            - pet.getPrice().getPrice());
        }

        double sum = ageScore + colorScore + colorPatternScore + speciesScore + priceScore;
        System.out.println(pet.getName() + "'s score: " + sum);
        return sum;
    }

    public double getAgeScoreWeight() {
        return ageScoreWeight;
    }

    public double getColorScoreWeight() {
        return colorScoreWeight;
    }

    public double getColorPatternScoreWeight() {
        return colorPatternScoreWeight;
    }

    public double getPriceScoreWeight() {
        return priceScoreWeight;
    }

    public double getSpeciesScoreWeight() {
        return speciesScoreWeight;
    }
}
