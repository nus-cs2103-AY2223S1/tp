package gim.model.date;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that contains the valid regular expressions (regex) needed to validate dates.
 */
public class RegexList {
    private static RegexList regexListInstance = null;
    private static List<String> regexList = null;

    private RegexList() {
        regexList = new ArrayList<>();
        /*
            Examples (DAY/MONTH/YEAR):
            01/01/2022, 01/1/2022, 1/01/2022, 1/1/2022
         */
        regexList.add("^(0?[0-9]||[1-2][0-9]||3[0-1])/(0?[0-9]||1[0-2])/([0-9][0-9])[0-9][0-9]$");

        /*
            Examples (YEAR/MONTH/DAY):
            2022/01/01, 2022/1/01, 2022/01/1, 2022/1/1
         */
        regexList.add("^([0-9][0-9])[0-9][0-9]/(0?[0-9]||1[0-2])/(0?[0-9]||[1-2][0-9]||3[0-1])$");
        /*
            Examples (DAY-MONTH-YEAR):
            01-01-2022, 01-1-2022, 1-01-2022, 1-1-2022
         */
        regexList.add("^(0?[0-9]||[1-2][0-9]||3[0-1]).*\\-.*(0?[0-9]||1[0-2]).*\\-.*([0-9][0-9])[0-9][0-9]$");

        /*
            Examples (YEAR-MONTH-DAY):
            2022-01-01, 2022-1-01, 2022-01-1, 2022-1-1
         */
        regexList.add("^([0-9][0-9])[0-9][0-9].*\\-.*(0?[0-9]||1[0-2]).*\\-.*(0?[0-9]||[1-2][0-9]||3[0-1])$");
        /*
            Examples (DAY MONTH YEAR):
            01 01 2022, 01 1 2022, 1 01 2022, 1 1 2022
         */
        regexList.add("^(0?[0-9]||[1-2][0-9]||3[0-1])\\s+(0?[0-9]||1[0-2])\\s+([0-9][0-9])[0-9][0-9]$");

        /*
            Examples (YEAR MONTH DAY):
            2022 01 01, 2022 1 01, 2022 01 1, 2022 1 1

            Note: multiple whitespaces have been substituted to a single whitespace in ParserUtil::parseDate
         */
        regexList.add("^([0-9][0-9])[0-9][0-9]\\s+(0?[0-9]||1[0-2])\\s+(0?[0-9]||[1-2][0-9]||3[0-1])$");
    }

    /**
     * Factory method to obtain singleton instance of RegexList.
     * @return RegexList object
     */
    public static RegexList getRegexList() {
        if (regexListInstance == null) {
            regexListInstance = new RegexList();
        }
        return regexListInstance;
    }

    /**
     * Returns true if a given string is a valid date according to the regex list.
     */
    public boolean isValidDateByRegex(String test) {
        for (String regex : regexList) {
            if (test.matches(regex)) {
                return true;
            }
        }
        return false;
    }
}
