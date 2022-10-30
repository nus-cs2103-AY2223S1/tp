package seedu.masslinkers.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.model.ReadOnlyMassLinkers;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Name;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.model.student.Telegram;

/**
 * Contains utility methods for populating {@code MassLinkers} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Telegram("ayeoh"), new GitHub("alexyeow"),
                getInterestsSet("AI"), getModSet("CS2100")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Telegram("bernieyu"), new GitHub("beryu"),
                getInterestsSet("java", "AI"), getModSet("CS2100")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Telegram("charl0tte0liveir0"), new GitHub("charlotte123"),
                getInterestsSet("algo"), getModSet("CS2100")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Telegram("daveyli"), new GitHub("daveli"),
                getInterestsSet("algo"), getModSet("CS2100")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Telegram("irfanibrahim"), new GitHub("irfanibrahim"),
                getInterestsSet("algo"), getModSet("CS2100")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Telegram("roybala"), new GitHub("balaroy"),
                getInterestsSet("java"), getModSet("CS2100"))
        };
    }

    public static ReadOnlyMassLinkers getSampleMassLinkers() {
        MassLinkers sampleAb = new MassLinkers();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns an interest set containing the list of strings given.
     */
    public static Set<Interest> getInterestsSet(String... strings) {
        return Arrays.stream(strings)
                .map(Interest::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a mod set containing the list of strings given.
     */
    public static ObservableList<Mod> getModSet(String... strings) {
        return Arrays.stream(strings)
                .map(Mod::new)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

}
