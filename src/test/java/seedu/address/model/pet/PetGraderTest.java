package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;
import seedu.address.testutil.PetBuilder;

public class PetGraderTest {

    @Test
    public void evaluate() {
        Age age = new Age(1);
        Color color = new Color("green");
        ColorPattern colorPattern = new ColorPattern("stripes");
        Species species = new Species("parakeeeeet");
        Order order = new OrderBuilder().withRequestedPriceRange(10, 50)
                .withRequest(age, color, colorPattern, species).build();

        //age scoring
        Pet firstPet = new PetBuilder().withDateOfBirth(2021, 10, 10).build();
        Pet secondPet = new PetBuilder().withDateOfBirth(2020, 10, 10).build();
        PetGrader petGrader = new PetGrader(order, 1, 0 , 0,
                0, 0);
        assertTrue(petGrader.evaluate(firstPet) > petGrader.evaluate(secondPet));

        //color scoring
        firstPet = new PetBuilder().withColor("green").build();
        secondPet = new PetBuilder().withColor("blue").build();
        petGrader = new PetGrader(order, 0, 1 , 0,
                0, 0);
        assertTrue(petGrader.evaluate(firstPet) > petGrader.evaluate(secondPet));

        //color pattern scoring
        firstPet = new PetBuilder().withColorPattern("stripes").build();
        secondPet = new PetBuilder().withColor("dots").build();
        petGrader = new PetGrader(order, 0, 0 , 1,
                0, 0);
        assertTrue(petGrader.evaluate(firstPet) > petGrader.evaluate(secondPet));

        //species scoring
        firstPet = new PetBuilder().withSpecies("parakeeeeet").build();
        secondPet = new PetBuilder().withSpecies("parrot").build();
        petGrader = new PetGrader(order, 0, 0 , 0,
                1, 0);
        assertTrue(petGrader.evaluate(firstPet) > petGrader.evaluate(secondPet));

        //price scoring
        firstPet = new PetBuilder().withPrice(15).build();
        secondPet = new PetBuilder().withPrice(100).build();
        petGrader = new PetGrader(order, 0, 0 , 0,
                0, 1);
        assertTrue(petGrader.evaluate(firstPet) > petGrader.evaluate(secondPet));

        //price scoring, none in range
        firstPet = new PetBuilder().withPrice(100).build();
        secondPet = new PetBuilder().withPrice(1000).build();
        petGrader = new PetGrader(order, 0, 0 , 0,
                0, 1);
        assertTrue(petGrader.evaluate(firstPet) > petGrader.evaluate(secondPet));
    }

    @Test
    public void getAgeScoreWeight() {
        Order order = new OrderBuilder().build();
        PetGrader petGrader = new PetGrader(order, 1.64, 454, 543,
                5, 9);
        assertEquals(petGrader.getAgeScoreWeight(), 1.64);
    }

    @Test
    public void getColorScoreWeight() {
        Order order = new OrderBuilder().build();
        PetGrader petGrader = new PetGrader(order, 1.64, 454, 543,
                5, 9);
        assertEquals(petGrader.getColorScoreWeight(), 454);
    }

    @Test
    public void getColorPatternScoreWeight() {
        Order order = new OrderBuilder().build();
        PetGrader petGrader = new PetGrader(order, 1.64, 454, 543,
                5, 9);
        assertEquals(petGrader.getColorPatternScoreWeight(), 543);
    }

    @Test
    public void getPriceScoreWeight() {
        Order order = new OrderBuilder().build();
        PetGrader petGrader = new PetGrader(order, 1.64, 454, 543,
                5, 9);
        assertEquals(petGrader.getPriceScoreWeight(), 9);
    }

    @Test
    public void getSpeciesScoreWeight() {
        Order order = new OrderBuilder().build();
        PetGrader petGrader = new PetGrader(order, 1.64, 454, 543,
                5, 9);
        assertEquals(petGrader.getSpeciesScoreWeight(), 5);
    }
}
