package gim.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RegexListTest {
    private final RegexList regexList = RegexList.getRegexList();

    @Test
    public void constructor_returnsSingletonInstance() {
        assertEquals(regexList, RegexList.getRegexList());
    }

    @Test
    public void isValidDateByRegex_validInput_success() {
        String[] dateStrings = {
            "1/1/2020", "01/1/2020", "1/01/2020", "01/01/2020",
            "2020/1/1", "2020/1/01", "2020/01/1", "2020/01/01",

            "01-01-2020", "01-1-2020", "1-01-2020", "1-1-2020",
            "2020-01-01", "2020-1-01", "2020-01-1", "2020-1-1",

            "01 01 2020", "01 1 2020", "1 01 2020", "1 1 2020",
            "2020 01 01", "2020 1 01", "2020 01 1", "2020 1 1",
        };
        for (String dateString: dateStrings) {
            assertTrue(regexList.isValidDateByRegex(dateString));
        }
    }

    @Test
    public void isValidDateByRegex_invalidInput_failure() {
        String[] dateStrings = {
            "1 /1/2020", "01/ 1/2020", "1/01 /2020", "01/01/ 2020",
            "2020 /1/1", "2020/ 1/01", "2020/01 /1", "2020/01/ 01",

            "1 /1/20", "01/ 1/20", "1/01 /20", "01/01/ 20",
            "20 /1/1", "20/ 1/01", "20/01 /1", "20/01/ 01",
        };
        for (String dateString: dateStrings) {
            assertFalse(regexList.isValidDateByRegex(dateString));
        }
    }
}
