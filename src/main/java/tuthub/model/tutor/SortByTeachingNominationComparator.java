package tuthub.model.tutor;

import static tuthub.logic.commands.SortCommand.NEGATIVE_MULTIPLIER;

import java.util.Comparator;

import tuthub.logic.commands.SortCommand;

/**
 * Contains the comparator that sorts a {@code Tutor}'s {@code TeachingNomination} in ascending or descending order.
 */
public class SortByTeachingNominationComparator implements Comparator<Tutor> {
    private final String order;

    public SortByTeachingNominationComparator(String order) {
        this.order = order;
    }

    @Override
    public int compare(Tutor o1, Tutor o2) {
        Integer teachingNominationTutor1 = Integer.parseInt(o1.getTeachingNomination().value);
        Integer teachingNominationTutor2 = Integer.parseInt(o2.getTeachingNomination().value);
        return (order.equals(SortCommand.ASCENDING_SHORT))
                ? teachingNominationTutor1.compareTo(teachingNominationTutor2)
                : NEGATIVE_MULTIPLIER * teachingNominationTutor1.compareTo(teachingNominationTutor2);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByTeachingNominationComparator // instanceof handles nulls
                && order.equals(((SortByTeachingNominationComparator) other).order)); // state check
    }
}
