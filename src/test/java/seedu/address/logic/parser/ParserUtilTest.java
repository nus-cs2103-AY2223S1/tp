package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientMobile;
import seedu.address.model.issue.Title;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectTest;
import seedu.address.model.project.Repository;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_MOBILE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_REPOSITORY = "Notes";
    private static final String INVALID_DEADLINE = "3rd May 2022";
    private static final String INVALID_TITLE = " ";
    private static final String INVALID_URGENCY = "-1";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_MOBILE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_REPOSITORY = "Rachel/Notes";
    private static final String VALID_DEADLINE = "2022-05-03";
    private static final String VALID_TITLE = "Finish up notes for the week";
    private static final String VALID_PROJECT_ID = "1";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseMobile_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMobile((String) null));
    }

    @Test
    public void parseMobile_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMobile(INVALID_MOBILE));
    }

    @Test
    public void parseMobile_validValueWithoutWhitespace_returnsMobile() throws Exception {
        ClientMobile expectedMobile = new ClientMobile(VALID_MOBILE);
        assertEquals(expectedMobile, ParserUtil.parseMobile(VALID_MOBILE));
    }

    @Test
    public void parseMobile_validValueWithWhitespace_returnsTrimmedMobile() throws Exception {
        String mobileWithWhitespace = WHITESPACE + VALID_MOBILE + WHITESPACE;
        ClientMobile expectedMobile = new ClientMobile(VALID_MOBILE);
        assertEquals(expectedMobile, ParserUtil.parseMobile(mobileWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        ClientEmail expectedEmail = new ClientEmail(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        ClientEmail expectedEmail = new ClientEmail(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseRepository_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRepository(null));
    }

    @Test
    public void parseRepository_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_REPOSITORY));
    }

    @Test
    public void parseRepository_validValueWithoutWhitespace_returnsRepository() throws Exception {
        Repository expectedRepository = new Repository(VALID_REPOSITORY);
        assertEquals(expectedRepository, ParserUtil.parseRepository(VALID_REPOSITORY));
    }

    @Test
    public void parseRepository_validValueWithWhitespace_returnsRepository() throws Exception {
        String repositoryWithWhitespace = WHITESPACE + VALID_REPOSITORY + WHITESPACE;
        Repository expectedRepository = new Repository(VALID_REPOSITORY);
        assertEquals(expectedRepository, ParserUtil.parseRepository(repositoryWithWhitespace));
    }

    @Test
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline(null));
    }

    @Test
    public void parseDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithWhitespace_returnsDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_DEADLINE + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(deadlineWithWhitespace));
    }

    @Test
    public void parseTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle(null));
    }

    @Test
    public void parseTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithoutWhitespace_returnsTitle() throws Exception {
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(VALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithWhitespace_returnsTitle() throws Exception {
        String titleWithWhitespace = WHITESPACE + VALID_TITLE + WHITESPACE;
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(titleWithWhitespace));
    }

    @Test
    public void parseUrgency_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUrgency(null));
    }

    @Test
    public void parseUrgency_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUrgency(INVALID_URGENCY));
    }

    @Test
    public void parseProject_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle(null));
    }

    @Test
    public void parseProject_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseProject_validValueWithoutWhitespace_returnsProject() throws ParseException {
        Project expectedProject = ProjectTest.getDefaultProject();
        assertEquals(expectedProject, ParserUtil.parseProjectStub(VALID_PROJECT_ID));
    }

    @Test
    public void parseProject_validValueWithWhitespace_returnsProject() throws Exception {
        String projectIdWithWhitespace = WHITESPACE + VALID_PROJECT_ID + WHITESPACE;
        Project expectedProject = ProjectTest.getDefaultProject();
        assertEquals(expectedProject, ParserUtil.parseProjectStub(projectIdWithWhitespace));
    }
}
