package friday.logic.parser;

import static java.util.Objects.requireNonNull;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.logic.commands.UnmarkMasteryCheckCommand;
import friday.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkMasteryCheckCommand object
 */
public class UnmarkMasteryCheckCommandParser implements Parser<UnmarkMasteryCheckCommand> {
    /**
     * parses
     *
     * @param args
     * @return MarkMasteryCheckCommand
     * @throws ParseException
     */
    @Override
    public UnmarkMasteryCheckCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_TELEGRAMHANDLE,
                        CliSyntax.PREFIX_CONSULTATION, CliSyntax.PREFIX_MASTERYCHECK, CliSyntax.PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    UnmarkMasteryCheckCommand.MESSAGE_USAGE), pe);
        }

        return new UnmarkMasteryCheckCommand(index);
    }
}
