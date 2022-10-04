package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectAppointmentCommand object. Used for commands that involve selecting
 * an appointment only with no additional arguments.
 */
public abstract class SelectAppointmentCommandParser<T extends SelectAppointmentCommand> implements Parser<T> {

    /**
     * Gets the index of the person with the appointment and the index of the appointment itself.
     *
     * @param args The string to be parsed.
     * @return A list of 2 indexes, denoting the index of the person with the appointment and the index of the
     *     appointment itself respectively.
     * @throws ParseException If there is not exactly 2 indexes found or if there is any invalid indexes.
     */
    protected List<Index> getPersonAndAppointmentIndexes(String args) throws ParseException {
        requireNonNull(args);
        List<Index> indexList;
        int expectedIndexCount = 2;

        indexList = ParserUtil.parseIndexes(args, expectedIndexCount);
        return indexList;
    }
}
