package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_AMBIGUOUS_NAME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NAME;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.InspectCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class InspectCommandParser implements Parser<InspectCommand> {
    private final Model model;

    /**
     * Constructs a {@code DeleteCommandParser}
     * @param model the model of the current state
     */
    public InspectCommandParser(Model model) {
        this.model = model;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InspectCommand parse(String args) throws ParseException {
        String[] namePart = args.split(" ", 2);

        if (namePart.length < 2) {
            return new InspectCommand(new String[0]);
        }

        String[] splitName = Arrays.stream(namePart[1].split(" "))
                .map(x -> x.trim().replaceAll(" +", " "))
                .filter(x -> !x.equals(" ")).toArray(String[]::new);

        return new InspectCommand(splitName);
    }
}
