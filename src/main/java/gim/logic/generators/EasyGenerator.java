package gim.logic.generators;

import gim.model.exercise.Exercise;


/**
 * Generator for easy workout session.
 */
public class EasyGenerator implements Generator {
    private Exercise exercisePR;

    public EasyGenerator(Exercise exercisePR) {
        this.exercisePR = exercisePR;
    }

    @Override
    public String suggest() {
        return "easy workout for " + exercisePR;
    }
}
