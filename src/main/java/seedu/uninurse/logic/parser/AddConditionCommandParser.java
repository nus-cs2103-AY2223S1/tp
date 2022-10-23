package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;

import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.AddConditionCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.condition.Condition;

/**
 * Parses input arguments and creates a new {@code AddConditionCommand}.
 */
public class AddConditionCommandParser implements Parser<AddConditionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddConditionCommand
     * and returns an AddConditionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddConditionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CONDITION);

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            Condition condition = ParserUtil.parseCondition(argMultimap.getValue(PREFIX_CONDITION).orElseThrow());
            return new AddConditionCommand(index, condition);
            // TODO: display specific error messages
        } catch (ParseException pe) { // handles invalid indices and conditions
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddConditionCommand.MESSAGE_USAGE), pe);
        } catch (NoSuchElementException nse) { // Handles missing prefix
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConditionCommand.MESSAGE_USAGE));
        }
    }
}
