package seedu.boba.model.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * Tests that a {@code Customer}'s {@code Name} sounds like any of the keywords given.
 * Implemented using soundex (algorithm can be found on wikipedia, slight modifications were made)
 *
 * @author albertZhangTJ
 */
public class NameSoundsSimilarToPredicate implements Predicate<Customer> {
    private static final List<List<String>> soundexMapping = List.of(
            List.of("a", "e", "i", "o", "u", "y", "h", "w"), //0
            List.of("b", "f", "p", "v"), //1
            List.of("c", "g", "j", "k", "q", "s", "x", "z"), //2
            List.of("d", "t"), //3
            List.of("l"), //4
            List.of("m", "n"), //5
            List.of("r") //6
    );
    private final List<String> keywords;

    /**
     * Constructor for this class
     * @param keywords
     */
    public NameSoundsSimilarToPredicate(List<String> keywords) {
        assert keywords != null;
        if (keywords == null) {
            throw new RuntimeException("Keyword list cannot be null");
        }
        this.keywords = keywords;
    }

    //====================SOUNDEX==================================

    /**
     * Check if the name is fully alphabetical
     *
     * @param name the input name
     * @return whether the name is fully alphabetical
     */
    private static boolean isFullyAlphabetical(String name) {
        for (int i = 0; i < name.length(); i++) {
            int ascii = (int) name.charAt(i);
            if (!(ascii >= 65 && ascii <= 90) && !(ascii >= 97 && ascii <= 122)) {
                return false;
            }
        }
        return true;
    }

    private static String dropHwy(String name) {
        if (name == null || name.length() < 2) {
            return name;
        }
        String ans = "";
        for (int i = 0; i < name.length(); i++) {
            //add if is first character or is not HWY
            if (i == 0 || (name.charAt(i) != 'h' && name.charAt(i) != 'w' && name.charAt(i) != 'y')) {
                ans = ans + name.charAt(i);
            }
        }
        return ans;
    }

    private static String applySoundexMapping(String strippedName) {
        if (strippedName == null || strippedName.length() < 2) {
            return strippedName;
        }
        String ans = strippedName.substring(0, 1);
        for (int i = 1; i < strippedName.length(); i++) {
            for (int j = 0; j < soundexMapping.size(); j++) {
                if (soundexMapping.get(j).contains(strippedName.substring(i, i + 1))) {
                    ans = ans + j;
                    break;
                }
            }
        }
        return ans;
    }

    private static String combineAdjacentDigits(String code) {
        //short name, no repetition is possible
        if (code == null || code.length() <= 2) {
            return code;
        }
        String ans = code.substring(0, 2);
        for (int i = 2; i < code.length(); i++) {
            if (code.charAt(i) != code.charAt(i - 1)) {
                ans = ans + code.charAt(i);
            }
        }
        return ans;
    }

    private static String trimSoundexCode(String code) {
        //remove all existing zeros
        String ans = "";
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) != '0') {
                ans = ans + code.charAt(i);
            }
        }

        //if length<4, append 0 to end
        while (ans.length() < 4) {
            ans = ans + "0";
        }

        //if length>4, only keep letter + the first 3 digits
        if (ans.length() > 4) {
            ans = ans.substring(0, 4);
        }
        return ans;
    }

    private static String soundexOfIfAlphabetical(String name) {
        name = name.toLowerCase();
        if (!isFullyAlphabetical(name)) {
            return name;
        }
        name = dropHwy(name);
        name = applySoundexMapping(name);
        name = combineAdjacentDigits(name);
        name = trimSoundexCode(name);
        return name;
    }

    private boolean hasMatch(String name) {
        String[] splitedName = name.split(" ");
        List<String> searchCode = new ArrayList<>();
        for (String part : splitedName) {
            if (part.length() > 0) {
                searchCode.add(soundexOfIfAlphabetical(part));
            }
        }
        for (String keyword : keywords) {
            String keywordCode = soundexOfIfAlphabetical(keyword);
            for (String nameCode : searchCode) {
                if (keywordCode.equals(nameCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean test(Customer customer) {
        return hasMatch(customer.getName().fullName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.boba.model.customer
                .NameSoundsSimilarToPredicate // instanceof handles nulls
                && keywords.equals((
                (seedu.boba.model.customer.NameSoundsSimilarToPredicate) other).keywords
        )); // state check
    }

}

