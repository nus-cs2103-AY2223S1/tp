package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_FINAL_ASST;
import static jarvis.logic.parser.CliSyntax.PREFIX_MIDTERM;
import static jarvis.logic.parser.CliSyntax.PREFIX_PRACTICAL_ASST;
import static jarvis.logic.parser.CliSyntax.PREFIX_RA1;
import static jarvis.logic.parser.CliSyntax.PREFIX_RA2;
import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.GradeCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.Assessment;
import jarvis.model.GradeProfile;

/**
 * Parses input arguments and creates a new MasteryCheckCommand object
 */
public class GradeCommandParser implements Parser<GradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MasteryCheckCommand
     * and returns a MasteryCheckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RA1, PREFIX_RA2, PREFIX_MIDTERM,
                        PREFIX_PRACTICAL_ASST, PREFIX_FINAL_ASST);

        if (!anyPrefixesPresent(argMultimap, PREFIX_RA1, PREFIX_RA2, PREFIX_MIDTERM,
                PREFIX_PRACTICAL_ASST, PREFIX_FINAL_ASST) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE),
                    pe);
        }

        GradeProfile gp = new GradeProfile();

        if (argMultimap.getValue(PREFIX_RA1).isPresent()) {
            gp.setRa1(ParserUtil.parseMarks(argMultimap.getValue(PREFIX_RA1).get(), Assessment.RA1));
        }
        if (argMultimap.getValue(PREFIX_RA2).isPresent()) {
            gp.setRa2(ParserUtil.parseMarks(argMultimap.getValue(PREFIX_RA2).get(), Assessment.RA2));
        }
        if (argMultimap.getValue(PREFIX_MIDTERM).isPresent()) {
            gp.setMidterm(ParserUtil.parseMarks(argMultimap.getValue(PREFIX_MIDTERM).get(), Assessment.MIDTERM));
        }
        if (argMultimap.getValue(PREFIX_PRACTICAL_ASST).isPresent()) {
            gp.setPracticalAssessment(ParserUtil.parseMarks(argMultimap.getValue(PREFIX_PRACTICAL_ASST).get(),
                    Assessment.PRACTICAL_ASSESSMENT));
        }
        if (argMultimap.getValue(PREFIX_FINAL_ASST).isPresent()) {
            gp.setFinalAssessment(ParserUtil.parseMarks(argMultimap.getValue(PREFIX_FINAL_ASST).get(),
                    Assessment.FINAL_ASSESSMENT));
        }

        return new GradeCommand(index, gp);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
