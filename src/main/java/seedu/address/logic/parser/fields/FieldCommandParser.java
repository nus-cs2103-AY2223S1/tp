package seedu.address.logic.parser.fields;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.fields.AddFieldCommand;
import seedu.address.logic.commands.fields.DeleteFieldCommand;
import seedu.address.logic.commands.fields.EditFieldCommand;
import seedu.address.logic.commands.fields.FieldCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for all Task commands
 */
public class FieldCommandParser implements Parser<FieldCommand> {
    private static final String MESSAGE_USAGE = FieldCommand.COMMAND_WORD + " [add|delete|edit]";
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<subcommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution. The input must be a valid subcommand for Task.
     * There should not be a TaskCommand prefix in the input.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public FieldCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("subcommandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddFieldCommand.SUBCOMMAND_WORD:
            return new AddFieldCommandParser().parse(arguments);
        case EditFieldCommand.SUBCOMMAND_WORD:
            return new EditFieldCommandParser().parse(arguments);
        case DeleteFieldCommand.SUBCOMMAND_WORD:
            return new DeleteFieldCommandParser().parse(arguments);
        default:
            throw new ParseException("errrr");
        }
    }
}
