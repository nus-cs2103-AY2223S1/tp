package seedu.phu.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.parser.exceptions.InvalidCategoryException;
import seedu.phu.model.internship.FindableCategory;

public class FindableCategoryParserTest {
    @Test
    public void parse_companyNameCategory() throws InvalidCategoryException {
        assertEquals(FindableCategory.COMPANY_NAME,
                FindableCategoryParser.parse("company_name"));

        assertEquals(FindableCategory.COMPANY_NAME,
                FindableCategoryParser.parse("n"));
    }

    @Test
    public void parse_tagCategory() throws InvalidCategoryException {
        assertEquals(FindableCategory.TAGS,
                FindableCategoryParser.parse("tags"));

        assertEquals(FindableCategory.TAGS,
                FindableCategoryParser.parse("t"));
    }

    @Test
    public void parse_applicationProcessCategory() throws InvalidCategoryException {
        assertEquals(FindableCategory.APPLICATION_PROCESS,
                FindableCategoryParser.parse("application_process"));

        assertEquals(FindableCategory.APPLICATION_PROCESS,
                FindableCategoryParser.parse("pr"));
    }

    @Test
    public void parse_positionCategory() throws InvalidCategoryException {
        assertEquals(FindableCategory.POSITION,
                FindableCategoryParser.parse("position"));

        assertEquals(FindableCategory.POSITION,
                FindableCategoryParser.parse("p"));
    }

    @Test
    public void parse_dateCategory() throws InvalidCategoryException {
        assertEquals(FindableCategory.DATE,
                FindableCategoryParser.parse("date"));

        assertEquals(FindableCategory.DATE,
                FindableCategoryParser.parse("d"));
    }

    @Test
    public void parse_unknownCategory_throwsException() {
        try {
            FindableCategoryParser.parse("iaebfinqoaewf");
            fail();
        } catch (InvalidCategoryException e) {
            //pass
        }
    }
}
