package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.LessonPlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LessonPlanCommand object
 */
public class LessonPlanCommandParser implements Parser<LessonPlanCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LessonPlanCommand
     * and returns a LessonPlanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonPlanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_LESSON_PLAN);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LessonPlanCommand.MESSAGE_USAGE), ive);
        }

        Optional<String> optLesson = argMultimap.getValue(PREFIX_LESSON_PLAN);

        if (optLesson.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonPlanCommand.MESSAGE_USAGE));
        }

        String lessonPlan = optLesson.get();

        return new LessonPlanCommand(index, ParserUtil.parseLessonPlan(lessonPlan));
    }
}
