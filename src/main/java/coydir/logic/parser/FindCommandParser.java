package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static coydir.logic.parser.CliSyntax.PREFIX_NAME;
import static coydir.logic.parser.CliSyntax.PREFIX_POSITION;

import java.util.stream.Stream;

import coydir.logic.commands.FindCommand;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.person.Name;
import coydir.model.person.PersonMatchesKeywordsPredicate;
import coydir.model.person.Position;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_POSITION, PREFIX_DEPARTMENT);

        if (!isPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_POSITION, PREFIX_DEPARTMENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String keywordName = "";
        String keywordPosition = "";
        String keywordDepartment = "";
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            keywordName = ParserUtil.parseKeyword(argMultimap.getValue(PREFIX_NAME).get());
            if (!Name.isValidName(keywordName)) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
        }
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            keywordPosition = ParserUtil.parseKeyword(argMultimap.getValue(PREFIX_POSITION).get());
        }
        if (argMultimap.getValue(PREFIX_DEPARTMENT).isPresent()) {
            keywordDepartment = ParserUtil.parseKeyword(argMultimap.getValue(PREFIX_DEPARTMENT).get());
        }

        return new FindCommand(new PersonMatchesKeywordsPredicate(keywordName, keywordPosition, keywordDepartment));
    }

    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
