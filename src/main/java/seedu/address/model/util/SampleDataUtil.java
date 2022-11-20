package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ProfNus;
import seedu.address.model.ReadOnlyProfNus;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleDescription;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.schedule.ClassType;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Venue;
import seedu.address.model.module.schedule.Weekdays;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ProfNus} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSampleStudents() {
        return new Person[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("attentive"),
                    new StudentId("A0123457W"), new TelegramHandle("@good_user"),
                    getModuleCodeSet("CS2040S", "CS2030S"), getModuleCodeSet("CS1101S"),
                    getClassGroups("CS2030S:Tut07")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("good"),
                    new StudentId("A0123627W"), new TelegramHandle("@good_1234"),
                    getModuleCodeSet("CS2030S"), getModuleCodeSet("CS1101S"),
                    getClassGroups("CS1101S:Tut02")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("lazy"),
                    new StudentId("A0523457W"), new TelegramHandle("@good_1234r"),
                    getModuleCodeSet("CS2030S"), getModuleCodeSet("CS1101S"),
                    getClassGroups("CS1101S:Tut03")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("hardworking"),
                    new StudentId("A0125357W"), new TelegramHandle("@1241_user"),
                    getModuleCodeSet("CS2030S"), getModuleCodeSet("CS1101S"),
                    getClassGroups("CS1101S:Rec09")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("patient"),
                    new StudentId("A0897457W"), new TelegramHandle("@bad_user"),
                    getModuleCodeSet("CS2030S"), getModuleCodeSet("CS1101S"),
                    getClassGroups("CS1101S:Tut07")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("late"),
                    new StudentId("A0123234K"), new TelegramHandle("@new_user"),
                    getModuleCodeSet("CS2030S"), getModuleCodeSet("CS1101S"),
                    getClassGroups("CS1101S:Tut07"))
        };
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new ModuleName("Programming Methodology 1"), new ModuleCode("CS1101S"),
                    new ModuleDescription("Intro mod"), getTagSet("Fun")),
            new Module(new ModuleName("Programming Methodology 2"), new ModuleCode("CS2030S"),
                    new ModuleDescription("Continued mod"), getTagSet("Fun")),
            new Module(new ModuleName("Data Structure and Algos"), new ModuleCode("CS2040S"),
                    new ModuleDescription("Intro mod"), getTagSet("Fun")),
        };
    }

    public static ReadOnlyProfNus getSampleProfNus() {
        ProfNus sampleAb = new ProfNus();
        for (Person samplePerson : getSampleStudents()) {
            sampleAb.addPerson(samplePerson);
            if (samplePerson instanceof Student) {
                Student temp = (Student) samplePerson;
                if (temp.isTeachingAssistant()) {
                    sampleAb.addTutor(temp);
                }
            }
        }
        Module[] moduleArray = getSampleModules();
        List<Weekdays> weekdaysArrayList = Arrays.asList(Weekdays.Monday, Weekdays.Tuesday, Weekdays.Wednesday,
                Weekdays.Thursday, Weekdays.Friday, Weekdays.Saturday, Weekdays.Sunday);
        List<ClassType> classTypes = Arrays.asList(ClassType.Lab, ClassType.Lecture, ClassType.Tutorial,
                ClassType.Reflection);

        for (int i = 0; i < moduleArray.length; i++) {
            sampleAb.addModule(moduleArray[i]);
            moduleArray[i].addSchedule(new Schedule(moduleArray[i].getCode().fullCode,
                    new Venue("School"),
                    weekdaysArrayList.get(i % 7), "10:00", "12:00",
                    classTypes.get(i % 4), moduleArray[i].getCode().fullCode + ":" + classTypes.get(i % 4)));
        }


        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a ModuleCode set containing the list of strings given.
     */
    public static Set<ModuleCode> getModuleCodeSet(String... strings) {
        return Arrays.stream(strings)
                .map(ModuleCode::new)
                .collect(Collectors.toSet());
    }

    public static Set<String> getClassGroups(String ... strings) {
        return Arrays.stream(strings)
                .collect(Collectors.toSet());
    }

}
