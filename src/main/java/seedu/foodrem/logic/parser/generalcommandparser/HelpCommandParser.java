package seedu.foodrem.logic.parser.generalcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.HELP_FOR_ALL_COMMANDS;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.HELP_FOR_SPECIFIC_COMMAND;
import static seedu.foodrem.logic.commands.generalcommands.HelpCommand.NOT_A_COMMAND;

import seedu.foodrem.enums.CommandWord;
import seedu.foodrem.logic.commands.generalcommands.HelpCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String commandWordString = argMultimap.getPreamble();

        if (commandWordString.isBlank()) {
            return new HelpCommand(HELP_FOR_ALL_COMMANDS);
        }

        CommandWord commandWord = CommandWord.parseWord(commandWordString);
        if (commandWord.equals(CommandWord.DEFAULT)) {
            return new HelpCommand(String.format(NOT_A_COMMAND, commandWordString));
        }

        return new HelpCommand(String.format(HELP_FOR_SPECIFIC_COMMAND, commandWord.getHelp()));
    }
}
