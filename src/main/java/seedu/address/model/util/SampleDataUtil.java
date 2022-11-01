package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.deadline.Deadline;
import seedu.address.model.project.Budget;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffContact;
import seedu.address.model.staff.StaffDepartment;
import seedu.address.model.staff.StaffLeave;
import seedu.address.model.staff.StaffName;
import seedu.address.model.staff.StaffTitle;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskMark;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Project[] getSampleProjects() {
        Project[] projectList = new Project[] {
            new Project(new ProjectName("Artemis"), new Budget("20000"), new Deadline("2022-10-28"),
                getTagSet("Software")),
            new Project(new ProjectName("Apollo"), new Budget("3"), new Deadline("2023-07-02"),
                getTagSet("Computing", "Pending")),
            new Project(new ProjectName("Gemini"), new Budget("1100"), new Deadline("2024-08-03"),
                getTagSet("Expansion")),
            new Project(new ProjectName("Microsoft 365"), new Budget("25000"), new Deadline("2016-09-04"),
                getTagSet("Important")),
            new Project(new ProjectName("Merger with Tiktok"), new Budget("10000000"), new Deadline("2018-10-05"),
                getTagSet("Important")),
            new Project(new ProjectName("Trends Visualizer"), new Budget("3000"), new Deadline("2024-11-06"),
                getTagSet("Finance"))
        };
        Project tempProject = projectList[0];
        Set<Tag> tagList = new HashSet<>();
        tagList.add(new Tag("Vacation"));
        tagList.add(new Tag("Busy"));
        Staff staff = new Staff(new StaffName("Andy Lee"), new StaffContact("89012375"),
                new StaffTitle("Senior Software Engineer"), new StaffDepartment("IT Department"),
                new StaffLeave("true"), tagList);
        tempProject.getStaffList().add(staff);
        projectList[0] = tempProject;
        return projectList;
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Deadline("2022-10-28"), new TaskDescription("Downloaded jar file"),
                    new TaskMark("true")),
            new Task(new Deadline("2022-08-30"), new TaskDescription("Read user guide"),
                    new TaskMark("false")),
            new Task(new Deadline("2022-06-01"), new TaskDescription("Try out commands"),
                    new TaskMark("false")),
            new Task(new Deadline("2022-07-02"), new TaskDescription("Make changes to create save file"),
                    new TaskMark("false"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Project sampleProject : getSampleProjects()) {
            sampleAb.addProject(sampleProject);
        }

        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
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

}
