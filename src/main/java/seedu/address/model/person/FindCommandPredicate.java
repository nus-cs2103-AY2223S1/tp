package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindCommandPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FindCommandPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean checkName = keywords.stream()
                                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName,
                                                                                           keyword));
        boolean checkClass = keywords.stream()
                                     .anyMatch(
                                         keyword -> StringUtil.containsWordIgnoreCase(person.getStudentClass().value,
                                                                                      keyword));
        boolean checkSubject = keywords.stream()
                                       .anyMatch(keyword ->
                person.getSubjectsTaken().toString().toLowerCase().contains(keyword));
        return checkName || checkClass || checkSubject;
    }

    /**
     * Checks if a sentence contains any one of the keywords, where keywords are separated by spaces.
     * Method helps to resolve issue of keyword given after prefix is more than 1 word and
     * separated by spaces.
     *
     * @param sentence Sentence that may contain one or more keywords.
     * @param keywords Keywords separated by spaces and are given by user using the find command.
     * @return A boolean for if keyword(s) is contained in sentence.
     */
    private boolean stringContainsWord(String sentence, String keywords) {
        assert sentence != null : "Sentence cannot be null";
        assert keywords != null : "Keywords cannot be null";
        String[] keywordArr = keywords.split(" ");
        for (String keyword: keywordArr) {
            if (StringUtil.containsWordIgnoreCase(sentence, keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof FindCommandPredicate // instanceof handles nulls
                   && keywords.equals(((FindCommandPredicate) other).keywords)); // state check
    }
}
