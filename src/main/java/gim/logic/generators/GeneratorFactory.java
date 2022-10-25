package gim.logic.generators;

import static gim.logic.generators.ValidLevel.EASY;
import static gim.logic.generators.ValidLevel.HARD;
import static gim.logic.generators.ValidLevel.MEDIUM;

import gim.model.exercise.Exercise;

/**
 * Selects and creates the appropriate generator based on a {@link ValidLevel}.
 */
public class GeneratorFactory {

    /**
     * @param exercisePR the PR of the exercise to generate a workout for.
     * @param level difficulty level of the workout to be generated.
     */
    public static Generator getGenerator(Exercise exercisePR, ValidLevel level) {
        if (level.equals(EASY)) {
            return new EasyGenerator(exercisePR);
        }
        if (level.equals(MEDIUM)) {
            return new MediumGenerator(exercisePR);
        }
        if (level.equals(HARD)) {
            return new HardGenerator(exercisePR);
        }
        assert false : "cannot create generator of invalid difficulty level: " + level;
        return null;
    }
}
