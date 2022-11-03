package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_EXAMONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXAMONE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_KEYWORDS;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_NUMBER_OF_KEYWORDS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Criteria;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskDescription;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    private static final String INVALID_TASK_DESCRIPTION = "";
    private static final String INVALID_EXAM_DATE_WRONG_FORMAT = "29 Dec 2024";
    private static final String INVALID_EXAM_DATE_DOES_NOT_EXIST = "29-02-2023";
    private static final String INVALID_EXAM_DATE_PAST_DATE = "20-02-2010";

    private static final String INVALID_MODULE_CODE = "2100";
    private static final String INVALID_MODULE_NAME = "";
    private static final String INVALID_MODULE_CREDIT_NOT_AN_INT = "word";
    private static final String INVALID_MODULE_CREDIT_EXCEED_LIMIT = "50";

    private static final String INVALID_CRITERIA = "pri";
    private static final String INVALID_PRIORITY = "highlow";
    private static final String INVALID_DEADLINE_WRONG_FORMAT = "30 Dec 2024";
    private static final String INVALID_DEADLINE_INVALID_DATE = "29-02-2023";
    private static final String INVALID_DEADLINE_DATE_PASSED = "30-12-2010";
    private static final String INVALID_KEYWORDS_TOO_MANY = "priority deadline priority";
    private static final String INVALID_KEYWORDS_WRONG_KEYWORDS = "priority dl";

    private static final String VALID_TASK_DESCRIPTION = "task 1";

    private static final String VALID_EXAM_DATE = "30-05-2025";

    private static final String VALID_MODULE_CODE = "cs2100";
    private static final String VALID_MODULE_NAME = "Computer Organisation";
    private static final String VALID_MODULE_CREDIT = "20";
    private static final Integer VALID_MODULE_CREDIT_INT = 20;

    private static final String VALID_CRITERIA = "priority";
    private static final String VALID_PRIORITY = "high";
    private static final String VALID_DEADLINE = "30-12-2024";
    private static final String VALID_KEYWORDS = "priority deadline";
    private static final String VALID_KEYWORDS_WITH_WHITE_SPACE = " priority  deadline ";

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
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_TASK_DESCRIPTION));
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(WHITESPACE));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsTaskDescription() throws Exception {
        TaskDescription expectedTaskDescription = new TaskDescription(VALID_TASK_DESCRIPTION);
        assertEquals(expectedTaskDescription, ParserUtil.parseDescription(VALID_TASK_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedTaskDescription() throws Exception {
        String taskDescriptionWithWhitespace = WHITESPACE + VALID_TASK_DESCRIPTION + WHITESPACE;
        TaskDescription expectedTaskDescription = new TaskDescription(VALID_TASK_DESCRIPTION);
        assertEquals(expectedTaskDescription, ParserUtil.parseDescription(taskDescriptionWithWhitespace));
    }

    @Test
    public void parseModuleCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleCode((String) null));
    }

    @Test
    public void parseModuleCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleCode(INVALID_MODULE_CODE));
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleCode(WHITESPACE));
    }

    @Test
    public void parseModuleCode_validValueWithoutWhitespace_returnsModuleCode() throws Exception {
        ModuleCode expectedModuleCode = new ModuleCode(VALID_MODULE_CODE);
        assertEquals(expectedModuleCode, ParserUtil.parseModuleCode(VALID_MODULE_CODE));
    }

    @Test
    public void parseModuleCode_validValueWithWhitespace_returnsTrimmedModuleCode() throws Exception {
        String moduleCodeWithWhitespace = WHITESPACE + VALID_MODULE_CODE + WHITESPACE;
        ModuleCode expectedModuleCode = new ModuleCode(VALID_MODULE_CODE);
        assertEquals(expectedModuleCode, ParserUtil.parseModuleCode(moduleCodeWithWhitespace));
    }

    @Test
    public void parseModuleName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleCode((String) null));
    }

    @Test
    public void parseModuleName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleName(INVALID_MODULE_NAME));
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleName(WHITESPACE));
    }

    @Test
    public void parseModuleName_validValueWithoutWhitespace_returnsModuleName() throws Exception {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULE_NAME);
        assertEquals(expectedModuleName, ParserUtil.parseModuleName(VALID_MODULE_NAME));
    }

    @Test
    public void parseModuleName_validValueWithWhitespace_returnsTrimmedModuleName() throws Exception {
        String moduleNameWithWhitespace = WHITESPACE + VALID_MODULE_NAME + WHITESPACE;
        ModuleName expectedModuleName = new ModuleName(VALID_MODULE_NAME);
        assertEquals(expectedModuleName, ParserUtil.parseModuleName(moduleNameWithWhitespace));
    }

    @Test
    public void parseModuleCredit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleCredit((String) null));
    }

    @Test
    public void parseModuleCredit_invalidValue_throwsParseException() {
        // not an integer
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleCredit(INVALID_MODULE_CREDIT_NOT_AN_INT));

        // not a valid module code
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleCredit(INVALID_MODULE_CREDIT_EXCEED_LIMIT));
    }

    @Test
    public void parseModuleCredit_validValueWithoutWhitespace_returnsModuleCredit() throws Exception {
        ModuleCredit expectedModuleCredit = new ModuleCredit(VALID_MODULE_CREDIT_INT);
        assertEquals(expectedModuleCredit, ParserUtil.parseModuleCredit(VALID_MODULE_CREDIT));
    }

    @Test
    public void parseModuleCredit_validValueWithWhitespace_returnsTrimmedModuleCredit() throws Exception {
        String moduleCreditWithWhitespace = WHITESPACE + VALID_MODULE_CREDIT + WHITESPACE;
        ModuleCredit expectedModuleCredit = new ModuleCredit(VALID_MODULE_CREDIT_INT);
        assertEquals(expectedModuleCredit, ParserUtil.parseModuleCredit(moduleCreditWithWhitespace));
    }

    @Test
    public void parseCriteria_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCriteria((String) null));
    }

    @Test
    public void parseCriteria_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCriteria(INVALID_CRITERIA));
    }

    @Test
    public void parseCriteria_validValueWithoutWhitespace_returnsCriteria() throws Exception {
        Criteria expectedCriteria = new Criteria(VALID_CRITERIA);
        assertEquals(expectedCriteria, ParserUtil.parseCriteria(VALID_CRITERIA));
    }

    @Test
    public void parseCriteria_validValueWithWhitespace_returnsTrimmedCriteria() throws Exception {
        String criteriaWithWhitespace = WHITESPACE + VALID_CRITERIA + WHITESPACE;
        Criteria expectedCriteria = new Criteria(VALID_CRITERIA);
        assertEquals(expectedCriteria, ParserUtil.parseCriteria(criteriaWithWhitespace));
    }

    @Test
    public void parsePriorityTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePriorityTag((String) null));
    }

    @Test
    public void parsePriorityTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriorityTag(INVALID_PRIORITY));
    }

    @Test
    public void parsePriorityTag_validValueWithoutWhitespace_returnsPriorityTag() throws Exception {
        PriorityTag expectedPriorityTag = new PriorityTag(VALID_PRIORITY);
        assertEquals(expectedPriorityTag, ParserUtil.parsePriorityTag(VALID_PRIORITY));
    }

    @Test
    public void parsePriorityTag_validValueWithWhitespace_returnsTrimmedPriorityTag() throws Exception {
        String priorityTagWithWhitespace = WHITESPACE + VALID_PRIORITY + WHITESPACE;
        PriorityTag expectedPriorityTag = new PriorityTag(VALID_PRIORITY);
        assertEquals(expectedPriorityTag, ParserUtil.parsePriorityTag(priorityTagWithWhitespace));
    }

    @Test
    public void parseDeleteTagKeywords_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeleteTagKeywords((String) null));
    }

    @Test
    public void parseDeleteTagKeywords_invalidNumOfKeywords_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_NUMBER_OF_KEYWORDS, () ->
            ParserUtil.parseDeleteTagKeywords(INVALID_KEYWORDS_TOO_MANY));
    }

    @Test
    public void parseDeleteTagKeywords_wrongKeywords_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_KEYWORDS, () ->
            ParserUtil.parseDeleteTagKeywords(INVALID_KEYWORDS_WRONG_KEYWORDS));
    }

    @Test
    public void parseDeleteTagKeywords_validValueWithoutWhitespace_success() throws Exception {
        Set<String> expectedKeywordSet = new HashSet<>();
        expectedKeywordSet.add("priority");
        expectedKeywordSet.add("deadline");
        assertEquals(expectedKeywordSet, ParserUtil.parseDeleteTagKeywords(VALID_KEYWORDS));
    }

    @Test
    public void parseDeleteTagKeywords_validValueWithWhitespace_success() throws Exception {
        Set<String> expectedKeywordSet = new HashSet<>();
        expectedKeywordSet.add("priority");
        expectedKeywordSet.add("deadline");
        assertEquals(expectedKeywordSet, ParserUtil.parseDeleteTagKeywords(VALID_KEYWORDS_WITH_WHITE_SPACE));
    }

    @Test
    public void parseDeadlineTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadlineTag((String) null));
    }

    @Test
    public void parseDeadlineTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, DeadlineTag.DEADLINE_TAG_FORMAT_CONSTRAINTS, () ->
            ParserUtil.parseDeadlineTag(INVALID_DEADLINE_WRONG_FORMAT));
        assertThrows(ParseException.class, DeadlineTag.DEADLINE_TAG_INVALID_DATE, () ->
            ParserUtil.parseDeadlineTag(INVALID_DEADLINE_INVALID_DATE));
        assertThrows(ParseException.class, DeadlineTag.DEADLINE_TAG_DATE_HAS_PASSED, () ->
            ParserUtil.parseDeadlineTag(INVALID_DEADLINE_DATE_PASSED));
    }

    @Test
    public void parseDeadlineTag_validValueWithoutWhitespace_returnsDeadlineTag() throws Exception {
        DeadlineTag expectedDeadlineTag = new DeadlineTag(
            LocalDate.parse(VALID_DEADLINE, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        assertEquals(expectedDeadlineTag, ParserUtil.parseDeadlineTag(VALID_DEADLINE));
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
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
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
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseExamDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExamDate((String) null));
    }

    @Test
    public void parseExamDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class,
            ExamDate.DATE_FORMAT_CONSTRAINTS, () -> ParserUtil.parseExamDate(INVALID_EXAM_DATE_WRONG_FORMAT));
        assertThrows(ParseException.class,
            ExamDate.VALID_DATE_CONSTRAINTS, () -> ParserUtil.parseExamDate(INVALID_EXAM_DATE_DOES_NOT_EXIST));
        assertThrows(ParseException.class,
            ExamDate.NOT_A_PAST_DATE_CONSTRAINTS, () -> ParserUtil.parseExamDate(INVALID_EXAM_DATE_PAST_DATE));
    }

    @Test
    public void parseExamDate_validValueWithoutWhitespace_returnsExamDate() throws Exception {
        ExamDate expectedExamDate = new ExamDate(VALID_EXAM_DATE);
        assertEquals(expectedExamDate, ParserUtil.parseExamDate(VALID_EXAM_DATE));
    }

    @Test
    public void parseExamDate_validValueWithWhitespace_returnsTrimmedExamDate() throws Exception {
        String criteriaWithWhitespace = WHITESPACE + VALID_EXAM_DATE + WHITESPACE;
        ExamDate expectedExamDate = new ExamDate(VALID_EXAM_DATE);
        assertEquals(expectedExamDate, ParserUtil.parseExamDate(criteriaWithWhitespace));
    }

    @Test
    public void parseExamDate_validDateWithWhitespace_returnsTrimmedDate() throws Exception {
        String examDateWithWhitespace = WHITESPACE + VALID_DATE_EXAMONE + WHITESPACE;
        ExamDate examDate = new ExamDate(VALID_DATE_EXAMONE);
        assertEquals(examDate, ParserUtil.parseExamDate(examDateWithWhitespace));
    }

    @Test
    public void parseExamDesc_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExamDescription((String) null));
    }

    @Test
    public void parseExamDesc_invalidDesc_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExamDescription(""));
    }

    @Test
    public void parseExamDesc_validDescWithoutWhitespace_returnsDesc() throws Exception {
        ExamDescription examDescription = new ExamDescription(VALID_DESCRIPTION_EXAMONE);
        assertEquals(examDescription, ParserUtil.parseExamDescription(VALID_DESCRIPTION_EXAMONE));
    }

    @Test
    public void parseExamDesc_validDescWithWhitespace_returnsTrimmedDesc() throws Exception {
        String examDateWithWhitespace = WHITESPACE + VALID_DESCRIPTION_EXAMONE + WHITESPACE;
        ExamDescription examDescription = new ExamDescription(VALID_DESCRIPTION_EXAMONE);
        assertEquals(examDescription, ParserUtil.parseExamDescription(examDateWithWhitespace));
    }

}
