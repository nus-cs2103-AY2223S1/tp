package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;

import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.AddConditionCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.condition.Condition;

/**
 * Parses input arguments and creates a new AddConditionCommand.
 */
public class AddConditionCommandParser implements Parser<AddConditionCommand> {
    /**
     * Parses the given arguments in the context of the AddConditionCommand
     * and returns an AddConditionCommand object for execution.
     *
     * @param args The string of arguments given.
     * @return AddConditionCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddConditionCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CONDITION);

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            Condition condition = ParserUtil.parseCondition(argMultimap.getValue(PREFIX_CONDITION).orElseThrow());

            return new AddConditionCommand(index, condition);
        } catch (NoSuchElementException nse) {
            // Handles missing prefix
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConditionCommand.MESSAGE_USAGE));
        }
    }
}
