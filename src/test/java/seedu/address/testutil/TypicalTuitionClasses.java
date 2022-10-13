package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_CLASS1;
import static seedu.address.testutil.TypicalStudents.STUDENT1;
import static seedu.address.testutil.TypicalStudents.STUDENT2;
import static seedu.address.testutil.TypicalTutors.TUTOR1;
import static seedu.address.testutil.TypicalTutors.TUTOR2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A utility class containing a list of {@code TuitionClass} objects to be used in tests.
 */
public class TypicalTuitionClasses {

    public static final TuitionClass TUITIONCLASS1 =
            new TuitionClassBuilder().withName("P2MATH")
                    .withSubject("MATHEMATICS")
                    .withLevel("PRIMARY2")
                    .withDay("MONDAY")
                    .withTime("10:00", "12:00")
                    .withTags("tough")
                    .withStudents(STUDENT1, STUDENT2)
                    .withTutors(TUTOR1, TUTOR2)
                    .build();

    public static final TuitionClass TUITIONCLASS2 =
            new TuitionClassBuilder().withName("P5ENG")
                    .withSubject("ENGLISH")
                    .withLevel("PRIMARY5")
                    .withDay("FRIDAY")
                    .withTime("18:00", "20:00")
                    .withTags("easy")
                    .withStudents(STUDENT1, STUDENT2)
                    .withTutors(TUTOR1, TUTOR2)
                    .build();

    public static final TuitionClass CHEMISTRY_CLASS = new TuitionClassBuilder().withName(VALID_NAME_CLASS1)
            .withSubject(VALID_SUBJECT_CLASS1).withLevel(VALID_LEVEL_CLASS1).withDay(VALID_DAY_CLASS1)
            .withTime(VALID_STARTTIME_CLASS1, VALID_ENDTIME_CLASS1).build();


    private TypicalTuitionClasses() {} // prevents instantiation

    public static AddressBook getTypicalTuitionClassesAddressBook() {
        AddressBook ab = new AddressBook();
        List<TuitionClass> tuitionClassList = new ArrayList<>(Arrays.asList(TypicalTuitionClasses.TUITIONCLASS1,
                TypicalTuitionClasses.TUITIONCLASS2));
        for (TuitionClass c : tuitionClassList) {
            ab.addTuitionClass(c);
        }
        return ab;
    }
}
