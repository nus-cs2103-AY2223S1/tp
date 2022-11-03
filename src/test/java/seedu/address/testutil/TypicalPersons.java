package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_PROGRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_PROGRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOMEWORK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOMEWORK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_PLAN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_PLAN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("alice Pauline")
            .withPhone("94351253").withLessonPlan("Algorithms").withHomework("Science worksheet")
            .withAttendance("2019-01-01").withSession("Tue 09:00").withGradeProgress("English: A+")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withHomework("Science Worksheet", "Math asst").withPhone("98765432").withLessonPlan("Trigonometry")
            .withAttendance("2022-07-12", "2022-07-17")
            .withSession("MoN 08:30")
            .withGradeProgress("Math: A")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withLessonPlan("Cell structure").withAttendance("2022-06-07").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withLessonPlan("Chemistry")
            .withAttendance("2022-05-07" , "2022-12-01", "2022-12-17")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822243")
            .withLessonPlan("Sec 4 Physics")
            .withAttendance("2022-06-05" , "2022-07-07" , "2022-08-08")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824273")
            .withLessonPlan("Higher chinese").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94824425")
            .withLessonPlan("Javascript").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withLessonPlan("Python").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withLessonPlan("Geography chapter 1-5").build();
    public static final Person JOLENE = new PersonBuilder().withName("Jolene Goons").withPhone("93458732")
            .withLessonPlan("CSS").withSession("Sun 01:00", "Tue 08:30", "Wed 07:00").build();
    public static final Person KALEY = new PersonBuilder().withName("Kaley Huang").withPhone("93452109")
            .withLessonPlan("HTML").withSession("Sun 03:00", "Tue 07:00").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withLessonPlan(VALID_LESSON_PLAN_AMY)
            .withHomework(VALID_HOMEWORK_AMY)
            .withGradeProgress(VALID_GRADE_PROGRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withLessonPlan(VALID_LESSON_PLAN_BOB)
            .withHomework(VALID_HOMEWORK_BOB)
            .withGradeProgress(VALID_GRADE_PROGRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
    public static final String ALICE_WITH_SPACES = "alice     Pauline";

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getCopyOfTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getCopyOfTypicalPersons() {
        final Person aliceCopy = new PersonBuilder(ALICE).build();
        final Person bensonCopy = new PersonBuilder(BENSON).build();
        final Person carlCopy = new PersonBuilder(CARL).build();
        final Person danielCopy = new PersonBuilder(DANIEL).build();
        final Person elleCopy = new PersonBuilder(ELLE).build();
        final Person fionaCopy = new PersonBuilder(FIONA).build();
        final Person georgeCopy = new PersonBuilder(GEORGE).build();
        return new ArrayList<>(Arrays.asList(aliceCopy, bensonCopy, carlCopy, danielCopy, elleCopy,
                fionaCopy, georgeCopy));
    }
}
