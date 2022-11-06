package gim.logic.generators;


import static gim.testutil.TypicalExercises.ARM_CURLS;
import static gim.testutil.TypicalExercises.BICEP_CURLS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


class HardGeneratorTest {
    final HardGenerator standardHardGenerator = new HardGenerator(BICEP_CURLS);
    final double standardWeight = Double.parseDouble(BICEP_CURLS.getWeight().value);

    @Test
    void suggest() {
        String setsAndReps = "3sets x 8reps";
        double factor = 0.8;

        double suggestedWeight = factor * standardWeight;
        String expectedString = String.format("%s: %.2fkg %s", BICEP_CURLS.getName(), suggestedWeight, setsAndReps);
        assertEquals(expectedString, standardHardGenerator.suggest());

        // suggestion with wrong weight
        double wrongWeight = factor * standardWeight + 1;
        String stringWrongWeight = String.format("%s: %.2fkg %s", BICEP_CURLS.getName(), wrongWeight, setsAndReps);
        assertNotEquals(stringWrongWeight, standardHardGenerator.suggest());

    }

    @Test
    void equals() {
        // same exercise and level
        assertEquals(standardHardGenerator, new HardGenerator(BICEP_CURLS));

        // same exercise but different level
        assertNotEquals(standardHardGenerator, new EasyGenerator(BICEP_CURLS));

        // same level but different exercise
        assertNotEquals(standardHardGenerator, new HardGenerator(ARM_CURLS));
    }
}
