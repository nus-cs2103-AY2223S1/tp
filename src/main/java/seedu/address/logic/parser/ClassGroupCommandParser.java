package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ClassGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.ClassGroup;

/**
 * Parses input arguments and creates a new ClassGroupCommand object
 */
public class ClassGroupCommandParser implements Parser<ClassGroupCommand> {

    public static final String INFO_NOT_AVAILABLE = "NA";

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ClassGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLASS_GROUP);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClassGroupCommand.MESSAGE_USAGE), ive);
        }

        String classGroup = argMultimap.getValue(PREFIX_CLASS_GROUP).orElse(INFO_NOT_AVAILABLE);

        return new ClassGroupCommand(index, new ClassGroup(classGroup.isBlank() ? INFO_NOT_AVAILABLE : classGroup));
    }
}
