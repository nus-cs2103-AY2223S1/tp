package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EmailAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

/**
 * Parses input arguments and creates a new EmailAllCommand object
 */
public class EmailAllCommandParser implements Parser<EmailAllCommand> {

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Parses the given {@code String} of arguments in the context of the EmailAllCommand
     * and returns an EmailAllCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EmailAllCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Group groupToEmail;

        String[] splitArgs = args.trim().split("\\s+", 2);
        if (splitArgs.length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailAllCommand.MESSAGE_USAGE));
        }

        if (!Group.isValidGroupName(splitArgs[0])) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailAllCommand.MESSAGE_USAGE));
        }
        groupToEmail = new Group(splitArgs[0]);

        return new EmailAllCommand(groupToEmail);
    }

}
