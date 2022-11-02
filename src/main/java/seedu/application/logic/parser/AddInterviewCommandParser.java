package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_TIME;
import static seedu.application.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROUND;

import java.util.stream.Stream;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.AddInterviewCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.logic.parser.exceptions.ParseIntegerOverflowException;
import seedu.application.model.application.interview.Interview;
import seedu.application.model.application.interview.InterviewDate;
import seedu.application.model.application.interview.InterviewTime;
import seedu.application.model.application.interview.Location;
import seedu.application.model.application.interview.Round;

/**
 * Parses input arguments and creates a new AddInterviewCommand object
 */
public class AddInterviewCommandParser implements Parser<AddInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddInterviewCommand
     * and returns an AddInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInterviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROUND, PREFIX_INTERVIEW_DATE,
                PREFIX_INTERVIEW_TIME, PREFIX_LOCATION);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseIntegerOverflowException e) {
            // Rethrow exception if index formatted correctly but too large to store in an int
            throw e;
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE),
                    pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_ROUND, PREFIX_INTERVIEW_DATE, PREFIX_INTERVIEW_TIME,
                PREFIX_LOCATION) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE));
        }

        Round round = ParserUtil.parseRound(argMultimap.getValue(PREFIX_ROUND).get());
        InterviewDate interviewDate = ParserUtil.parseInterviewDate(argMultimap.getValue(PREFIX_INTERVIEW_DATE).get());
        InterviewTime interviewTime = ParserUtil.parseInterviewTime(argMultimap.getValue(PREFIX_INTERVIEW_TIME).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());

        Interview interview = new Interview(round, interviewDate, interviewTime, location);

        return new AddInterviewCommand(index, interview);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
