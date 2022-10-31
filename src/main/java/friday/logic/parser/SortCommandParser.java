package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.parser.CliSyntax.ORDER_ASCENDING;
import static friday.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static friday.logic.parser.CliSyntax.PREFIX_FINALS;
import static friday.logic.parser.CliSyntax.PREFIX_MASTERYCHECK;
import static friday.logic.parser.CliSyntax.PREFIX_MIDTERM;
import static friday.logic.parser.CliSyntax.PREFIX_NAME;
import static friday.logic.parser.CliSyntax.PREFIX_PRACTICAL;
import static friday.logic.parser.CliSyntax.PREFIX_RA1;
import static friday.logic.parser.CliSyntax.PREFIX_RA2;
import static friday.logic.parser.CliSyntax.PREFIX_TELEGRAMHANDLE;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import friday.logic.commands.SortCommand;
import friday.logic.parser.exceptions.ParseException;
import friday.model.student.Student;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TELEGRAMHANDLE, PREFIX_CONSULTATION,
                        PREFIX_MASTERYCHECK, PREFIX_RA1, PREFIX_RA2, PREFIX_MIDTERM, PREFIX_PRACTICAL, PREFIX_FINALS);

        if (argMultimap.size() < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (argMultimap.size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortCommand.MESSAGE_TOO_MANY_CRITERIA));
        }

        return parseMain(argMultimap);
    }

    private SortCommand parseMain(ArgumentMultimap argMultimap) throws ParseException {
        assert argMultimap.size() == 1;

        Prefix prefix = argMultimap.getFirstPrefix().orElse(null);

        // Since parse asserts that there will be one valid prefix in the argMultimap, prefix object should not be null
        assert !isNull(prefix);

        Comparator<Student> comparator = getComparator(argMultimap, prefix);

        assert !isNull(comparator);

        return new SortCommand(comparator);
    }

    private Comparator<Student> getComparator(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        if (argMultimap.getValue(prefix).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(prefix).get());
            return (student1, student2) -> order.equals(ORDER_ASCENDING)
                    ? compare(prefix, student1, student2)
                    : compare(prefix, student2, student1);
        }
        return null;
    }

    private int compare(Prefix prefix, Student student1, Student student2) {
        if (prefix.equals(PREFIX_NAME)) {
            return student1.getName().compareTo(student2.getName());
        } else if (prefix.equals(PREFIX_TELEGRAMHANDLE)) {
            return student1.getTelegramHandle().compareTo(student2.getTelegramHandle());
        } else if (prefix.equals(PREFIX_CONSULTATION)) {
            return student1.getConsultation().compareTo(student2.getConsultation());
        } else if (prefix.equals(PREFIX_MASTERYCHECK)) {
            return student1.getMasteryCheck().compareTo(student2.getMasteryCheck());
        } else {
            return compareGrades(prefix, student1, student2);
        }
    }

    private int compareGrades(Prefix prefix, Student student1, Student student2) {
        String exam = "";
        if (prefix.equals(PREFIX_RA1)) {
            exam = "RA1";
        } else if (prefix.equals(PREFIX_RA2)) {
            exam = "RA2";
        } else if (prefix.equals(PREFIX_MIDTERM)) {
            exam = "Midterm";
        } else if (prefix.equals(PREFIX_PRACTICAL)) {
            exam = "Practical";
        } else if (prefix.equals(PREFIX_FINALS)) {
            exam = "Finals";
        }
        return Double.compare(
                Double.parseDouble(student1.getGradesList().getGrade(exam).getScore()),
                Double.parseDouble(student2.getGradesList().getGrade(exam).getScore()));
    }
}

