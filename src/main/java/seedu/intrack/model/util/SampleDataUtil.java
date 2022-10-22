package seedu.intrack.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.intrack.model.InTrack;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Phone;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Remark;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.internship.Task;
import seedu.intrack.model.internship.Website;
import seedu.intrack.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InTrack} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new Name("Microsoft"), new Position("Software Engineer"), new Status("Progress"),
                new Phone("11111111"), new Email("hr@microsoft.com"),
                new Website("https://careers.microsoft.com/us/en"),
                getTaskList("HR Interview /at 10-10-2023 12:00"), getTagSet("Urgent"), EMPTY_REMARK),
            new Internship(new Name("Apple"), new Position("Frontend Developer"), new Status("Progress"),
                new Phone("22222222"), new Email("hr@apple.com"), new Website("https://www.apple.com/careers/sg/"),
                getTaskList("Technical Interview /at 11-11-2023 10:00"), getTagSet("Remote"),
                EMPTY_REMARK),
            new Internship(new Name("Tesla"), new Position("Machine Learning Expert"), new Status("Progress"),
                new Phone("63456789"), new Email("hr@tesla.com"), new Website("https://www.tesla.com/en_sg/careers"),
                getTaskList("Online Assessment /at 09-10-2023 12:00"), getTagSet("Postponed"),
                EMPTY_REMARK),
            new Internship(new Name("Alphabet"), new Position("Full Stack Developer"), new Status("Progress"),
                new Phone("64567890"), new Email("hr@alphabet.com"),
                new Website("https://www.alphabet.com/en-ww/company-careers"),
                getTaskList("Coding Interview /at 10-12-2022 09:00"), getTagSet("Urgent"),
                EMPTY_REMARK),
            new Internship(new Name("Nvidia"), new Position("Data Analyst"), new Status("Progress"),
                new Phone("65678901"), new Email("hr@nvidia.com"),
                new Website("https://www.nvidia.com/en-us/about-nvidia/careers/"),
                getTaskList("Coding Interview /at 10-12-2022 09:00"), getTagSet("Remote"),
                EMPTY_REMARK),
            new Internship(new Name("Amazon"), new Position("Backend Developer"), new Status("Progress"),
                new Phone("66789012"), new Email("hr@amazon.com"), new Website("https://www.amazon.jobs/en"),
                getTaskList("Behavioural Interview /at 10-10-2023 08:00"), getTagSet("Postponed"),
                EMPTY_REMARK)
        };
    }

    public static ReadOnlyInTrack getSampleInTrack() {
        InTrack sampleIt = new InTrack();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleIt.addInternship(sampleInternship);
        }
        return sampleIt;
    }

    /**
     * Returns a task list containing the list of strings given.
     */
    public static List<Task> getTaskList(String... strings) {
        return Arrays.stream(strings)
                .map(s -> {
                    String[] splitTask = s.split(" /at ");
                    String taskName = splitTask[0];
                    String taskTime = splitTask[1];
                    return new Task(taskName, taskTime);
                })
                .collect(Collectors.toList());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
