package gim.logic.generators;


import static gim.logic.generators.ValidLevel.EASY;
import static gim.logic.generators.ValidLevel.HARD;
import static gim.logic.generators.ValidLevel.MEDIUM;
import static gim.testutil.TypicalExercises.ARM_CURLS;
import static gim.testutil.TypicalExercises.BICEP_CURLS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;



class GeneratorFactoryTest {


    @Test
    public void getEasyGenerator_success() {
        assertEquals(new EasyGenerator(BICEP_CURLS), GeneratorFactory.getGenerator(BICEP_CURLS, EASY));
    }

    @Test
    public void getMediumGenerator_success() {
        assertEquals(new MediumGenerator(BICEP_CURLS), GeneratorFactory.getGenerator(BICEP_CURLS, MEDIUM));
    }

    @Test
    public void getHardGenerator_success() {
        assertEquals(new HardGenerator(BICEP_CURLS), GeneratorFactory.getGenerator(BICEP_CURLS, HARD));
    }

    @Test
    public void getGeneratorWrongDifficulty_failure() {
        assertNotEquals(new EasyGenerator(BICEP_CURLS), GeneratorFactory.getGenerator(BICEP_CURLS, HARD));
    }

    @Test
    public void getGeneratorWrongExercise_failure() {
        assertNotEquals(new EasyGenerator(BICEP_CURLS), GeneratorFactory.getGenerator(ARM_CURLS, HARD));
    }

}
