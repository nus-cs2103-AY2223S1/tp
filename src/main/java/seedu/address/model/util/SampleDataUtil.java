package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.project.Budget;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffContact;
import seedu.address.model.staff.StaffDepartment;
import seedu.address.model.staff.StaffInsurance;
import seedu.address.model.staff.StaffName;
import seedu.address.model.staff.StaffTitle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Project[] getSampleProjects() {
        Project[] projectList = new Project[] {
            new Project(new ProjectName("CS2103T TP"), new Budget("200"), new Deadline("2022-06-01"),
                getTagSet("friends")),
            new Project(new ProjectName("CS2102"), new Budget("3"), new Deadline("2023-07-02"),
                getTagSet("colleagues", "friends")),
            new Project(new ProjectName("GESS1025"), new Budget("1100"), new Deadline("2024-08-03"),
                getTagSet("neighbours")),
            new Project(new ProjectName("CS2101"), new Budget("25"), new Deadline("2016-09-04"),
                getTagSet("family")),
            new Project(new ProjectName("Analysis"), new Budget("10000009"), new Deadline("2018-10-05"),
                getTagSet("classmates")),
            new Project(new ProjectName("I am tired"), new Budget("1250"), new Deadline("2024-11-06"),
                getTagSet("colleagues"))
        };
        Project tempProject = projectList[0];
        Set<Tag> tagList = new HashSet<>();
        tagList.add(new Tag("husband"));
        tagList.add(new Tag("friend"));
        Staff staff = new Staff(new StaffName("Andy Lee"), new StaffContact("11111111"),
                new StaffTitle("Senior Software Engineer"), new StaffDepartment("IT Department"),
                new StaffInsurance("true"), tagList);
        tempProject.getStaffList().add(staff);
        projectList[0] = tempProject;
        return projectList;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Project sampleProject : getSampleProjects()) {
            sampleAb.addProject(sampleProject);
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
