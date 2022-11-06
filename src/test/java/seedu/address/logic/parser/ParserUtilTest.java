package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.index.Index.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.person.nextofkin.Relationship;
import seedu.address.model.person.student.School;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ENTITY = "gangster";
    private static final String INVALID_SCHOOL = "St. Something School";
    private static final String INVALID_LEVEL = "primary8";
    private static final String INVALID_QUALIFICATION = "Master of [";
    private static final String INVALID_INSTITUTION = "University of /";
    private static final String INVALID_SUBJECT = "Social Studies";
    private static final String INVALID_DAY = "yesterday";
    private static final String INVALID_RELATIONSHIP = "stranger";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "1234567";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_SCHOOL = "Valid Primary School";
    private static final String VALID_LEVEL_1 = "primary1";
    private static final String VALID_LEVEL_2 = "secondary1";
    private static final String VALID_QUALIFICATION = "MSc, Master of Science";
    private static final String VALID_INSTITUTION = "University of Valid";
    private static final String VALID_SUBJECT = "english";
    private static final String VALID_DAY = "monday";
    private static final String VALID_TIME = "16:00-17:00";
    private static final String VALID_RELATIONSHIP = "mother";

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
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseEntity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEntity((String) null));
    }

    @Test
    public void parseEntity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity(INVALID_ENTITY));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("stu"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("tut"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("cla"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("somethingstude"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("somethingtuto"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("somethingclas"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("studesomething"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("tutosomething"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("classomething"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("a student"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("a tutor"));
        assertThrows(ParseException.class, () -> ParserUtil.parseEntity("a class"));
    }

    @Test
    public void parseEntity_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(AddCommand.Entity.STUDENT, ParserUtil.parseEntity("student"));
        assertEquals(AddCommand.Entity.TUTOR, ParserUtil.parseEntity("tutor"));
        assertEquals(AddCommand.Entity.CLASS, ParserUtil.parseEntity("class"));
        assertEquals(AddCommand.Entity.STUDENT, ParserUtil.parseEntity("s"));
        assertEquals(AddCommand.Entity.TUTOR, ParserUtil.parseEntity("t"));
        assertEquals(AddCommand.Entity.CLASS, ParserUtil.parseEntity("c"));

        // Leading and trailing whitespaces
        assertEquals(AddCommand.Entity.STUDENT, ParserUtil.parseEntity("   student   "));
        assertEquals(AddCommand.Entity.TUTOR, ParserUtil.parseEntity("   tutor   "));
        assertEquals(AddCommand.Entity.CLASS, ParserUtil.parseEntity("   class   "));
        assertEquals(AddCommand.Entity.STUDENT, ParserUtil.parseEntity("   s   "));
        assertEquals(AddCommand.Entity.TUTOR, ParserUtil.parseEntity("   t   "));
        assertEquals(AddCommand.Entity.CLASS, ParserUtil.parseEntity("   c   "));

        // capitalised
        assertEquals(AddCommand.Entity.STUDENT, ParserUtil.parseEntity("STUDENT"));
        assertEquals(AddCommand.Entity.TUTOR, ParserUtil.parseEntity("TUTOR"));
        assertEquals(AddCommand.Entity.CLASS, ParserUtil.parseEntity("CLASS"));
        assertEquals(AddCommand.Entity.STUDENT, ParserUtil.parseEntity("S"));
        assertEquals(AddCommand.Entity.TUTOR, ParserUtil.parseEntity("T"));
        assertEquals(AddCommand.Entity.CLASS, ParserUtil.parseEntity("C"));
    }

    @Test
    public void parseSchool_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSchool((String) null));
    }

    @Test
    public void parseSchool_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSchool(INVALID_SCHOOL));

    }

    @Test
    public void parseSchool_validInput_success() throws Exception {
        School expectedSchool = new School(VALID_SCHOOL);
        assertEquals(expectedSchool, ParserUtil.parseSchool(VALID_SCHOOL));
        //capitalises
        assertEquals(expectedSchool, ParserUtil.parseSchool("valid primary school"));
        //trim leading and trailing whitespace
        assertEquals(expectedSchool, ParserUtil.parseSchool(WHITESPACE + VALID_SCHOOL + WHITESPACE));
        //dupe white space
        assertEquals(expectedSchool, ParserUtil.parseSchool("Valid    Primary    School"));
    }

    @Test
    public void parseLevel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLevel((String) null));
    }

    @Test
    public void parseLevel_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLevel(INVALID_LEVEL));
    }

    @Test
    public void parseLevel_validInput_success() throws Exception {
        Level expectedLevel1 = Level.createLevel(VALID_LEVEL_1);
        Level expectedLevel2 = Level.createLevel(VALID_LEVEL_2);
        assertEquals(expectedLevel1, ParserUtil.parseLevel(VALID_LEVEL_1));
        assertEquals(expectedLevel2, ParserUtil.parseLevel(VALID_LEVEL_2));
        //trim leading and trailing whitespace
        assertEquals(expectedLevel1, ParserUtil.parseLevel(WHITESPACE + VALID_LEVEL_1 + WHITESPACE));
        assertEquals(expectedLevel2, ParserUtil.parseLevel(WHITESPACE + VALID_LEVEL_2 + WHITESPACE));
        //short form
        assertEquals(expectedLevel1, ParserUtil.parseLevel("p1"));
        assertEquals(expectedLevel1, ParserUtil.parseLevel("pri1"));
        assertEquals(expectedLevel2, ParserUtil.parseLevel("s1"));
        assertEquals(expectedLevel2, ParserUtil.parseLevel("sec1"));

        //1 or more space
        assertEquals(expectedLevel1, ParserUtil.parseLevel("primary    1"));
        assertEquals(expectedLevel2, ParserUtil.parseLevel("secondary   1"));
        assertEquals(expectedLevel1, ParserUtil.parseLevel("p   1"));
        assertEquals(expectedLevel1, ParserUtil.parseLevel("pri   1"));
        assertEquals(expectedLevel2, ParserUtil.parseLevel("s   1"));
        assertEquals(expectedLevel2, ParserUtil.parseLevel("sec   1"));

        //case sensitive
        assertEquals(expectedLevel1, ParserUtil.parseLevel("PRIMARY1"));
        assertEquals(expectedLevel2, ParserUtil.parseLevel("SECONDARY1"));
        assertEquals(expectedLevel1, ParserUtil.parseLevel("P1"));
        assertEquals(expectedLevel1, ParserUtil.parseLevel("PRI1"));
        assertEquals(expectedLevel2, ParserUtil.parseLevel("S1"));
        assertEquals(expectedLevel2, ParserUtil.parseLevel("SEC1"));
    }

    @Test
    public void parseQualification_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQualification((String) null));
    }

    @Test
    public void parseQualification_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQualification(INVALID_QUALIFICATION));
    }

    @Test
    public void parseQualification_validInput_success() throws Exception {
        Qualification expectedQualification = new Qualification(VALID_QUALIFICATION);
        assertEquals(expectedQualification, ParserUtil.parseQualification(VALID_QUALIFICATION));
        //trim leading and trailing whitespace
        assertEquals(expectedQualification,
                ParserUtil.parseQualification(WHITESPACE + VALID_QUALIFICATION + WHITESPACE));
        //dupe white space
        assertEquals(expectedQualification, ParserUtil.parseQualification("MSc,,,,    Master   of    Science"));
    }

    @Test
    public void parseInstitution_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInstitution((String) null));
    }

    @Test
    public void parseInstitution_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInstitution(INVALID_INSTITUTION));
    }

    @Test
    public void parseInstitution_validInput_success() throws Exception {
        Institution expectedInstitution = new Institution(VALID_INSTITUTION);
        assertEquals(expectedInstitution, ParserUtil.parseInstitution(VALID_INSTITUTION));
        //trim leading and trailing whitespace
        assertEquals(expectedInstitution, ParserUtil.parseInstitution(WHITESPACE + VALID_INSTITUTION + WHITESPACE));
        //dupe white space
        assertEquals(expectedInstitution, ParserUtil.parseInstitution("University     of    Valid"));
    }

    @Test
    public void parseSubject_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSubject((String) null));
    }

    @Test
    public void parseSubject_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject(INVALID_SUBJECT));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("engli"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("mathemati"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("physi"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("chemist"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("biolo"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("elementa"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("e"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("addition"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("english eubject"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("mathematics eubject"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("physics eubject"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("chemistry eubject"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("biology eubject"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("emath eubject"));
        assertThrows(ParseException.class, () -> ParserUtil.parseSubject("amath eubject"));
    }

    @Test
    public void parseSubject_validInput_success() throws Exception {

        //trim leading and trailing whitespace
        assertEquals(Subject.createSubject(VALID_SUBJECT),
                ParserUtil.parseSubject(WHITESPACE + VALID_SUBJECT + WHITESPACE));
        //long and short form
        assertEquals(Subject.ENGLISH, ParserUtil.parseSubject("english"));
        assertEquals(Subject.ENGLISH, ParserUtil.parseSubject("eng"));
        assertEquals(Subject.MATHEMATICS, ParserUtil.parseSubject("mathematics"));
        assertEquals(Subject.MATHEMATICS, ParserUtil.parseSubject("maths"));
        assertEquals(Subject.MATHEMATICS, ParserUtil.parseSubject("math"));
        assertEquals(Subject.PHYSICS, ParserUtil.parseSubject("physics"));
        assertEquals(Subject.PHYSICS, ParserUtil.parseSubject("phys"));
        assertEquals(Subject.PHYSICS, ParserUtil.parseSubject("phy"));
        assertEquals(Subject.CHEMISTRY, ParserUtil.parseSubject("chemistry"));
        assertEquals(Subject.CHEMISTRY, ParserUtil.parseSubject("chem"));
        assertEquals(Subject.BIOLOGY, ParserUtil.parseSubject("biology"));
        assertEquals(Subject.BIOLOGY, ParserUtil.parseSubject("bio"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("elementarymathematics"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("elemmathematics"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("emathematics"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("emaths"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("emath"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("additionalmathematics"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("addmathematics"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("amathematics"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("amaths"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("amath"));

        //1 or more space
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("elementary   mathematics"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("elem   mathematics"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("e   mathematics"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("e   maths"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("e   math"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("additional   mathematics"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("add   mathematics"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("a   mathematics"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("a   maths"));
        assertEquals(Subject.AMATH, ParserUtil.parseSubject("a   math"));

        //case sensitive
        assertEquals(Subject.ENGLISH, ParserUtil.parseSubject("ENGLISH"));
        assertEquals(Subject.EMATH, ParserUtil.parseSubject("ELEMENTARY   MATHEMATICS"));
    }


    @Test
    public void parseDay_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDay((String) null));
    }

    @Test
    public void parseDay_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDay(INVALID_DAY));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("mond"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("tuesd"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("wednesd"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("thursd"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("frid"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("saturd"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("sund"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("on monday"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("on tuesday"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("on wednesday"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("on thursday"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("on friday"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("on saturday"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDay("on sunday"));
    }

    @Test
    public void parseDay_validInput_success() throws Exception {

        //trim leading and trailing whitespace
        assertEquals(Day.createDay(VALID_DAY),
                ParserUtil.parseDay(WHITESPACE + VALID_DAY + WHITESPACE));

        //long and short form
        assertEquals(Day.MONDAY, ParserUtil.parseDay("monday"));
        assertEquals(Day.MONDAY, ParserUtil.parseDay("mon"));
        assertEquals(Day.TUESDAY, ParserUtil.parseDay("tuesday"));
        assertEquals(Day.TUESDAY, ParserUtil.parseDay("tues"));
        assertEquals(Day.TUESDAY, ParserUtil.parseDay("tue"));
        assertEquals(Day.WEDNESDAY, ParserUtil.parseDay("wednesday"));
        assertEquals(Day.WEDNESDAY, ParserUtil.parseDay("wed"));
        assertEquals(Day.THURSDAY, ParserUtil.parseDay("thursday"));
        assertEquals(Day.THURSDAY, ParserUtil.parseDay("thurs"));
        assertEquals(Day.THURSDAY, ParserUtil.parseDay("thur"));
        assertEquals(Day.THURSDAY, ParserUtil.parseDay("thu"));
        assertEquals(Day.FRIDAY, ParserUtil.parseDay("friday"));
        assertEquals(Day.FRIDAY, ParserUtil.parseDay("fri"));
        assertEquals(Day.SATURDAY, ParserUtil.parseDay("saturday"));
        assertEquals(Day.SATURDAY, ParserUtil.parseDay("sat"));
        assertEquals(Day.SUNDAY, ParserUtil.parseDay("sunday"));
        assertEquals(Day.SUNDAY, ParserUtil.parseDay("sun"));

        //case sensitive
        assertEquals(Day.MONDAY, ParserUtil.parseDay("MONDAY"));
        assertEquals(Day.SUNDAY, ParserUtil.parseDay("SUN"));
    }

    @Test
    public void parseTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTime((String) null));
    }

    @Test
    public void parseTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("98:00 - 99:00"));
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("198:00 - 199:00"));
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("12:0 - 13:0"));
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("12:99 - 13:99"));
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("13pm - 14pm"));
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("13am - 14am"));
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("12:00 until 13:00"));

        //endtime before start time
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("12:00 - 11:00"));
        assertThrows(ParseException.class, () -> ParserUtil.parseTime("23:00 - 00:01"));
    }

    @Test
    public void parseTime_validInput_success() throws Exception {

        //trim leading and trailing whitespace

        assertEquals(new Time(VALID_TIME.split("-")[0], VALID_TIME.split("-")[1]),
                ParserUtil.parseTime(WHITESPACE + VALID_TIME + WHITESPACE));

        //different separator
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("16:00-17:00"));
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("16:00to17:00"));
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("16:00 17:00"));

        //different time format
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("4pm-5pm"));
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("1600-1700"));


        //mixing time format
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("4pm-17:00"));
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("1600-5pm"));
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("1600-17:00"));

        //duplicate whitespace
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("16:00   -   17:00"));
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("16:00   to   17:00"));
        assertEquals(new Time("16:00", "17:00"), ParserUtil.parseTime("16:00     17:00"));

        //allow end on midnight
        assertEquals(new Time("23:00", "00:00"), ParserUtil.parseTime("11pm - 12am"));
        assertEquals(new Time("23:00", "00:00"), ParserUtil.parseTime("23:00 - 00:00"));

        //24:00
        assertEquals(new Time("23:00", "00:00"), ParserUtil.parseTime("23:00 - 24:00"));
    }


    @Test
    public void parsePersonName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePersonName((String) null));
    }

    @Test
    public void parsePersonName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePersonName(INVALID_NAME));
    }

    @Test
    public void parsePersonName_validValueWithoutWhitespace_returnsName() throws Exception {
        seedu.address.model.person.Name expectedName = new seedu.address.model.person.Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parsePersonName(VALID_NAME));
    }

    @Test
    public void parsePersonName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        seedu.address.model.person.Name expectedName = new seedu.address.model.person.Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parsePersonName(nameWithWhitespace));
    }

    @Test
    public void parseClassName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClassName((String) null));
    }

    @Test
    public void parseClassName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClassName(INVALID_NAME));
    }

    @Test
    public void parseClassName_validValueWithoutWhitespace_returnsName() throws Exception {
        seedu.address.model.tuitionclass.Name expectedName = new seedu.address.model.tuitionclass.Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseClassName(VALID_NAME));
    }

    @Test
    public void parseClassName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        seedu.address.model.tuitionclass.Name expectedName = new seedu.address.model.tuitionclass.Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseClassName(nameWithWhitespace));
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
    public void parseRelationship_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRelationship((String) null));
    }

    @Test
    public void parseRelationship_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRelationship(INVALID_RELATIONSHIP));
    }

    @Test
    public void parseRelationship_validValueWithoutWhitespace_returnsRelationship() throws Exception {
        Relationship expectedRelationship = Relationship.createRelationship(VALID_RELATIONSHIP);
        assertEquals(expectedRelationship, ParserUtil.parseRelationship(VALID_RELATIONSHIP));
    }

    @Test
    public void parseRelationship_validValueWithWhitespace_returnsTrimmedRelationship() throws Exception {
        String relationshipWithWhitespace = WHITESPACE + VALID_RELATIONSHIP + WHITESPACE;
        Relationship expectedRelationship = Relationship.createRelationship(VALID_RELATIONSHIP);
        assertEquals(expectedRelationship, ParserUtil.parseRelationship(relationshipWithWhitespace));
    }

}
