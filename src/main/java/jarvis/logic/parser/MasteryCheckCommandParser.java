package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_MC_NUM;
import static jarvis.logic.parser.CliSyntax.PREFIX_MC_RES;

import java.util.stream.Stream;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.MasteryCheckCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.MasteryCheckResult;

/**
 * Parses input arguments and creates a new MasteryCheckCommand object
 */
public class MasteryCheckCommandParser implements Parser<MasteryCheckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MasteryCheckCommand
     * and returns a MasteryCheckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MasteryCheckCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MC_NUM, PREFIX_MC_RES);

        if (!arePrefixesPresent(argMultimap, PREFIX_MC_NUM, PREFIX_MC_RES) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MasteryCheckCommand.MESSAGE_USAGE));
        }

        Index index;
        MasteryCheckResult mcResult;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            int mcNumber = ParserUtil.parseMcNum(argMultimap.getValue(PREFIX_MC_NUM).get());
            boolean isPass = ParserUtil.parseMcResult(argMultimap.getValue(PREFIX_MC_RES).get());
            mcResult = new MasteryCheckResult(mcNumber, isPass);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MasteryCheckCommand.MESSAGE_USAGE),
                    pe);
        }

        return new MasteryCheckCommand(index, mcResult);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
