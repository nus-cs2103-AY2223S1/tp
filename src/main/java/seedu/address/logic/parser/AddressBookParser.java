package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import picocli.CommandLine;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.MainCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        try {
            String[] args = ArgumentTokenizer.tokenize(userInput.trim());
            CommandLine commandLine = new CommandLine(new MainCommand());
            CommandLine.ParseResult parseResult = commandLine.parseArgs(args);
            CommandLine.ParseResult commandExecuted = parseResult.subcommand();

            if (commandExecuted == null) {
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

            return (Command) commandExecuted.commandSpec().userObject();
        } catch (CommandLine.ParameterException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
        }
    }

}
