package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;

import seedu.address.logic.commands.ListPersonsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonSortField;


/**
 * Parses input arguments and creates a new ListPersonsCommand object.
 */
public class ListPersonsCommandParser implements Parser<ListPersonsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListPersonsCommand
     * and returns a {@code ListPersonsCommand} for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ListPersonsCommand parse(String args) throws ParseException {

        PersonSortField sortField;
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_SORT);

        // If sort field is missing
        if (!argMultiMap.getValue(PREFIX_SORT).isPresent()) {
            // Don't perform any sorting
            sortField = PersonSortField.sortByNoField();

        // Else sort field is present
        } else {
            sortField = ParserUtil.parsePersonSortField(argMultiMap.getValue(PREFIX_SORT).get());
        }

        return new ListPersonsCommand(sortField);
    }

}
