package seedu.address.model.project;

import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.ClientTest;



public class ProjectTest {

    private static Project projectStub = new Project(new Name("default"), new Repository("default/default"),
            new Deadline("2022-03-05"), ClientTest.getClientStub(), new ArrayList<>(), new ProjectId(1));

    public static Project getProjectStub() {
        return projectStub;
    }

    @Test
    public void constructor_nullParam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Project(null, new Repository("tom/tp"),
                new Deadline("2022-03-05"), ClientTest.getClientStub(), new ArrayList<>(), new ProjectId(1)));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Project(new Name(invalidName),
                new Repository("tom/tp"), new Deadline("2022-03-05"), ClientTest.getClientStub(),
                new ArrayList<>(), new ProjectId(1)));
    }
}
