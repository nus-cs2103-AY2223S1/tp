package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOSPITAL_WING_PARSER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_PARSER;


/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern GET_COMMAND_FORMAT = Pattern
            .compile("(?<commandWord>[get\\s]+)(?<prefix>[/a-z\\s]{4})(?<arguments>[a-z\\s].*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {

       final Matcher getMatcher = GET_COMMAND_FORMAT.matcher(userInput.trim());

       if (getMatcher.matches()) {
            final String prefix = getMatcher.group("prefix").trim();
            final Prefix prefixes = new Prefix(prefix);
            final String arguments = getMatcher.group("arguments").trim();

            if (prefixes.equals(PREFIX_HOSPITAL_WING_PARSER)) {
                return new GetHospitalWingCommandParser().parse(arguments);
            }

            if (prefixes.equals(PREFIX_NAME_PARSER)) {
                return new FindCommandParser().parse(arguments);
            }
        }

        final Matcher basicCommandMatcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (basicCommandMatcher.matches()) {
            final String commandWord = basicCommandMatcher.group("commandWord");
            final String arguments = basicCommandMatcher.group("arguments");
            switch (commandWord) {

            case AddCommand.COMMAND_WORD:
                return new AddCommandParser().parse(arguments);

            case EditCommand.COMMAND_WORD:
                return new EditCommandParser().parse(arguments);

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommandParser().parse(arguments);

            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case FindCommand.COMMAND_WORD:
                return new FindCommandParser().parse(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }

        if (!basicCommandMatcher.matches() && !getMatcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        return null;
    }
}
