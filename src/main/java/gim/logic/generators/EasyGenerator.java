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
        String setsAndReps = "3sets x 8reps";
        double factor = 0.5;
        double suggestedWeight = factor * Double.parseDouble(exercisePR.getWeight().value);
        return String.format("%s: %.2fkg %s", exercisePR.getName(), suggestedWeight, setsAndReps);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EasyGenerator)) {
            return false;
        }

        // state check
        EasyGenerator e = (EasyGenerator) other;
        return exercisePR.equals(e.exercisePR);
    }
}
