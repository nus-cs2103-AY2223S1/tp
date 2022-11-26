package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CRITERIA;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.SortTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Criteria;


/**
 * SortTaskCommandParser represents a parser which parses the arguments
 * given by the user to create a SortTaskCommand object.
 */
public class SortTaskCommandParser implements Parser<SortTaskCommand> {
    private final Logger logger = LogsCenter.getLogger(SortTaskCommand.class);
    @Override
    public SortTaskCommand parse(String args) throws ParseException {
        logger.info("Parsing arguments for SortTaskCommand.");
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CRITERIA);
        if (!isPrefixPresent(argumentMultimap)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SortTaskCommand.MESSAGE_USAGE));
        }

        Criteria criteria = ParserUtil.parseCriteria(argumentMultimap.getValue(PREFIX_CRITERIA).get());
        return new SortTaskCommand(criteria);
    }

    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_CRITERIA).isPresent();
    }
}
