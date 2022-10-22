package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.parser.CliSyntax.PREFIX_ALIAS;
import static java.util.Objects.requireNonNull;

import friday.logic.commands.AliasCommand;
import friday.logic.commands.UnaliasCommand;
import friday.logic.parser.exceptions.ParseException;
import friday.model.alias.Alias;

/**
 * Parses input arguments and creates a new UnaliasCommand object
 */
public class UnaliasCommandParser implements Parser<UnaliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnaliasCommand
     * and returns an UnaliasCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnaliasCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ALIAS);

        Alias alias;

        if (!argMultimap.getValue(PREFIX_ALIAS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS).get());

        return new UnaliasCommand(alias);
    }
}

