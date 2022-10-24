package seedu.foodrem.logic.parser.generalcommandparser;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.enums.CommandType;
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
            return new HelpCommand(HelpCommand.getGeneralHelpMessage());
        }

        CommandType commandType = CommandType.parseWord(commandWordString);
        if (commandType.equals(CommandType.DEFAULT)) {
            return new HelpCommand(String.format(HelpCommand.NOT_A_COMMAND, commandWordString));
        }

        return new HelpCommand(HelpCommand.getCommandHelpMessage(commandType));
    }
}
