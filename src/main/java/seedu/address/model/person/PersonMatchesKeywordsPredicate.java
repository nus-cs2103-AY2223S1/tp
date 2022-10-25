package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.function.Predicate;
import java.util.Arrays;


public class PersonMatchesKeywordsPredicate implements Predicate<Person> {
    private final String keywords;

    public PersonMatchesKeywordsPredicate(String keywords) {
        this.keywords = keywords;
    }

    static int getLevenshteindist(String str1, String str2) {

        int[][] arr = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
        {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    arr[i][j] = j;
                }

                else if (j == 0) {
                    arr[i][j] = i;
                }

                else {
                    arr[i][j] = minEdits(arr[i - 1][j - 1]
                                    + numOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)),
                            arr[i - 1][j] + 1,
                            arr[i][j - 1] + 1);
                }
            }
        }
        return arr[str1.length()][str2.length()];
    }


    public static double findSimilarity(String x, String y) {
        double maxLength = Double.max(x.length(), y.length());
        if (maxLength > 0) {
            return (maxLength - getLevenshteindist(x, y)) / maxLength;
        }
        return 1.0;
    }

    static int numOfReplacement(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }

    static int minEdits(int... nums) {
        return Arrays.stream(nums).min().orElse(Integer.MAX_VALUE);
    }

    private boolean matchesName(Person person) {
        return findSimilarity(keywords,person.getName().fullName) > 0.5 ||
                StringUtil.containsWordIgnoreCase(person.getName().fullName, keywords);
    }

    private boolean matchesAddress(Person person) {
        return findSimilarity(keywords,person.getAddress().toString()) > 0.5 ||
                StringUtil.containsWordIgnoreCase(person.getAddress().toString(), keywords);
    }

    private boolean matchesRole(Person person) {
        return findSimilarity(keywords, String.valueOf(person.getRole().get())) > 0.5 ||
                StringUtil.containsWordIgnoreCase(String.valueOf(person.getRole().get()), keywords);
    }

    private boolean matchesGitHubUser(Person person) {
        return findSimilarity(keywords,person.getGitHubUser().toString()) > 0.5 ||
                StringUtil.containsWordIgnoreCase(person.getGitHubUser().toString(), keywords);
    }

    private boolean matchesTags(Person person) {
        String tags = person.getTags().toString().
                replaceAll("\\[", "").
                replaceAll("\\]", "").
                replaceAll(",", "");
        return findSimilarity(keywords,tags) > 0.5 ||
                StringUtil.containsWordIgnoreCase(tags, keywords);
    }

    @Override
    public boolean test(Person person) {
        return matchesName(person) || matchesAddress(person) || matchesRole(person) || matchesTags(person) ||
                matchesGitHubUser(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonMatchesKeywordsPredicate) other).keywords)); // state check
    }
}
