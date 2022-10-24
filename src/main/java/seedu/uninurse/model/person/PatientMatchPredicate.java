package seedu.uninurse.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.uninurse.model.condition.ConditionContainsKeywordsPredicate;
import seedu.uninurse.model.tag.TagContainsKeywordsPredicate;
import seedu.uninurse.model.task.TaskContainsKeywordsPredicate;

/**
 * Tests that a {@code Patient} matches with all descriptors.
 */
public class PatientMatchPredicate implements Predicate<Patient> {
    private final PatientContainsKeywordsPredicate patientContainsKeywordsPredicate;
    private final NameContainsKeywordsPredicate nameContainsKeywordsPredicate;
    private final PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate;
    private final EmailContainsKeywordsPredicate emailContainsKeywordsPredicate;
    private final AddressContainsKeywordsPredicate addressContainsKeywordsPredicate;
    private final TagContainsKeywordsPredicate tagContainsKeywordsPredicate;
    private final ConditionContainsKeywordsPredicate conditionContainsKeywordsPredicate;
    private final TaskContainsKeywordsPredicate taskContainsKeywordsPredicate;

    /**
     * Constructs a {@code PatientMatchPredicate}
     * which tests {@code Patient} with given keywords.
     *
     * @param keywords
     */
    public PatientMatchPredicate(List<String> keywords) {
        this(keywords, List.of(""), List.of(""), List.of(""),
                List.of(""), List.of(""), List.of(""), List.of(""));
    }

    /**
     * Constructs a {@code PatientMatchPredicate}
     * which tests {@code Patient} with all given descriptors.
     *
     * @param keywords
     * @param nameList
     * @param phoneList
     * @param emailList
     * @param addressList
     * @param tagList
     * @param conditionList
     * @param taskList
     */
    public PatientMatchPredicate(List<String> keywords, List<String> nameList, List<String> phoneList,
                                 List<String> emailList, List<String> addressList, List<String> tagList,
                                 List<String> conditionList, List<String> taskList) {
        this.patientContainsKeywordsPredicate = new PatientContainsKeywordsPredicate(keywords);
        this.nameContainsKeywordsPredicate = new NameContainsKeywordsPredicate(nameList);
        this.phoneContainsKeywordsPredicate = new PhoneContainsKeywordsPredicate(phoneList);
        this.emailContainsKeywordsPredicate = new EmailContainsKeywordsPredicate(emailList);
        this.addressContainsKeywordsPredicate = new AddressContainsKeywordsPredicate(addressList);
        this.tagContainsKeywordsPredicate = new TagContainsKeywordsPredicate(tagList);
        this.conditionContainsKeywordsPredicate = new ConditionContainsKeywordsPredicate(conditionList);
        this.taskContainsKeywordsPredicate = new TaskContainsKeywordsPredicate(taskList);
    }

    @Override
    public boolean test(Patient person) {
        return patientContainsKeywordsPredicate.test(person)
                && nameContainsKeywordsPredicate.test(person)
                && phoneContainsKeywordsPredicate.test(person)
                && emailContainsKeywordsPredicate.test(person)
                && addressContainsKeywordsPredicate.test(person)
                && tagContainsKeywordsPredicate.test(person)
                && conditionContainsKeywordsPredicate.test(person)
                && taskContainsKeywordsPredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }
        if (other instanceof PatientMatchPredicate) {
            PatientMatchPredicate oth = (PatientMatchPredicate) other;
            return patientContainsKeywordsPredicate.equals(oth.patientContainsKeywordsPredicate)
                    && nameContainsKeywordsPredicate.equals(oth.nameContainsKeywordsPredicate)
                    && phoneContainsKeywordsPredicate.equals(oth.phoneContainsKeywordsPredicate)
                    && emailContainsKeywordsPredicate.equals(oth.emailContainsKeywordsPredicate)
                    && addressContainsKeywordsPredicate.equals(oth.addressContainsKeywordsPredicate)
                    && tagContainsKeywordsPredicate.equals(oth.tagContainsKeywordsPredicate)
                    && conditionContainsKeywordsPredicate.equals(oth.conditionContainsKeywordsPredicate)
                    && taskContainsKeywordsPredicate.equals(oth.taskContainsKeywordsPredicate);
        }
        return false;
    }
}
