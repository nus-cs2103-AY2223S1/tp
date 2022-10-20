package friday.logic.parser;

import static java.util.Objects.requireNonNull;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.logic.commands.EditCommand;
import friday.logic.commands.MarkMasteryCheckCommand;
import friday.logic.parser.exceptions.ParseException;

/**
 * class that parses a mark command.
 */
public class MarkMasteryCheckCommandParser implements Parser<MarkMasteryCheckCommand> {
    /**
     * parses
     *
     * @param args
     * @return MarkMasteryCheckCommand
     * @throws ParseException
     */
    @Override
    public MarkMasteryCheckCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_TELEGRAMHANDLE,
                        CliSyntax.PREFIX_CONSULTATION, CliSyntax.PREFIX_MASTERYCHECK, CliSyntax.PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE),
                    pe);
        }

        return new MarkMasteryCheckCommand(index);
    }
}
