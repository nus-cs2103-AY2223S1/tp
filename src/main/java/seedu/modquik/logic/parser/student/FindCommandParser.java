package seedu.modquik.logic.parser.student;

import static seedu.modquik.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.modquik.logic.commands.student.FindCommand;
import seedu.modquik.logic.parser.ArgumentMultimap;
import seedu.modquik.logic.parser.ArgumentTokenizer;
import seedu.modquik.logic.parser.Parser;
import seedu.modquik.logic.parser.ParserUtil;
import seedu.modquik.logic.parser.Prefix;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.student.predicates.ModuleContainsKeywordPredicate;
import seedu.modquik.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.modquik.model.student.predicates.StudentIdContainsKeywordPredicate;
import seedu.modquik.model.student.predicates.TutorialContainsKeywordPredicate;

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

        ParserUtil.assertAnyPrefixesPresent(argMultimap, FindCommand.MESSAGE_USAGE,
                PREFIX_NAME, PREFIX_ID, PREFIX_MODULE, PREFIX_TUTORIAL);
        ParserUtil.assertPrefixesPresentNotEmpty(argMultimap, FindCommand.MESSAGE_USAGE,
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
