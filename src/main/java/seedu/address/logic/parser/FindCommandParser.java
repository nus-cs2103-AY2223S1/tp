package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.commons.util.FunctionalInterfaces.Changer;
import seedu.address.commons.util.FunctionalInterfaces.Retriever;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.NameContainsKeywordsPredicate;

/**
 * Parses user input and returns a find command for finding items
 */
public class FindCommandParser<T extends DisplayItem> implements Parser<FindCommand<T>> {

    private final Changer<Predicate<T>> changer;
    private final Retriever<Integer> getSize;

    /**
     * Constructor to create a find command parser
     *
     * @param changer sam to set boolean of observable list
     * @param getSize sam to receive length of observable list
     */
    public FindCommandParser(Changer<Predicate<T>> changer, Retriever<Integer> getSize) {
        this.changer = changer;
        this.getSize = getSize;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand and returns a
     * FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand<T> parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new FindCommand<T>(null, changer, getSize);
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand<T>(
            new NameContainsKeywordsPredicate<T>(Arrays.asList(nameKeywords)),
            changer,
            getSize);
    }

}
