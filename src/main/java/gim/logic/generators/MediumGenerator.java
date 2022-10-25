package gim.logic.generators;

import gim.model.exercise.Exercise;

/**
 * Generator for easy workout session.
 */
public class MediumGenerator implements Generator {
    private Exercise exercisePR;

    public MediumGenerator(Exercise exercisePR) {
        this.exercisePR = exercisePR;
    }

    @Override
    public String suggest() {
        return "medium workout for " + exercisePR;
    }
}
