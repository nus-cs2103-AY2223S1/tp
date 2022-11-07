package seedu.classify.logic.parser;

import java.util.stream.Stream;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.commands.ViewStatsCommand;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.student.Class;


/**
 * Parses input arguments and creates a ViewStatsCommand object
 * */
public class ViewStatsCommandParser implements Parser<ViewStatsCommand> {

    @Override
    public ViewStatsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_CLASS,
                CliSyntax.PREFIX_EXAM, CliSyntax.PREFIX_FILTER);
        if (!arePrefixesPresent(argMultiMap, CliSyntax.PREFIX_CLASS, CliSyntax.PREFIX_EXAM, CliSyntax.PREFIX_FILTER)) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewStatsCommand.MESSAGE_USAGE));
        }

        try {
            Class className = ParserUtil.parseClass(argMultiMap.getValue(CliSyntax.PREFIX_CLASS).get());
            String exam = ParserUtil.parseExamQuery(argMultiMap.getValue(CliSyntax.PREFIX_EXAM).get());
            boolean isFilterOn = ParserUtil.parseFilter(
                    argMultiMap.getValue(CliSyntax.PREFIX_FILTER).get().toUpperCase());
            return new ViewStatsCommand(className, exam, isFilterOn);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(pe.getMessage()));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
