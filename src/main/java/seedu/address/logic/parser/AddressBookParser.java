package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND_TYPE;
import static seedu.address.logic.parser.CliSyntax.CONTACT_COMMAND_IDENTIFIER;
import static seedu.address.logic.parser.CliSyntax.MODULE_COMMAND_IDENTIFIER;

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
import seedu.address.logic.commands.ListModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user's input.
 */
public class AddressBookParser {

    /**
     * Used for initial extraction of command word.
     */
    private static final Pattern GENERAL_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<commandParameter>.*)");
    /**
     * Used for extracting command type and command arguments.
     */
    private static final Pattern COMMAND_PARAMETER_FORMAT =
            Pattern.compile("(.)(?<commandType>\\S+)(?<commandArguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        /* Used to extract command word and all parameters */
        final Matcher generalMatcher = GENERAL_COMMAND_FORMAT.matcher(userInput.trim());
        if (!generalMatcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = generalMatcher.group("commandWord");
        final String commandParameter = generalMatcher.group("commandParameter");

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD:
            /* Used to extract command type and arguments. */
            final Matcher parameterMatcher = COMMAND_PARAMETER_FORMAT.matcher(commandParameter);
            if (!parameterMatcher.matches()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_UNKNOWN_COMMAND_TYPE));
            }
            final String commandType = parameterMatcher.group("commandType");
            final String commandArguments = parameterMatcher.group("commandArguments");
            switch (commandWord) {
            case AddCommand.COMMAND_WORD:
                switch (commandType) {
                case CONTACT_COMMAND_IDENTIFIER:
                    return new AddCommandParser().parse(commandArguments);
                case MODULE_COMMAND_IDENTIFIER:
                    return new AddModuleCommandParser().parse(commandArguments);
                default:
                    //TODO doesnt reach this block of code, to fix
                    throw new ParseException(MESSAGE_UNKNOWN_COMMAND_TYPE);
                }
            case DeleteCommand.COMMAND_WORD:
                switch (commandType) {
                case CONTACT_COMMAND_IDENTIFIER:
                    return new DeleteCommandParser().parse(commandArguments);
                case MODULE_COMMAND_IDENTIFIER:
                    return new DeleteModuleCommandParser().parse(commandArguments);
                default:
                    throw new ParseException(MESSAGE_UNKNOWN_COMMAND_TYPE);
                }
            case ListCommand.COMMAND_WORD:
                switch(commandType) {
                case CONTACT_COMMAND_IDENTIFIER:
                    return new ListCommand();
                case MODULE_COMMAND_IDENTIFIER:
                    return new ListModuleCommand();
                default:
                    throw new ParseException(MESSAGE_UNKNOWN_COMMAND_TYPE);
                }
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND_TYPE);
            }
            // methods that are supposed to differentiate m and c
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(commandParameter);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(commandParameter);

        // method that do not require m and c
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            //TODO error runs to here when missing c/m
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
