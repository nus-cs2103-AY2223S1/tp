package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Lesson;

import java.time.LocalTime;
import java.util.Locale;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class LessonCommandParser implements Parser<LessonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LessonCommand
     * and returns an LessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LESSON, PREFIX_MOD, PREFIX_DAY, PREFIX_START, PREFIX_END);

        String preamble = argMultimap.getPreamble();

        if (!arePrefixesPresent(argMultimap, PREFIX_LESSON, PREFIX_MOD, PREFIX_DAY, PREFIX_START, PREFIX_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonCommand.MESSAGE_USAGE));
        }

        String type = argMultimap.getValue(PREFIX_LESSON).get().trim();
        String moduleName = argMultimap.getValue(PREFIX_MOD).get().trim();
        String day = argMultimap.getValue(PREFIX_DAY).get();
        String start = argMultimap.getValue(PREFIX_START).get();
        String end = argMultimap.getValue(PREFIX_END).get();
        Lesson lesson = ParserUtil.parseLesson(type, moduleName, day, start, end);

        if (preamble.equals("user")) {
            return new LessonUserCommand(lesson);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(preamble);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonCommand.MESSAGE_USAGE), pe);
        }

        return new LessonIndexCommand(lesson, index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
