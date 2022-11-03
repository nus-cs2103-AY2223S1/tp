package seedu.taassist.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Date;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.Address;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentModuleData;

/**
 * Contains utility methods for populating {@code TaAssist} with sample data.
 */
public class SampleDataUtil {

    private static final Session ASSIGNMENT_1 = new Session("Assignment 1", new Date(LocalDate.parse("2022-01-01")));
    private static final Session LAB_1 = new Session("Lab 1", new Date(LocalDate.parse("2022-10-12")));
    private static final Session TUTORIAL_1 = new Session("Tutorial 1", new Date(LocalDate.of(2022, 2, 28)));

    private static final ModuleClass CS1101S = new ModuleClass("CS1101S", List.of(LAB_1));
    private static final ModuleClass CS1231S = new ModuleClass("CS1231S", List.of(ASSIGNMENT_1, TUTORIAL_1));

    private static List<ModuleClass> getSampleModuleClasses() {
        return List.of(CS1101S, CS1231S);
    }

    private static List<Student> getSampleStudents() {
        return List.of(
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getModuleDataList(CS1231S)).updateGrade(CS1231S, ASSIGNMENT_1, 100),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getModuleDataList(CS1101S, CS1231S)).updateGrade(CS1101S, LAB_1, 50),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getModuleDataList(CS1231S)).updateGrade(CS1231S, ASSIGNMENT_1, 50),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getModuleDataList(CS1101S)).updateGrade(CS1101S, LAB_1, 100),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getModuleDataList(CS1231S)).updateGrade(CS1231S, TUTORIAL_1, 1),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getModuleDataList(CS1101S))
        );
    }

    public static ReadOnlyTaAssist getSampleTaAssist() {
        TaAssist taAssist = new TaAssist();
        getSampleModuleClasses().forEach(taAssist::addModuleClass);
        getSampleStudents().forEach(taAssist::addStudent);
        return taAssist;
    }

    /**
     * Returns a list of {@code StudentModuleData}, containing the list of modules given.
     */
    private static List<StudentModuleData> getModuleDataList(ModuleClass... modules) {
        return Arrays.stream(modules)
                .map(StudentModuleData::new)
                .collect(Collectors.toList());
    }

}
