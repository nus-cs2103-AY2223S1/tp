package seedu.address.logic.parser.persons;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.persons.AddCommand;
import seedu.address.logic.commands.persons.DeleteCommand;
import seedu.address.logic.commands.persons.FindCommand;
import seedu.address.logic.commands.persons.ForEachPersonCommand;
import seedu.address.logic.commands.persons.PersonCommand;
import seedu.address.logic.commands.persons.SelectPersonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for all Task commands
 */
public class PersonCommandParser implements Parser<PersonCommand> {
    private static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " [new|delete|select|find]";
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
    public PersonCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("subcommandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddCommand.SUBCOMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case DeleteCommand.SUBCOMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case SelectPersonCommand.SUBCOMMAND_WORD:
            return new SelectPersonCommandParser().parse(arguments);
        case ForEachPersonCommand.SUBCOMMAND_WORD:
            return new ForEachPersonCommandParser().parse(arguments);
        case FindCommand.SUBCOMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_USAGE);
        }
    }
}
