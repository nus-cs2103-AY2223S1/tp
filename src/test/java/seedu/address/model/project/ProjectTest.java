package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.client.Client;


public class ProjectTest {

    private static final Project defaultProject = new Project(new Name("default"), new Repository("default/default"),
            new Deadline("2022-03-05"), new Client(new Name("default")), new ArrayList<>(), new ProjectId(1));

    private static final Project otherProject = new Project(new Name("test"), new Repository("test/test"),
            new Deadline("2022-03-05"), new Client(new Name("test")), new ArrayList<>(), new ProjectId(1));

    public static Project getDefaultProject() {
        return defaultProject;
    }

    public static Project getOtherProject() {
        return otherProject;
    }

    @Test
    public void constructor_nullParam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Project(null, new Repository("tom/tp"),
                new Deadline("2022-03-05"), new Client(new Name("default")), new ArrayList<>(), new ProjectId(1)));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Project(new Name(invalidName),
                new Repository("tom/tp"), new Deadline("2022-03-05"), new Client(new Name("default")),
                new ArrayList<>(), new ProjectId(1)));
    }

    @Test
    public void equals() {
        Project defaultProject = getDefaultProject();
        Project otherProject = getOtherProject();

        // same values -> returns true
        assertEquals(defaultProject, defaultProject);

        // same object -> returns true
        assertEquals(defaultProject, defaultProject);

        // different object -> returns false
        assertNotEquals(otherProject, defaultProject);

        // null -> returns false
        assertNotEquals(null, defaultProject);
    }

}
