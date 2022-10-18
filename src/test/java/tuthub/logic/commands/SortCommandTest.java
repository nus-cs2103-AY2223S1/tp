package tuthub.logic.commands;

import org.junit.jupiter.api.Test;
import tuthub.logic.parser.Prefix;
import tuthub.model.tutor.SortByRatingComparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortCommandTest {

    @Test
    public void equals() {
        SortByRatingComparator firstComparator =
                new SortByRatingComparator("a");
        SortByRatingComparator secondComparator =
                new SortByRatingComparator("d");

        Prefix firstPrefix = new Prefix("a/");
        Prefix secondPrefix = new Prefix("tn/");

        SortCommand sortFirstCommand = new SortCommand("a", firstPrefix,
                firstComparator);
        SortCommand sortSecondCommand = new SortCommand("a", firstPrefix,
                secondComparator); // different comparator
        SortCommand sortThirdCommand = new SortCommand("d", firstPrefix,
                firstComparator); // different order
        SortCommand sortFourthCommand = new SortCommand("a", secondPrefix,
                firstComparator); // different prefix
        SortCommand sortFifthCommand = new SortCommand("d", secondPrefix,
                secondComparator); // all different

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand("a", firstPrefix, firstComparator);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different comparator -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
        assertFalse(sortFirstCommand.equals(sortThirdCommand));
        assertFalse(sortFirstCommand.equals(sortFourthCommand));
        assertFalse(sortFirstCommand.equals(sortFifthCommand));
    }
}
