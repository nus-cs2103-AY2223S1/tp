package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ModulesLeftCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ModulesLeftCommandParser implements Parser<ModulesLeftCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ModulesLeftCommand
     * and returns a ModulesLeftCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ModulesLeftCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ModulesLeftCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModulesLeftCommand.MESSAGE_USAGE), pe);
        }
    }
}
