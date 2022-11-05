package seedu.address.model.person.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_11;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_9;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.XAVIER;
import static seedu.address.testutil.TypicalPersons.ZEPHYR;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Lesson;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.UserBuilder;

public class UserTest {

    private final EmptyUser emptyUser = new EmptyUser();
    // existing valid lesson of a Person, existing start time - 12:00, existing end time - 13:00
    private final Lesson existingLesson = new LessonBuilder().withDay("1")
            .withStartTime("12:00").withEndTime("13:00").build();
    // test case 1 : start time is after or same as the existing end time - valid
    private final Lesson lesson1 = new LessonBuilder().withDay("1").withStartTime("13:00").withEndTime("14:00").build();
    // test case 2 : end time is before or the same as existing start time - valid
    private final Lesson lesson2 = new LessonBuilder().withDay("1").withStartTime("11:00").withEndTime("12:00").build();
    // test case 3a : start time is the same - invalid
    private final Lesson lesson3a = new LessonBuilder().withDay("1")
            .withStartTime("12:00").withEndTime("14:00").build();
    // test case 3b : end time is the same - invalid
    private final Lesson lesson3b = new LessonBuilder().withDay("1")
            .withStartTime("11:00").withEndTime("13:00").build();
    // test case 4 : start time is later, end time is earlier - invalid
    private final Lesson lesson4 = new LessonBuilder().withDay("1").withStartTime("12:15").withEndTime("12:45").build();
    // test case 5 : start time is later, but earlier than existing end time - invalid
    private final Lesson lesson5 = new LessonBuilder().withDay("1").withStartTime("12:30").withEndTime("13:30").build();
    // test case 6 : end time is earlier, but later than existing start time - invalid
    private final Lesson lesson6 = new LessonBuilder().withDay("1").withStartTime("11:30").withEndTime("12:30").build();
    // test case 7 : different day, same existing start and end time - valid
    private final Lesson lesson7 = new LessonBuilder().withDay("7").withStartTime("12:00").withEndTime("13:00").build();


    @Test
    public void existingUser_equals() {
        // same values -> returns true
        User zephyrCopy = new UserBuilder(ZEPHYR).build();
        assertTrue(ZEPHYR.equals(zephyrCopy));

        // same object -> returns true
        assertTrue(ZEPHYR.equals(ZEPHYR));

        // null -> returns false
        assertFalse(ZEPHYR.equals(null));

        // different type -> returns false
        assertFalse(ZEPHYR.equals(5));

        // different user -> returns false
        assertFalse(ZEPHYR.equals(XAVIER));

        // empty user -> returns false
        assertFalse(ZEPHYR.equals(emptyUser));

        // different name -> returns false
        User editedZephyr = new UserBuilder(ZEPHYR).withName(VALID_NAME_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different phone -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different email -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different address -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different github -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withGithub(VALID_GITHUB_BOB).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different current modules -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withCurrentModules(VALID_MODULE_7).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different previous modules -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withPreviousModules(VALID_MODULE_9).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

        // different planned modules -> returns false
        editedZephyr = new UserBuilder(ZEPHYR).withPlannedModules(VALID_MODULE_11).build();
        assertFalse(ZEPHYR.equals(editedZephyr));

    }

    @Test
    public void existingUser_hashcode() {
        // same values -> returns same hashcode
        User zephyrCopy = new UserBuilder(ZEPHYR).build();
        assertEquals(ZEPHYR.hashCode(), zephyrCopy.hashCode());

        // different user -> returns different hashcode
        assertNotEquals(ZEPHYR.hashCode(), XAVIER.hashCode());

        // empty user -> returns different hashcode
        assertNotEquals(ZEPHYR.hashCode(), emptyUser.hashCode());

        // different name -> returns different hashcode
        User editedZephyr = new UserBuilder(ZEPHYR).withName(VALID_NAME_BOB).build();
        assertNotEquals(ZEPHYR.hashCode(), editedZephyr.hashCode());

        // different phone -> returns different hashcode
        editedZephyr = new UserBuilder(ZEPHYR).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ZEPHYR.hashCode(), editedZephyr.hashCode());


        // different email -> returns different hashcode
        editedZephyr = new UserBuilder(ZEPHYR).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ZEPHYR.hashCode(), editedZephyr.hashCode());

        // different address -> returns different hashcode
        editedZephyr = new UserBuilder(ZEPHYR).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(ZEPHYR.hashCode(), editedZephyr.hashCode());

        // different github -> returns different hashcode
        editedZephyr = new UserBuilder(ZEPHYR).withGithub(VALID_GITHUB_BOB).build();
        assertNotEquals(ZEPHYR.hashCode(), editedZephyr.hashCode());

        // different current modules -> returns different hashcode
        editedZephyr = new UserBuilder(ZEPHYR).withCurrentModules(VALID_MODULE_7).build();
        assertNotEquals(ZEPHYR.hashCode(), editedZephyr.hashCode());

        // different previous modules -> returns different hashcode
        editedZephyr = new UserBuilder(ZEPHYR).withPreviousModules(VALID_MODULE_9).build();
        assertNotEquals(ZEPHYR.hashCode(), editedZephyr.hashCode());

        // different planned modules -> returns different hashcode
        editedZephyr = new UserBuilder(ZEPHYR).withPlannedModules(VALID_MODULE_11).build();
        assertNotEquals(ZEPHYR.hashCode(), editedZephyr.hashCode());

    }

    @Test
    public void emptyUser_equals() {
        // same object -> returns true
        assertTrue(emptyUser.equals(emptyUser));

        // both empty user -> returns true
        assertTrue(emptyUser.equals(new EmptyUser()));

        // null -> returns false
        assertFalse(emptyUser.equals(null));

        // different type -> returns false
        assertFalse(emptyUser.equals(5));
    }

    @Test
    public void emptyUser_toString() {
        // correct string representation of emptyUser
        assertEquals(emptyUser.toString(), "no user exists");

        // wrong string representation of emptyUser
        assertNotEquals(emptyUser.toString(), "something else");
    }

    @Test
    public void addLessonWithSameStartAndEndTime_throwsCommandException() throws CommandException {
        User sampleUser = new UserBuilder().withName("Aaron Tay").withPhone("98765432")
                .withEmail("aarontay@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aarontay").build();
        sampleUser.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> sampleUser.addLesson(existingLesson));
    }

    @Test
    public void addLessonWithStartTimeAfterExistingEndTime_isValid() throws CommandException {
        User sampleUser = new UserBuilder().withName("Aaron Tan").withPhone("98765432")
                .withEmail("aarontan@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aarontan").build();
        sampleUser.addLesson(existingLesson);
        sampleUser.addLesson(lesson1);
        Set<Lesson> lessonsAddedToSamplePerson = sampleUser.getLessons();
        int expectedNumberOfLessons = 2;
        assertEquals(expectedNumberOfLessons, lessonsAddedToSamplePerson.size());
    }

    @Test
    public void addLessonWithEndTimeBeforeExistingStartTime_isValid() throws CommandException {
        User sampleUser = new UserBuilder().withName("Aaron Teo").withPhone("98765432")
                .withEmail("aaronteo@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronteo").build();
        sampleUser.addLesson(existingLesson);
        sampleUser.addLesson(lesson2);
        Set<Lesson> lessonsAddedToSamplePerson = sampleUser.getLessons();
        int expectedNumberOfLessons = 2;
        assertEquals(expectedNumberOfLessons, lessonsAddedToSamplePerson.size());
    }

    @Test
    public void addLessonWithSameStartTime_throwsCommandException() throws CommandException {
        User sampleUser = new UserBuilder().withName("Aaron Sng").withPhone("98765432")
                .withEmail("aaronsng@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronsng").build();
        sampleUser.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> sampleUser.addLesson(lesson3a));
    }

    @Test
    public void addLessonWithSameEndTime_throwsCommandException() throws CommandException {
        User sampleUser = new UserBuilder().withName("Aaron Lee").withPhone("98765432")
                .withEmail("aaronlee@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronlee").build();
        sampleUser.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> sampleUser.addLesson(lesson3b));
    }

    @Test
    public void addLessonWithLaterStartTimeAndEarlierEndTime_throwsCommandException() throws CommandException {
        User sampleUser = new UserBuilder().withName("Aaron Koh").withPhone("98765432")
                .withEmail("aaronkoh@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronkoh").build();
        sampleUser.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> sampleUser.addLesson(lesson4));
    }

    @Test
    public void addLessonWithLaterStartTimeButEarlierThanExistingEndTime_throwsCommandException()
            throws CommandException {
        User sampleUser = new UserBuilder().withName("Aaron Woo").withPhone("98765432")
                .withEmail("aaronwoo@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronwoo").build();
        sampleUser.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> sampleUser.addLesson(lesson5));
    }

    @Test
    public void addLessonWithEarlierEndTimeButLaterThanExistingEndTime_throwsCommandException()
            throws CommandException {
        User sampleUser = new UserBuilder().withName("Aaron Poh").withPhone("98765432")
                .withEmail("aaronpoh@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronpoh").build();
        sampleUser.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> sampleUser.addLesson(lesson6));
    }

    @Test
    public void addLessonWithDifferentDay_isValid() throws CommandException {
        User sampleUser = new UserBuilder().withName("Aaron Ang").withPhone("98765432")
                .withEmail("aaronang@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronang").build();
        sampleUser.addLesson(existingLesson);
        sampleUser.addLesson(lesson7);
        Set<Lesson> lessonsAddedToSamplePerson = sampleUser.getLessons();
        int expectedNumberOfLessons = 2;
        assertEquals(expectedNumberOfLessons, lessonsAddedToSamplePerson.size());
    }
}
