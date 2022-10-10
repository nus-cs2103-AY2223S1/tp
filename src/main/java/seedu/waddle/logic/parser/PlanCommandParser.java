package seedu.waddle.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.logic.commands.PlanCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;

public class PlanCommandParser {
    public PlanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PlanCommand.MESSAGE_USAGE), ive);
        }

        return new PlanCommand(index);
    }
}

