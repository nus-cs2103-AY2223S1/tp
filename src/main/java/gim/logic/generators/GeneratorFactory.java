package gim.logic.generators;

import static gim.logic.generators.ValidLevel.EASY;
import static gim.logic.generators.ValidLevel.HARD;
import static gim.logic.generators.ValidLevel.MEDIUM;

import gim.model.exercise.Name;

/**
 * Selects and creates the appropriate generator based on difficulty level.
 */
public class GeneratorFactory {

    /**
     * @param name name of the exercise.
     * @param level difficulty level of the workout to be generated.
     */
    public static Generator getGenerator(Name name, ValidLevel level) {
        if (level.equals(EASY)) {
            return new EasyGenerator(name);
        }
        if (level.equals(MEDIUM)) {
            return new MediumGenerator(name);
        }
        if (level.equals(HARD)) {
            return new HardGenerator(name);
        }
        assert false : "cannot create generator of invalid difficulty level: " + level;
        return null;
    }
}
