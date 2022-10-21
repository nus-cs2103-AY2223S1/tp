package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_NOTES_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.util.stream.Stream;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.DeleteNotesCommand;
import jarvis.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteNotesCommand object
 */
public class DeleteNotesCommandParser implements Parser<DeleteNotesCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteNotesCommand
     * and returns a DeleteNotesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteNotesCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTES_INDEX, PREFIX_LESSON_INDEX,
                PREFIX_STUDENT_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTES_INDEX, PREFIX_LESSON_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNotesCommand.MESSAGE_USAGE));
        }

        Index noteIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_NOTES_INDEX).get());
        Index lessonIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_LESSON_INDEX).get());
        String studentPrefix = argMultimap.getValue(PREFIX_STUDENT_INDEX).orElse("-1");
        Index studentIndex = studentPrefix.equals("-1") ? null : ParserUtil.parseIndex(studentPrefix);

        return new DeleteNotesCommand(noteIndex, lessonIndex, studentIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

