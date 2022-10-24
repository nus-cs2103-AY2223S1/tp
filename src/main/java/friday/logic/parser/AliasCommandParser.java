package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.parser.CliSyntax.PREFIX_ALIAS;
import static friday.logic.parser.CliSyntax.PREFIX_RESERVED_KEYWORD;
import static java.util.Objects.requireNonNull;

import friday.logic.commands.AliasCommand;
import friday.logic.parser.exceptions.ParseException;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;

/**
 * Parses input arguments and creates a new AliasCommand object
 */
public class AliasCommandParser implements Parser<AliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns an AliasCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AliasCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_RESERVED_KEYWORD);

        Alias alias;
        ReservedKeyword reservedKeyword;

        if (!argMultimap.getValue(PREFIX_ALIAS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_RESERVED_KEYWORD).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS).get());
        reservedKeyword = ParserUtil.parseReservedKeyword(argMultimap.getValue(PREFIX_RESERVED_KEYWORD).get());

        return new AliasCommand(alias, reservedKeyword);
    }
}

