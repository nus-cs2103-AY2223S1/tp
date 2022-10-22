package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_NOTES;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.util.Optional;
import java.util.stream.Stream;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.AddNotesCommand;
import jarvis.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddNotesCommand object
 */
public class AddNotesCommandParser implements Parser<AddNotesCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddNotesCommand
     * and returns a AddNotesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNotesCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTES, PREFIX_LESSON_INDEX,
                PREFIX_STUDENT_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTES, PREFIX_LESSON_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNotesCommand.MESSAGE_USAGE));
        }

        Index lessonIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_LESSON_INDEX).get());
        Optional<String> studentPrefix = argMultimap.getValue(PREFIX_STUDENT_INDEX);
        Index studentIndex = studentPrefix.isPresent() ? ParserUtil.parseIndex(studentPrefix.get()) : null;
        String notes = argMultimap.getValue(PREFIX_NOTES).get();

        return new AddNotesCommand(lessonIndex, studentIndex, notes);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

