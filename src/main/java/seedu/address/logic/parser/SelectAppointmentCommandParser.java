package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectAppointmentCommand object. Used for commands that involve selecting
 * an appointment only with no additional arguments.
 */
public abstract class SelectAppointmentCommandParser<T extends SelectAppointmentCommand> implements Parser<T> {

    /**
     * Gets the Index of the appointment itself.
     *
     * @param args The string to be parsed.
     * @return Index of the appointment itself.
     * @throws ParseException If there is not exactly 1 index found or if there is any invalid indexes.
     */
    protected Index getAppointmentIndex(String args) throws ParseException {
        requireNonNull(args);

        Index index = ParserUtil.parseIndex(args);
        return index;
    }
}
