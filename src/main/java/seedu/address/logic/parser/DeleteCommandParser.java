package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CATEGORY;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonCategory;

/**
 * Parses input arguments and creates a new DeleteCommand object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_PERSON_CATEGORY, PREFIX_INDEX);

            if (!arePrefixesPresent(argMultimap, PREFIX_PERSON_CATEGORY, PREFIX_INDEX)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }

            PersonCategory personCategory =
                    ParserUtil.parsePersonCategory(argMultimap.getValue(PREFIX_PERSON_CATEGORY).get());
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

            return new DeleteCommand(personCategory, index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
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
