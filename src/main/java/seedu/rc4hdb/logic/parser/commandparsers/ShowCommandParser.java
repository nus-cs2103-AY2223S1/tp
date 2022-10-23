package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.rc4hdb.logic.commands.modelcommands.ShowCommand;
import seedu.rc4hdb.logic.parser.Parser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.fields.ResidentField;

/**
 * Parses input arguments and creates a new ShowCommand object.
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    public static final String INTENDED_USAGE = "Please (only) enter some fields after the show command\n"
            + "Example: show name phone email";

    public static final String ERROR_MESSAGE = "Please only specify fields that correspond to resident data, "
            + "or check if you have made a typo.";

    private static Logger logger = Logger.getLogger("ShowCommandParser");

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

        // Process global list of fields into lowercase list first
        List<String> allFields = ResidentField.FIELDS.stream().map(String::toLowerCase).collect(Collectors.toList());

        // Create result list
        List<String> fieldsToHide = new ArrayList<>(allFields);

        String[] specifiedFields = getSpecifiedFields(args);

        populateFieldLists(specifiedFields, fieldsToHide, allFields);

        logger.log(Level.INFO, "Parsing completed, returning ShowCommand");
        return new ShowCommand(fieldsToHide);
    }

    private String[] getSpecifiedFields(String args) {
        return args.trim().toLowerCase().split(" ");
    }

    private void populateFieldLists(String[] specifiedFields,
                                    List<String> fieldsToHide,
                                    List<String> allFields) throws ParseException {
        for (String field : specifiedFields) {
            if (!allFields.contains(field)) {
                throw new ParseException(ERROR_MESSAGE);
            }
            fieldsToHide.remove(field);
        }
    }
}
