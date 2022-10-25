package seedu.classify.logic.parser;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.commands.ViewStatsCommand;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.ClassPredicate;

/**
 * Parses input arguments and creates a ViewStatsCommand object
 * */
public class ViewStatsCommandParser implements Parser<ViewStatsCommand> {

    @Override
    public ViewStatsCommand parse(String args) throws ParseException {
        Prefix filterPrefix = new Prefix("filter/");
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_CLASS,
                CliSyntax.PREFIX_EXAM, filterPrefix);
        if (!argMultiMap.getValue(CliSyntax.PREFIX_CLASS).isPresent() || !argMultiMap.getValue(filterPrefix).isPresent()
                || !argMultiMap.getValue(CliSyntax.PREFIX_EXAM).isPresent()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewStatsCommand.MESSAGE_USAGE));
        }

        try {
            Class className = ParserUtil.parseClass(argMultiMap.getValue(CliSyntax.PREFIX_CLASS).get());
            boolean isFilterOn = ParserUtil.parseFilter(argMultiMap.getValue(filterPrefix).get().toUpperCase());
            return new ViewStatsCommand(new ClassPredicate(className), className.toString(),
                    argMultiMap.getValue(CliSyntax.PREFIX_EXAM).get(), isFilterOn);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewStatsCommand.MESSAGE_USAGE));
        }
    }
}
