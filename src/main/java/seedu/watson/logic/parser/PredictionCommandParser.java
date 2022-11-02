package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_FUTURE_ASSESSMENT_DIFFICULTY;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.stream.Stream;

import seedu.watson.logic.commands.PredictionCommand;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.Name;
import seedu.watson.model.student.subject.Subject;

/**
 * Parses input arguments and creates a new PredictionCommand object
 */
public class PredictionCommandParser implements Parser<PredictionCommand> {

    @Override
    public PredictionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUBJECT,
                PREFIX_FUTURE_ASSESSMENT_DIFFICULTY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SUBJECT,
            PREFIX_FUTURE_ASSESSMENT_DIFFICULTY)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PredictionCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Subject subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        double difficulty = ParserUtil.parseFutureAssessmentDifficulty(argMultimap.getValue(
            PREFIX_FUTURE_ASSESSMENT_DIFFICULTY).get());

        return new PredictionCommand(name, subject.getSubjectName(), difficulty);
    }
}
