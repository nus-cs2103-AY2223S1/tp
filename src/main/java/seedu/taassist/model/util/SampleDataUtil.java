package seedu.taassist.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.StudentModuleData;
import seedu.taassist.model.student.Address;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;
import seedu.taassist.model.student.Student;

/**
 * Contains utility methods for populating {@code TaAssist} with sample data.
 */
public class SampleDataUtil {

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getModuleDataList(new ModuleClass("CS1231S"))),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getModuleDataList(new ModuleClass("CS1101S"), new ModuleClass("CS1231S"))),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getModuleDataList(new ModuleClass("CS1231S"))),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getModuleDataList(new ModuleClass("CS1101S"))),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getModuleDataList(new ModuleClass("CS1231S"))),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getModuleDataList(new ModuleClass("CS1101S")))
        };
    }

    public static ReadOnlyTaAssist getSampleTaAssist() {
        TaAssist taAssist = new TaAssist();
        for (Student student : getSampleStudents()) {
            student.getModuleClasses().stream()
                    .filter(c -> !taAssist.hasModuleClass(c))
                    .forEach(taAssist::addModuleClass);
            taAssist.addStudent(student);
        }
        return taAssist;
    }

    /**
     * Returns a list of {@code StudentModuleData}, containing the list of modules given.
     * The module classes will not have any sessions.
     */
    public static List<StudentModuleData> getModuleDataList(ModuleClass... modules) {
        return Arrays.stream(modules)
                .map(module -> new ModuleClass(module.getClassName()))
                .map(StudentModuleData::new)
                .collect(Collectors.toList());
    }

}
