package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignmentdetails.AssignmentDetails;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Telegram("@alexyeoh"), new ModuleCode("CS1101S"),
                getTagSet("friends", "colleagues")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Telegram("@bernice"), new ModuleCode("LSM1301"),
                getTagSet()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Telegram("@charlotte"), new ModuleCode("MA1521"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Telegram("@david"), new ModuleCode("CG1111A"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Telegram("@irfan"), new ModuleCode("CS3243"),
                getTagSet("classmates", "neighbours")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Telegram("@balak"), new ModuleCode("CS2040S"),
                getTagSet("colleagues", "childhoodFriend", "owesMeMoney"))
        };
    }

    public static Module[] getSampleModule() {
        ModuleCode cs2103t = new ModuleCode("CS2103T");
        cs2103t.setModuleTitle("Software Engineering");
        ModuleCode cs2040s = new ModuleCode("CS2040S");
        cs2040s.setModuleTitle("Data Structures and Algorithms");
        ModuleCode cs2030s = new ModuleCode("CS2030S");
        cs2030s.setModuleTitle("Programming Methodology II");
        ModuleCode cs2100 = new ModuleCode("CS2100");
        cs2100.setModuleTitle("Computer Organisation");
        ModuleCode cs2101 = new ModuleCode("CS2101");
        cs2101.setModuleTitle("Effective Communication for Computing Professionals");
        return new Module[]{
            new Module(cs2103t, new LectureDetails("Monday 2pm"), new TutorialDetails("Friday 7pm"),
                new ZoomLink("https://nus-sg.zoom.us/j/tutorialCS2103T"),
                new ZoomLink("https://nus-sg.zoom.us/j/lectureCS2103T"),
                getAssignmentDetailsSet("lectureQuiz", "weeklyTutorial")),
            new Module(cs2040s, new LectureDetails("Wednesday 3pm"), new TutorialDetails("Thursday 6pm"),
                new ZoomLink("https://nus-sg.zoom.us/j/tutorialCS2040S"),
                new ZoomLink("https://nus-sg.zoom.us/j/lectureCS2040S"),
                getAssignmentDetailsSet("ProblemSets", "Tutorial", "inLectureQuiz")),
            new Module(cs2030s, new LectureDetails("Saturday, 10am"), new TutorialDetails("Wednesday 1pm"),
                new ZoomLink("https://nus-sg.zoom.us/j/tutorialCS030S"),
                new ZoomLink("https://nus-sg.zoom.us/j/lectureCS2030S"),
                getAssignmentDetailsSet("Lab9")),
            new Module(cs2100, new LectureDetails("Monday 4pm"), new TutorialDetails("Thursday 2pm"),
                new ZoomLink("https://nus-sg.zoom.us/j/tutorialCS2100"),
                new ZoomLink("https://nus-sg.zoom.us/j/lectureCS2100"),
                getAssignmentDetailsSet()),
            new Module(cs2101, new LectureDetails("Friday 10am"), new TutorialDetails("Wednesday 10am"),
                new ZoomLink("https://nus-sg.zoom.us/j/tutorialCS2101"),
                new ZoomLink("https://nus-sg.zoom.us/j/lectureCS2101"),
                getAssignmentDetailsSet("OP2", "UGDGsubmission"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Module sampleModule : getSampleModule()) {
            sampleAb.addModule(sampleModule);
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<AssignmentDetails> getAssignmentDetailsSet(String... strings) {
        return Arrays.stream(strings)
            .map(AssignmentDetails::new)
            .collect(Collectors.toSet());
    }
}
