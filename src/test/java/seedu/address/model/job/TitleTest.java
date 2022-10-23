package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isWithinLengthLimit() {
        assertTrue(Title.isWithinLengthLimit("A".repeat(Title.LENGTH_LIMIT - 1)));
        assertTrue(Title.isWithinLengthLimit("A".repeat(Title.LENGTH_LIMIT)));
        assertFalse(Title.isWithinLengthLimit("A".repeat(Title.LENGTH_LIMIT + 1)));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // blank title
        assertFalse(Title.isValidTitle("")); // empty string

        // invalid title
        assertFalse(Title.isValidTitle("Campus Recruitment | Software Engineer Intern")); // '|' symbol in title
        assertFalse(Title.isValidTitle("Campus Recruitment ! Software Engineer Intern")); // '!' symbol in title
        assertFalse(Title.isValidTitle("Campus Recruitment ~ Software Engineer Intern")); // '~' symbol in title
        assertFalse(Title.isValidTitle("Campus Recruitment \\ Software Engineer Intern")); // '\' symbol in title
        assertFalse(Title.isValidTitle("* Campus Recruitment Software Engineer Intern")); // '*' symbol in title
        assertFalse(Title.isValidTitle("{Campus Recruitment} Software Engineer Intern")); // '{}' symbol in title


        // valid title
        assertTrue(Title.isValidTitle("Campus Recruitment - Software Engineer Intern")); // '-' symbol in title
        assertTrue(Title.isValidTitle("#Campus Recruitment Software Engineer Intern")); // '#' symbol in title
        assertTrue(Title.isValidTitle("Software Engineer Intern (May'23 - Aug'23)")); // '(', "'", ')' symbols in title
        assertTrue(Title.isValidTitle("Campus Recruitment, Software Engineer Intern")); // ',' symbol in title
        assertTrue(Title.isValidTitle("Campus Recruitment: Software Engineer Intern")); // ':' symbol in title
        assertTrue(Title.isValidTitle("\"Campus Recruitment\" Software Engineer Intern")); // '"' symbol in title
        assertTrue(Title.isValidTitle("[Campus Recruitment] Software Engineer Intern")); // '[]' symbol in title
        assertTrue(Title.isValidTitle("Data Science & Analytics Intern")); // '&' symbol in title
        assertTrue(Title.isValidTitle("Data Science / Analytics Intern")); // '/' symbol in title
    }
}
