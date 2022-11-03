package gim.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class FormatterListTest {
    private final FormatterList formatterList = FormatterList.getFormatterList();

    @Test
    public void constructor_returnsSingletonInstance() {
        assertEquals(formatterList, FormatterList.getFormatterList());
    }

    @Test
    public void validDateWithFormatters_validInput_success() {
        String[] dateStrings = {
            "1/1/2020", "01/1/2020", "1/01/2020", "01/01/2020",
            "2020/1/1", "2020/1/01", "2020/01/1", "2020/01/01",

            "01-01-2020", "01-1-2020", "1-01-2020", "1-1-2020",
            "2020-01-01", "2020-1-01", "2020-01-1", "2020-1-1",

            "01 01 2020", "01 1 2020", "1 01 2020", "1 1 2020",
            "2020 01 01", "2020 1 01", "2020 01 1", "2020 1 1",
        };
        for (String dateString: dateStrings) {
            assertNotNull(formatterList.validateDateWithFormatters(dateString));
        }
    }

    @Test
    public void validDateWithFormatters_invalidInput_failure() {
        String[] dateStrings = {
            "1 /1/2020", "01/ 1/2020", "1/01 /2020", "01/01/ 2020",
            "2020 /1/1", "2020/ 1/01", "2020/01 /1", "2020/01/ 01",

            "1 /1/20", "01/ 1/20", "1/01 /20", "01/01/ 20",
            "20 /1/1", "20/ 1/01", "20/01 /1", "20/01/ 01",

            "01 -01-2020", "01- 1-2020", "1-01 -2020", "1-1- 2020",
            "2020 -01-01", "2020- 1-01", "2020-01 -1", "2020-1- 1",

            "01 -01-20", "01- 1-20", "1-01 -20", "1-1- 20",
            "20 -01-01", "20- 1-01", "20-01 -1", "20-1- 1",
        };
        for (String dateString: dateStrings) {
            assertNull(formatterList.validateDateWithFormatters(dateString));
        }
    }
}
