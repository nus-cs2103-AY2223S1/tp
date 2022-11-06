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
        String setsAndReps = "3sets x 8reps";
        double factor = 0.7;
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
        if (!(other instanceof MediumGenerator)) {
            return false;
        }

        // state check
        MediumGenerator e = (MediumGenerator) other;
        return exercisePR.equals(e.exercisePR);
    }
}

