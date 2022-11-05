package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_11;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_9;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Lesson;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

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
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withGithub(VALID_GITHUB_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different github -> returns false
        editedAlice = new PersonBuilder(ALICE).withGithub(VALID_GITHUB_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different current modules -> returns false
        editedAlice = new PersonBuilder(ALICE).withCurrentModules(VALID_MODULE_7).build();
        assertFalse(ALICE.equals(editedAlice));

        // different previous modules -> returns false
        editedAlice = new PersonBuilder(ALICE).withPreviousModules(VALID_MODULE_9).build();
        assertFalse(ALICE.equals(editedAlice));

        // different planned modules -> returns false
        editedAlice = new PersonBuilder(ALICE).withPlannedModules(VALID_MODULE_11).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertEquals(ALICE.hashCode(), aliceCopy.hashCode());

        // different person -> returns different hashcode
        assertNotEquals(ALICE.hashCode(), BOB.hashCode());

        // different name -> returns different hashcode
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different phone -> returns different hashcode
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different email -> returns different hashcode
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different address -> returns different hashcode
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different github -> returns different hashcode
        editedAlice = new PersonBuilder(ALICE).withGithub(VALID_GITHUB_BOB).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different tags -> returns different hashcode
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different current modules -> returns different hashcode
        editedAlice = new PersonBuilder(ALICE).withCurrentModules(VALID_MODULE_7).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different previous modules -> returns different hashcode
        editedAlice = new PersonBuilder(ALICE).withPreviousModules(VALID_MODULE_9).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());

        // different planned modules -> returns different hashcode
        editedAlice = new PersonBuilder(ALICE).withPlannedModules(VALID_MODULE_11).build();
        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());
    }

    @Test
    public void addLessonWithSameStartAndEndTime_throwsCommandException() throws CommandException {
        Person samplePerson = new PersonBuilder().withName("Aaron Tay").withPhone("98765432")
                .withEmail("aarontay@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aarontay").build();
        samplePerson.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> samplePerson.addLesson(existingLesson));
    }

    @Test
    public void addLessonWithStartTimeAfterExistingEndTime_isValid() throws CommandException {
        Person samplePerson = new PersonBuilder().withName("Aaron Tan").withPhone("98765432")
                .withEmail("aarontan@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aarontan").build();
        samplePerson.addLesson(existingLesson);
        samplePerson.addLesson(lesson1);
        Set<Lesson> lessonsAddedToSamplePerson = samplePerson.getLessons();
        int expectedNumberOfLessons = 2;
        assertEquals(expectedNumberOfLessons, lessonsAddedToSamplePerson.size());
    }

    @Test
    public void addLessonWithEndTimeBeforeExistingStartTime_isValid() throws CommandException {
        Person samplePerson = new PersonBuilder().withName("Aaron Teo").withPhone("98765432")
                .withEmail("aaronteo@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronteo").build();
        samplePerson.addLesson(existingLesson);
        samplePerson.addLesson(lesson2);
        Set<Lesson> lessonsAddedToSamplePerson = samplePerson.getLessons();
        int expectedNumberOfLessons = 2;
        assertEquals(expectedNumberOfLessons, lessonsAddedToSamplePerson.size());
    }

    @Test
    public void addLessonWithSameStartTime_throwsCommandException() throws CommandException {
        Person samplePerson = new PersonBuilder().withName("Aaron Sng").withPhone("98765432")
                .withEmail("aaronsng@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronsng").build();
        samplePerson.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> samplePerson.addLesson(lesson3a));
    }

    @Test
    public void addLessonWithSameEndTime_throwsCommandException() throws CommandException {
        Person samplePerson = new PersonBuilder().withName("Aaron Lee").withPhone("98765432")
                .withEmail("aaronlee@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronlee").build();
        samplePerson.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> samplePerson.addLesson(lesson3b));
    }

    @Test
    public void addLessonWithLaterStartTimeAndEarlierEndTime_throwsCommandException() throws CommandException {
        Person samplePerson = new PersonBuilder().withName("Aaron Koh").withPhone("98765432")
                .withEmail("aaronkoh@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronkoh").build();
        samplePerson.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> samplePerson.addLesson(lesson4));
    }

    @Test
    public void addLessonWithLaterStartTimeButEarlierThanExistingEndTime_throwsCommandException()
            throws CommandException {
        Person samplePerson = new PersonBuilder().withName("Aaron Woo").withPhone("98765432")
                .withEmail("aaronwoo@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronwoo").build();
        samplePerson.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> samplePerson.addLesson(lesson5));
    }

    @Test
    public void addLessonWithEarlierEndTimeButLaterThanExistingEndTime_throwsCommandException()
            throws CommandException {
        Person samplePerson = new PersonBuilder().withName("Aaron Poh").withPhone("98765432")
                .withEmail("aaronpoh@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronpoh").build();
        samplePerson.addLesson(existingLesson);
        assertThrows(CommandException.class, () -> samplePerson.addLesson(lesson6));
    }

    @Test
    public void addLessonWithDifferentDay_isValid() throws CommandException {
        Person samplePerson = new PersonBuilder().withName("Aaron Ang").withPhone("98765432")
                .withEmail("aaronang@gmail.com").withAddress("10 Heng Mui Keng Terrace")
                .withGithub("aaronang").build();
        samplePerson.addLesson(existingLesson);
        samplePerson.addLesson(lesson7);
        Set<Lesson> lessonsAddedToSamplePerson = samplePerson.getLessons();
        int expectedNumberOfLessons = 2;
        assertEquals(expectedNumberOfLessons, lessonsAddedToSamplePerson.size());
    }

}
