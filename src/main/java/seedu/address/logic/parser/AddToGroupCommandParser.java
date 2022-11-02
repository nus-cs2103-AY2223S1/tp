package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Locale;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddToGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

/**
 * Parses input arguments and creates a new AddToGroupCommand object
 */
public class AddToGroupCommandParser implements Parser<AddToGroupCommand> {

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AddToGroupCommand
     * and returns an AddToGroupCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddToGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        Group groupToAdd;

        String[] splitArgs = args.trim().split("\\s+", 2);
        if (splitArgs.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToGroupCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(splitArgs[0]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToGroupCommand.MESSAGE_USAGE),
                    pe);
        }

        if (!Group.isValidGroupName(splitArgs[1])) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToGroupCommand.MESSAGE_USAGE));
        }
        groupToAdd = new Group(splitArgs[1].toLowerCase());

        return new AddToGroupCommand(index, groupToAdd);
    }

}
