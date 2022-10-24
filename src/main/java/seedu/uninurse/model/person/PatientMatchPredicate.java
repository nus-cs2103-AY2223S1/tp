package seedu.uninurse.model.person;

import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.uninurse.logic.parser.ArgumentMultimap;
import seedu.uninurse.model.condition.ConditionContainsKeywordsPredicate;
import seedu.uninurse.model.tag.TagContainsKeywordsPredicate;
import seedu.uninurse.model.task.TaskContainsKeywordsPredicate;

/**
 * Tests that a {@code Patient} matches with all descriptors.
 */
public class PatientMatchPredicate implements Predicate<Patient> {
    private final List<Predicate<? super Patient>> predicates;

    /**
     * Constructs a {@code PatientMatchPredicate}
     * which tests {@code Patient} with given keywords.
     *
     * @param keywords
     */
    public PatientMatchPredicate(List<String> keywords) {
        this(keywords, new ArgumentMultimap());
    }

    /**
     * Constructs a {@code PatientMatchPredicate}
     * which tests {@code Patient} with all given descriptors.
     *
     * @param keywords the keywords
     * @param arg      the argument multimap
     */
    public PatientMatchPredicate(List<String> keywords, ArgumentMultimap arg) {
        this.predicates = new ArrayList<>();
        addPredicate(keywords, x -> new PatientContainsKeywordsPredicate(x));
        addPredicate(arg.getAllValues(PREFIX_NAME), x -> new NameContainsKeywordsPredicate(x));
        addPredicate(arg.getAllValues(PREFIX_PHONE), x -> new PhoneContainsKeywordsPredicate(x));
        addPredicate(arg.getAllValues(PREFIX_EMAIL), x -> new EmailContainsKeywordsPredicate(x));
        addPredicate(arg.getAllValues(PREFIX_ADDRESS), x -> new AddressContainsKeywordsPredicate(x));
        addPredicate(arg.getAllValues(PREFIX_TAG), x -> new TagContainsKeywordsPredicate(x));
        addPredicate(arg.getAllValues(PREFIX_CONDITION), x -> new ConditionContainsKeywordsPredicate(x));
        addPredicate(arg.getAllValues(PREFIX_TASK_DESCRIPTION), x -> new TaskContainsKeywordsPredicate(x));
    }

    private void addPredicate(List<String> list, Function<List<String>, Predicate<? super Patient>> function) {
        if (!list.isEmpty()) {
            predicates.add(function.apply(list));
        }
    }

    @Override
    public boolean test(Patient person) {
        return predicates.stream().allMatch(x -> x.test(person));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientMatchPredicate // instanceof handles nulls
                && predicates.equals(((PatientMatchPredicate) other).predicates)); // state check
    }
}
