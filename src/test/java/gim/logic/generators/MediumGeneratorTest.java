package gim.logic.generators;


import static gim.testutil.TypicalExercises.ARM_CURLS;
import static gim.testutil.TypicalExercises.BICEP_CURLS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


class MediumGeneratorTest {
    final MediumGenerator standardMediumGenerator = new MediumGenerator(BICEP_CURLS);
    final double standardWeight = Double.parseDouble(BICEP_CURLS.getWeight().value);

    @Test
    void suggest() {
        String setsAndReps = "3sets x 8reps";
        double factor = 0.7;

        double suggestedWeight = factor * standardWeight;
        String expectedString = String.format("%s: %.2fkg %s", BICEP_CURLS.getName(), suggestedWeight, setsAndReps);
        assertEquals(expectedString, standardMediumGenerator.suggest());

        // suggestion with wrong weight
        double wrongWeight = factor * standardWeight + 1;
        String stringWrongWeight = String.format("%s: %.2fkg %s", BICEP_CURLS.getName(), wrongWeight, setsAndReps);
        assertNotEquals(stringWrongWeight, standardMediumGenerator.suggest());
    }

    @Test
    void equals() {
        // same exercise and level
        assertEquals(standardMediumGenerator, new MediumGenerator(BICEP_CURLS));

        // same exercise but different level
        assertNotEquals(standardMediumGenerator, new HardGenerator(BICEP_CURLS));

        // same level but different exercise
        assertNotEquals(standardMediumGenerator, new MediumGenerator(ARM_CURLS));
    }
}
