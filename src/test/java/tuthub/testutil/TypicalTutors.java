package tuthub.testutil;

import static tuthub.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_RATING_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_RATING_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tuthub.logic.commands.CommandTestUtil.VALID_TEACHINGNOMINATION_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_TEACHINGNOMINATION_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_YEAR_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tuthub.model.Tuthub;
import tuthub.model.tutor.Tutor;

/**
 * A utility class containing a list of {@code Tutor} objects to be used in tests.
 */
public class TypicalTutors {

    public static final Tutor ALICE = new TutorBuilder().withName("Alice Pauline")
            .withEmail("e0000000")
            .withModule("CS2100").withYear("3").withPhone("94351253").withStudentId("A1234569L")
            .withTeachingNomination("1").withRating("4.5").withComment("Finishes tasks quickly")
            .withTags("friends").build();
    public static final Tutor BENSON = new TutorBuilder().withName("Benson Meier")
            .withEmail("e0101010").withPhone("98765432")
            .withModule("CS2100").withYear("3").withStudentId("A0000009Y")
            .withTeachingNomination("1").withRating("2.5")
            .withTags("owesMoney", "friends").build();
    public static final Tutor CARL = new TutorBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("e1234567").withModule("CS2100").withYear("3").withStudentId("A9875647U")
            .withTeachingNomination("1").withRating("4.9")
            .build();
    public static final Tutor DANIEL = new TutorBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("e7654321").withModule("CS2100").withYear("3").withStudentId("A9345647M")
            .withTeachingNomination("1").withRating("1.5")
            .withTags("friends").build();
    public static final Tutor ELLE = new TutorBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("e1212121").withModule("CS2100").withYear("3").withStudentId("A8768974P")
            .withTeachingNomination("1").withRating("3.9")
            .build();
    public static final Tutor FIONA = new TutorBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("e1231231").withModule("CS2100").withYear("3").withStudentId("A9870921H")
            .withTeachingNomination("1").withRating("2.7")
            .build();
    public static final Tutor GEORGE = new TutorBuilder().withName("George Best").withPhone("9482442")
            .withEmail("e1321321").withModule("CS2100").withYear("3").withStudentId("A0673201Y")
            .withTeachingNomination("1").withRating("4.2")
            .build();

    // Manually added
    public static final Tutor HOON = new TutorBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("e2222222").withModule("cs2103t").withYear("3").withStudentId("A1234758N")
            .withTeachingNomination("1").withRating("1.1")
            .build();
    public static final Tutor IDA = new TutorBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("e3333333").withModule("cs2100").withYear("4").withStudentId("A0965302K")
            .withTeachingNomination("1").withRating("4.8")
            .build();
    public static final Tutor JACKSON = new TutorBuilder().withName("Jackson Tan").withPhone("98765432")
            .withEmail("e4444444").withModule("cs2105").withYear("2").withStudentId("A2345678Y")
            .withTeachingNomination("1").withRating("4.6")
            .build();
    public static final Tutor KEN = new TutorBuilder().withName("Ken Tan").withPhone("99999999")
            .withEmail("e5555555").withModule("cs2105").withYear("3").withStudentId("A2345678X")
            .withTeachingNomination("2").withRating("4.9")
            .build();
    public static final Tutor LOPEZ = new TutorBuilder().withName("Lopez Tan").withPhone("99999999")
            .withEmail("e6666666").withModule("cs2105").withYear("3").withStudentId("A2345678X")
            .withTeachingNomination("2").withRating("4.9").withTags("friends")
            .build();
    public static final Tutor MIKE = new TutorBuilder().withName("Mike Tan").withPhone("99999999")
            .withEmail("e7777777").withModule("cs2105").withYear("3").withStudentId("A2345678X")
            .withTeachingNomination("2").withRating("4.9").withTags("owesMoney")
            .build();

    // Manually added - Tutor's details found in {@code CommandTestUtil}
    public static final Tutor AMY = new TutorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withModule(VALID_MODULE_AMY).withYear(VALID_YEAR_AMY)
            .withStudentId(VALID_STUDENTID_AMY).withTeachingNomination(VALID_TEACHINGNOMINATION_AMY)
            .withRating(VALID_RATING_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Tutor BOB = new TutorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withModule(VALID_MODULE_BOB).withYear(VALID_YEAR_BOB)
            .withStudentId(VALID_STUDENTID_BOB).withTeachingNomination(VALID_TEACHINGNOMINATION_BOB)
            .withRating(VALID_RATING_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTutors() {} // prevents instantiation

    /**
     * Returns an {@code Tuthub} with all the typical tutors.
     */
    public static Tuthub getTypicalTuthub() {
        Tuthub ab = new Tuthub();
        for (Tutor tutor : getTypicalTutors()) {
            ab.addTutor(tutor);
        }
        return ab;
    }

    public static List<Tutor> getTypicalTutors() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
