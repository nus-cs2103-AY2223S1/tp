package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.student.FindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Student;
import seedu.address.model.student.predicates.NameContainsKeywordsPredicate;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_MODULE, PREFIX_TUTORIAL);

        ParserUtil.assertAnyPrefixesPresent(argMultimap,
                PREFIX_NAME, PREFIX_ID, PREFIX_MODULE, PREFIX_TUTORIAL);

        String trimmedName = argMultimap.getValue(PREFIX_NAME).get().trim();
        String[] nameKeywords = trimmedName.split("\\s+");

        List<Predicate<Student>> predicates = new ArrayList<>();
        predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        predicates.add(new NameContainsKeywordsPredicate(List.of(new String[]{"test"})));


        Predicate<Student> chainedPredicates = student -> {
            for (Predicate<Student> predicate : predicates) {
                if (!predicate.test(student)) {
                    return false;
                }
            }
            return true;
        };
        return new FindCommand(chainedPredicates);
    }

}
