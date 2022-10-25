package gim.logic.generators;

import gim.model.exercise.Exercise;

/**
 * Generator for easy workout session.
 */
public class HardGenerator implements Generator {
    private Exercise exercisePR;

    public HardGenerator(Exercise exercisePR) {
        this.exercisePR = exercisePR;
    }

    @Override
    public String suggest() {
        double factor = 0.8;
        double suggestedWeight = factor * Double.parseDouble(exercisePR.getWeight().value);
        return "hard workout for " + exercisePR.getName() + ": " + suggestedWeight;
    }
}
