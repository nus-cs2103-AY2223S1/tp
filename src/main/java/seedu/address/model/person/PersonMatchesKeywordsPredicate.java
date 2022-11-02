package seedu.address.model.person;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person} matches any of the keywords given.
 */
public class PersonMatchesKeywordsPredicate implements Predicate<Person> {
    private final String keywords;

    public PersonMatchesKeywordsPredicate(String keywords) {
        this.keywords = keywords;
    }

    private boolean containsIgnoreCase(String str1, String str2) {
        return str1.toLowerCase().contains(str2.toLowerCase());
    }

    /**
     * Computes the Levenshtein distance, which tells how different two
     * strings are from one another by counting the minimum
     * number of operations (insertions, deletions and substitutions of characters)
     * required to transform one string to another.
     *
     * @param str1 string1 to be compared
     * @param str2 string2 to be compared
     * @return the Levenshtein distance between the 2 strings
     */
    private int getLevenshteinDist(String str1, String str2) {

        // A 2-D matrix to store previously calculated answers of subproblems
        int[][] arr = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {

                if (i == 0) {
                    // If str1 is empty, the only possible
                    // method of conversion with minimum operations
                    // would be for all characters of
                    // str2 to be inserted into str1.
                    arr[i][j] = j;
                } else if (j == 0) {
                    // If str2 is empty the only possible
                    // method of conversion with minimum operations
                    // would be for all characters of str1 to be removed.
                    arr[i][j] = i;
                } else {
                    //else find minimum among the three operations below
                    arr[i][j] = minEdits(arr[i - 1][j - 1]
                                    + numOfReplacement(str1.charAt(i - 1), str2.charAt(j - 1)),
                            arr[i - 1][j] + 1,
                            arr[i][j - 1] + 1);

                }
            }
        }
        return arr[str1.length()][str2.length()];
    }

    /**
     * This method uses the levenshtein distance to calculate the similarity
     * between two strings in the range [0, 1].
     * @param x the first string to be compared
     * @param y second string to be compared
     * @return the similarity between the 2 strings in the range [0,1]
     */
    private double findSimilarity(String x, String y) {
        double maxLength = Double.max(x.length(), y.length());
        if (maxLength > 0) {
            assert ((maxLength - getLevenshteinDist(x, y)) / maxLength) <= 1
                    : "findSimilarity method not working properly";
            return (maxLength - getLevenshteinDist(x, y)) / maxLength;
        }
        return 1.0;
    }

    /**
     * This method is used to check for distinct
     * characters in str1 and str2.
     *
     * @param c1 char1 to be compared
     * @param c2 char2 to be compared
     * @return 0 if both chars are same, else 1
     */
    private int numOfReplacement(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }

    /**
     * This method is used to receive the count of different
     * operations performed and return the minimum
     * value among them.
     *
     * @param nums the number of operations
     * @return minimum number of operations
     */
    private int minEdits(int... nums) {
        return Arrays.stream(nums).min().orElse(Integer.MAX_VALUE);
    }

    private boolean matchesName(Person person) {
        return findSimilarity(keywords, person.getName().fullName) > 0.5
                || containsIgnoreCase(
                        person.getName().fullName, keywords
        );
    }

    private boolean matchesAddress(Person person) {
        if (person.getAddress().isPresent()) {
            assert person.getAddress().get() != null : "Error with matchesAddress method";
            return findSimilarity(keywords, String.valueOf(
                    person.getAddress().get().value)) > 0.5
                    || containsIgnoreCase(
                            person.getAddress().get().value, keywords
            );
        } else {
            return false;
        }
    }

    private boolean matchesRole(Person person) {
        if (person.getRole().isPresent()) {
            return findSimilarity(keywords, String.valueOf(person.getRole().get().role)) > 0.5
                    || containsIgnoreCase(
                            person.getRole().get().role, keywords
            );
        } else {
            return false;
        }
    }

    private boolean matchesGitHubUser(Person person) {
        return findSimilarity(keywords, person.getGithubUser().toString()) > 0.5
                || containsIgnoreCase(
                        person.getGithubUser().toString(), keywords
        );
    }

    private boolean matchesTags(Person person) {
        Object[] tags = person.getTags().toArray();
        for (int i = 0; i < tags.length; i++) {
            assert tags[i] instanceof Tag : "Error with tags list of Person";
            if (findSimilarity(keywords, tags[i].toString()) > 0.5
                    || containsIgnoreCase(tags[i].toString(), keywords)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean test(Person person) {
        return matchesName(person)
                || matchesAddress(person)
                || matchesTags(person)
                || matchesRole(person)
                || matchesGitHubUser(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonMatchesKeywordsPredicate) other).keywords)); // state check
    }
}
