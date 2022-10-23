package seedu.address.logic.parser.student;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.student.FindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Student;
import seedu.address.model.student.predicates.ModuleContainsKeywordPredicate;
import seedu.address.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.student.predicates.StudentIdContainsKeywordPredicate;
import seedu.address.model.student.predicates.TutorialContainsKeywordPredicate;

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
        ParserUtil.assertPrefixesPresentNotEmpty(argMultimap,
                PREFIX_NAME, PREFIX_ID, PREFIX_MODULE, PREFIX_TUTORIAL);

        List<Predicate<Student>> predicates = new ArrayList<>();

        Prefix[] prefixes = {PREFIX_NAME, PREFIX_ID, PREFIX_MODULE, PREFIX_TUTORIAL};
        for (Prefix prefix : prefixes) {
            Optional<String> str = argMultimap.getValue(prefix);
            if (str.isEmpty()) {
                continue;
            }
            String trimmedKeywords = str.get().trim();
            if (prefix.equals(PREFIX_NAME)) {
                String[] nameKeywords = trimmedKeywords.split("\\s+");
                predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            } else if (prefix.equals(PREFIX_ID)) {
                predicates.add(new StudentIdContainsKeywordPredicate(trimmedKeywords));
            } else if (prefix.equals(PREFIX_MODULE)) {
                predicates.add(new ModuleContainsKeywordPredicate(trimmedKeywords));
            } else if (prefix.equals(PREFIX_TUTORIAL)) {
                predicates.add(new TutorialContainsKeywordPredicate(trimmedKeywords));
            }
        }

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
