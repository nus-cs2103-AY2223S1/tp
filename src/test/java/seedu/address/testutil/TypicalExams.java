package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.*;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalExams {
    public static final Exam EXAMONE = new ExamBuilder().withDescription("Exam one").withModule("CS2030S").withDate("20-08-2023;").build();
    public static final Exam EXAMTWO = new ExamBuilder().withDescription("Exam two").withModule("CS2040S").withDate("20-10-2023").build();


    private TypicalExams() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical exams.
     */
    public static AddressBook getTypicalAddressBookForExam() {
        Module firstModule = new Module(new ModuleCode("CS2030S"), new ModuleName("Programming"), new ModuleCredit(4));
        Module secondModule = new Module(new ModuleCode("CS2040S"), new ModuleName("Algorithm"), new ModuleCredit(4));
        AddressBook ab = new AddressBook();
        ab.addModule(firstModule);
        ab.addModule(secondModule);
        for (Exam exam : getTypicalExams()) {
            ab.addExam(exam);
        }
        return ab;
    }

    public static List<Exam> getTypicalExams() {
        return new ArrayList<>(Arrays.asList(EXAMONE,EXAMTWO));
    }
}