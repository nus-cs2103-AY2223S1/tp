package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.parser.CliSyntax.ORDER_ASCENDING;
import static friday.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static friday.logic.parser.CliSyntax.PREFIX_MASTERYCHECK;
import static friday.logic.parser.CliSyntax.PREFIX_NAME;
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
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TELEGRAMHANDLE, PREFIX_CONSULTATION,
                        PREFIX_MASTERYCHECK);

        if (argMultimap.size() < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (argMultimap.size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortCommand.MESSAGE_TOO_MANY_CRITERIA));
        }

        Comparator<Student> comparator = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_NAME).get());
            comparator = (x, y) -> order.equals(ORDER_ASCENDING)
                    ? x.getName().compareTo(y.getName())
                    : y.getName().compareTo(x.getName());
        }
        if (argMultimap.getValue(PREFIX_TELEGRAMHANDLE).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_TELEGRAMHANDLE).get());
            comparator = (x, y) -> order.equals(ORDER_ASCENDING)
                    ? x.getTelegramHandle().compareTo(y.getTelegramHandle())
                    : y.getTelegramHandle().compareTo(x.getTelegramHandle());
        }
        if (argMultimap.getValue(PREFIX_CONSULTATION).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_CONSULTATION).get());
            comparator = (x, y) -> order.equals(ORDER_ASCENDING)
                    ? x.getConsultation().compareTo(y.getConsultation())
                    : y.getConsultation().compareTo(x.getConsultation());
        }
        if (argMultimap.getValue(PREFIX_MASTERYCHECK).isPresent()) {
            Order order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_MASTERYCHECK).get());
            comparator = (x, y) -> order.equals(ORDER_ASCENDING)
                    ? x.getMasteryCheck().compareTo(y.getMasteryCheck())
                    : y.getMasteryCheck().compareTo(x.getMasteryCheck());
        }

        assert !isNull(comparator);

        return new SortCommand(comparator);
    }
}
