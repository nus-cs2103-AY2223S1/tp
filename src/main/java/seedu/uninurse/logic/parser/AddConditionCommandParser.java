package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.commons.exceptions.IllegalValueException;
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

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddConditionCommand.MESSAGE_USAGE), ive);
        }

        String condition = argMultimap.getValue(PREFIX_CONDITION).get();

        return new AddConditionCommand(index, new Condition(condition));
    }
}
