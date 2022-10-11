package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A utility class containing an {@code AddressBook} object to be used in tests.
 */
public class TypicalAddressBook {
    private TypicalAddressBook() {} //prevents instantiation

    /**
     * Returns a valid AddressBook with all valid Tutors, Students and TuitionClasses.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();

        List<Tutor> tutorList = new ArrayList<>(Arrays.asList(TypicalTutors.TUTOR1, TypicalTutors.TUTOR2));
        for (Tutor t : tutorList) {
            ab.addPerson(t);
        }

        List<Student> studentList = new ArrayList<>(Arrays.asList(TypicalStudents.STUDENT1, TypicalStudents.STUDENT2));
        for (Student s : studentList) {
            ab.addPerson(s);
        }

        List<TuitionClass> tuitionClassList = new ArrayList<>(Arrays.asList(TypicalTuitionClasses.TUITIONCLASS1,
                TypicalTuitionClasses.TUITIONCLASS2));
        for (TuitionClass tc : tuitionClassList) {
            ab.addTuitionClass(tc);
        }
        return ab;

    }
}
