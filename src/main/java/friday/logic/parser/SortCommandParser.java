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
        Comparator<Student> comparator = null;

        assert argMultimap.size() == 1;

        // Since there is only one prefix in argMultimap, only one of the following getComparator methods will
        // return a proper comparator
        comparator = getNameComparator(argMultimap, comparator);
        comparator = getTelegramHandleComparator(argMultimap, comparator);
        comparator = getConsultationComparator(argMultimap, comparator);
        comparator = getMasteryCheckComparator(argMultimap, comparator);
        comparator = getRa1Comparator(argMultimap, comparator);
        comparator = getRa2Comparator(argMultimap, comparator);
        comparator = getMidtermComparator(argMultimap, comparator);
        comparator = getPracticalComparator(argMultimap, comparator);
        comparator = getFinalsComparator(argMultimap, comparator);

        assert !isNull(comparator);

        return new SortCommand(comparator);
    }

    private Comparator<Student> getNameComparator(ArgumentMultimap argMultimap,
                                                  Comparator<Student> comparator) throws ParseException {
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_NAME).get());
            comparator = getNameComparator(order);
        }
        return comparator;
    }

    private Comparator<Student> getNameComparator(Order order) {
        return (x, y) -> order.equals(ORDER_ASCENDING)
                ? x.getName().compareTo(y.getName())
                : y.getName().compareTo(x.getName());
    }

    private Comparator<Student> getTelegramHandleComparator(ArgumentMultimap argMultimap,
                                                            Comparator<Student> comparator) throws ParseException {
        if (argMultimap.getValue(PREFIX_TELEGRAMHANDLE).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_TELEGRAMHANDLE).get());
            comparator = getTelegramHandleComparator(order);
        }
        return comparator;
    }

    private Comparator<Student> getTelegramHandleComparator(Order order) {
        return (x, y) -> order.equals(ORDER_ASCENDING)
                ? x.getTelegramHandle().compareTo(y.getTelegramHandle())
                : y.getTelegramHandle().compareTo(x.getTelegramHandle());
    }

    private Comparator<Student> getConsultationComparator(ArgumentMultimap argMultimap,
                                                          Comparator<Student> comparator) throws ParseException {
        if (argMultimap.getValue(PREFIX_CONSULTATION).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_CONSULTATION).get());
            comparator = getConsultationComparator(order);
        }
        return comparator;
    }

    private Comparator<Student> getConsultationComparator(Order order) {
        return (x, y) -> order.equals(ORDER_ASCENDING)
                ? x.getConsultation().compareTo(y.getConsultation())
                : y.getConsultation().compareTo(x.getConsultation());
    }

    private Comparator<Student> getMasteryCheckComparator(ArgumentMultimap argMultimap,
                                                          Comparator<Student> comparator) throws ParseException {
        if (argMultimap.getValue(PREFIX_MASTERYCHECK).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_MASTERYCHECK).get());
            comparator = getMasteryCheckComparator(order);
        }
        return comparator;
    }

    private Comparator<Student> getMasteryCheckComparator(Order order) {
        return (x, y) -> order.equals(ORDER_ASCENDING)
                ? x.getMasteryCheck().compareTo(y.getMasteryCheck())
                : y.getMasteryCheck().compareTo(x.getMasteryCheck());
    }

    private Comparator<Student> getRa1Comparator(ArgumentMultimap argMultimap,
                                                 Comparator<Student> comparator) throws ParseException {
        if (argMultimap.getValue(PREFIX_RA1).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_RA1).get());
            comparator = getRa1Comparator(order);
        }
        return comparator;
    }

    private Comparator<Student> getRa1Comparator(Order order) {
        return (x, y) -> order.equals(ORDER_ASCENDING)
                ? Integer.compareUnsigned(Integer.parseInt( x.getGradesList().getGrade("RA1").score),
                Integer.parseInt(y.getGradesList().getGrade("RA1").score))
                : Integer.compareUnsigned(Integer.parseInt( y.getGradesList().getGrade("RA1").score),
                Integer.parseInt(x.getGradesList().getGrade("RA1").score));
    }

    private Comparator<Student> getRa2Comparator(ArgumentMultimap argMultimap,
                                                 Comparator<Student> comparator) throws ParseException {
        if (argMultimap.getValue(PREFIX_RA2).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_RA2).get());
            comparator = getRa2Comparator(order);
        }
        return comparator;
    }

    private Comparator<Student> getRa2Comparator(Order order) {
        return (x, y) -> order.equals(ORDER_ASCENDING)
                ? Integer.compareUnsigned(Integer.parseInt( x.getGradesList().getGrade("RA2").score),
                  Integer.parseInt(y.getGradesList().getGrade("RA2").score))
                : Integer.compareUnsigned(Integer.parseInt( y.getGradesList().getGrade("RA2").score),
                  Integer.parseInt(x.getGradesList().getGrade("RA2").score));
    }

    private Comparator<Student> getMidtermComparator(ArgumentMultimap argMultimap,
                                                     Comparator<Student> comparator) throws ParseException {
        if (argMultimap.getValue(PREFIX_MIDTERM).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_MIDTERM).get());
            comparator = getMidtermComparator(order);
        }
        return comparator;
    }

    private Comparator<Student> getMidtermComparator(Order order) {
        return (x, y) -> order.equals(ORDER_ASCENDING)
                ? Integer.compareUnsigned(Integer.parseInt( x.getGradesList().getGrade("Midterm").score),
                Integer.parseInt(y.getGradesList().getGrade("Midterm").score))
                : Integer.compareUnsigned(Integer.parseInt( y.getGradesList().getGrade("Midterm").score),
                Integer.parseInt(x.getGradesList().getGrade("Midterm").score));
    }

    private Comparator<Student> getPracticalComparator(ArgumentMultimap argMultimap,
                                                       Comparator<Student> comparator) throws ParseException {
        if (argMultimap.getValue(PREFIX_PRACTICAL).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_PRACTICAL).get());
            comparator = getPracticalComparator(order);
        }
        return comparator;
    }

    private Comparator<Student> getPracticalComparator(Order order) {
        return (x, y) -> order.equals(ORDER_ASCENDING)
                ? Integer.compareUnsigned(Integer.parseInt( x.getGradesList().getGrade("Practical").score),
                Integer.parseInt(y.getGradesList().getGrade("Practical").score))
                : Integer.compareUnsigned(Integer.parseInt( y.getGradesList().getGrade("Practical").score),
                Integer.parseInt(x.getGradesList().getGrade("Practical").score));
    }

    private Comparator<Student> getFinalsComparator(ArgumentMultimap argMultimap,
                                                    Comparator<Student> comparator) throws ParseException {
        if (argMultimap.getValue(PREFIX_FINALS).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_FINALS).get());
            comparator = getFinalsComparator(order);
        }
        return comparator;
    }

    private Comparator<Student> getFinalsComparator(Order order) {
        return (x, y) -> order.equals(ORDER_ASCENDING)
                ? Integer.compareUnsigned(Integer.parseInt( x.getGradesList().getGrade("Finals").score),
                Integer.parseInt(y.getGradesList().getGrade("Finals").score))
                : Integer.compareUnsigned(Integer.parseInt( y.getGradesList().getGrade("Finals").score),
                Integer.parseInt(x.getGradesList().getGrade("Finals").score));
    }
}

