package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.rc4hdb.logic.commands.modelcommands.ShowCommand;
import seedu.rc4hdb.logic.parser.Parser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

/**
 * Parses input arguments and creates a new ShowCommand object.
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    public static final String INTENDED_USAGE = "Please enter at least one field after the show command.\n"
            + "Example: show name phone email";

    public static final String ERROR_MESSAGE = "Please only specify fields that correspond to resident data, "
            + "or check if you have made a typo.";

    private static final Logger logger = Logger.getLogger("ShowCommandParser");

    /**
     * Implements the parse method in the Parser interface.
     * @param args The arguments read from the user
     * @return A ShowCommand with the list of fields to hide
     * @throws ParseException if the arguments have invalid inputs
     */
    @Override
    public ShowCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.log(Level.INFO, "Going to start parsing.");

        if (args.isEmpty()) {
            logger.log(Level.WARNING, "Empty arguments when parsing.");
            throw new ParseException(INTENDED_USAGE);
        }

        List<String> fieldsToShow = new ArrayList<>();

        // Performs input validation and updates fieldsToShow if all fields are valid
        populateFieldList(args, fieldsToShow);

        logger.log(Level.INFO, "Parsing completed, returning ShowCommand");

        return new ShowCommand(fieldsToShow);
    }

    /**
     * Splits the given string into an array of lowercase fields.
     * @param args The string of arguments provided by ResidentBookParser
     * @return A {@code String} array of lowercase fields
     */
    private String[] getSpecifiedFields(String args) {
        return args.trim().toLowerCase().split(" ");
    }

    /**
     * Adds fields to the list of fields to be shown.
     * @param args The {@code String} of user input obtained from ResidentBookParser
     * @param fieldsToShow The list of fields to be returned to the
     * @throws ParseException If any inputs are invalid.
     */
    private void populateFieldList(String args, List<String> fieldsToShow) throws ParseException {
        String[] specifiedFields = getSpecifiedFields(args);
        for (String field : specifiedFields) {
            if (!ResidentFields.LOWERCASE_FIELDS.contains(field)) {
                throw new ParseException(ERROR_MESSAGE);
            }
            fieldsToShow.add(field);
        }
    }
}
