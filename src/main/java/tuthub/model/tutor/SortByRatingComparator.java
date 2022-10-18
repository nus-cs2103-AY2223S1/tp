package tuthub.model.tutor;

import tuthub.logic.commands.SortCommand;

import java.util.Comparator;

import static tuthub.logic.commands.SortCommand.NEGATIVE_MULTIPLIER;

public class SortByRatingComparator implements Comparator<Tutor> {
    private final String order;

    public SortByRatingComparator(String order) {
        this.order = order;
    }

    @Override
    public int compare(Tutor o1, Tutor o2) {
        Double ratingTutor1 = Double.parseDouble(o1.getRating().value);
        Double ratingTutor2 = Double.parseDouble(o2.getRating().value);
        return (order.equals(SortCommand.ASCENDING_SHORT))
            ? ratingTutor1.compareTo(ratingTutor2)
            : NEGATIVE_MULTIPLIER * ratingTutor1.compareTo(ratingTutor2);
    }

    public int compareAscend(double val1, double val2) {
        if (val1 > val2) {
            return 1;
        } else if (val1 < val2) {
            return -1;
        }
        return 0;
    }

    public int compareDescend(double val1, double val2) {
        if (val1 > val2) {
            return -1;
        } else if (val1 < val2) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByRatingComparator // instanceof handles nulls
                && order.equals(((SortByRatingComparator) other).order)); // state check
    }
}
