package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.HomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Homework;

/**
 * Parses input arguments and creates a new HomeworkCommand object
 */
public class HomeworkCommandParser implements Parser<HomeworkCommand> {

    public static final String MESSAGE_INVALID_EMPTY_FIELD = "HOMEWORK cannot be empty.";

    /**
     * Parses the given {@code String} of arguments in the context of the HomeworkCommand
     * and returns a HomeworkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HomeworkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_HOMEWORK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    HomeworkCommand.MESSAGE_USAGE), ive);
        }

        Optional<String> optHomework = argMultimap.getValue(PREFIX_HOMEWORK);
        // if no prefix h/ or field after h/ is empty, throw error
        if (optHomework.isEmpty() || optHomework.get().equals("")) {
            throw new ParseException(MESSAGE_INVALID_EMPTY_FIELD);
        }

        String homework = optHomework.get();

        return new HomeworkCommand(index, new Homework(homework));
    }
}
