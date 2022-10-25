package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.parser.CliSyntax.PREFIX_DEBT;
import static paymelah.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.DeleteDebtCommand;
import paymelah.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDebtCommand object
 */
public class DeleteDebtCommandParser implements Parser<DeleteDebtCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDebtCommand
     * and returns a DeleteDebtCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDebtCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DEBT);

        if (argMultimap.getPreamble().isEmpty()
                || !arePrefixesPresent(argMultimap, PREFIX_DEBT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDebtCommand.MESSAGE_USAGE));
        }

        try {
            Index personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            List<String> debtIndexList = Arrays.stream(argMultimap.getValue(PREFIX_DEBT).get().split(" "))
                    .collect(Collectors.toList());
            Set<Index> debtIndexSet = ParserUtil.parseIndexes(debtIndexList);
            return new DeleteDebtCommand(personIndex, debtIndexSet);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDebtCommand.MESSAGE_USAGE), pe);
        }
    }


}
