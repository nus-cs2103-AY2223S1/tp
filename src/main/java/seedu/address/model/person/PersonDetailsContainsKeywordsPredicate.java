package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code module code} matches any of the keywords given.
 */
public class PersonDetailsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonDetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * This method converts the String returned from calling toString method on a Set to correct format.
     * @param personTags String returned from calling toString method on PersonTags,
     *                          for example: "[[friend], [TA]]".
     * @return String representation of person tags seperated by space, for example: "friend TA".
     */
    private String tagsToString(String personTags) {
        //case when no tags
        if (personTags.equals("[]")) {
            return "";
        }
        String[] personTagsSplit = personTags.split(", ");
        String result = "";
        int numTags = personTagsSplit.length;
        for (int i = 0; i < numTags; i++) {
            //in the case of only 1 word, need to trim 2 brackets from either side
            if (numTags == 1) {
                result = personTagsSplit[i].substring(2, personTagsSplit[i].length() - 2);
                //for the first word, need to trim 2 brackets from front, 1 from rear
            } else if (i == 0) {
                result = result + personTagsSplit[i].substring(2, personTagsSplit[i].length() - 1) + " ";
                //for last word, need trim 1 bracket from front, 2 from rear
            } else if(i == numTags - 1) {
                result = result + personTagsSplit[i].substring(1, personTagsSplit[i].length() - 2);
                //if middle, just trim 1 bracket from both front and rear
            } else {
                result = result + personTagsSplit[i].substring(1, personTagsSplit[i].length() - 1) + " ";
            }
        }
        return result;
    }

    @Override
    public boolean test(Person person) {
        String personDetails = person.getName().toString()
                + " " + person.getModuleCode().toString()
                + " " + person.getEmail().toString()
                + " " + person.getPhone().toString()
                + " " + person.getTelegram().toString()
                + " " + tagsToString(person.getTags().toString());
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(personDetails, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonDetailsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonDetailsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
