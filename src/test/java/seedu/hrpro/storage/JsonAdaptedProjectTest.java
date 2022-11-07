package seedu.hrpro.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.hrpro.storage.JsonAdaptedProject.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalProjects.BANANA;
import static seedu.hrpro.testutil.TypicalStaff.STAFF_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.exceptions.IllegalValueException;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.project.Budget;
import seedu.hrpro.model.project.ProjectName;

public class JsonAdaptedProjectTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_BUDGET = "+651234";
    private static final String INVALID_DEADLINE = "2022-05";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_STAFF_LEAVE = "Invalid";

    private static final String VALID_NAME = BANANA.getProjectName().toString();
    private static final String VALID_BUDGET = BANANA.getBudget().toString();
    private static final String VALID_DEADLINE = BANANA.getDeadline().toString();
    private static final String VALID_STAFF_NAME = STAFF_1.getStaffName().toString();
    private static final String VALID_STAFF_CONTACT = STAFF_1.getStaffContact().toString();
    private static final String VALID_STAFF_TITLE = STAFF_1.getStaffTitle().toString();
    private static final String VALID_STAFF_DEPARTMENT = STAFF_1.getStaffDepartment().toString();
    private static final String VALID_STAFF_LEAVE = STAFF_1.getStaffLeave().toString();
    private static final List<JsonAdaptedStaff> VALID_STAFF = Arrays.asList(new JsonAdaptedStaff(STAFF_1));
    private static final List<JsonAdaptedTag> VALID_TAGS = STAFF_1.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validProjectDetails_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(BANANA);
        assertEquals(BANANA, project.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(INVALID_NAME, VALID_BUDGET, VALID_DEADLINE, VALID_TAGS, VALID_STAFF);
        String expectedMessage = ProjectName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(null, VALID_BUDGET,
                VALID_DEADLINE, VALID_TAGS, VALID_STAFF);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ProjectName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidBudget_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_NAME, INVALID_BUDGET, VALID_DEADLINE, VALID_TAGS, VALID_STAFF);
        String expectedMessage = Budget.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullBudget_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_NAME, null, VALID_DEADLINE, VALID_TAGS, VALID_STAFF);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Budget.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_NAME, VALID_BUDGET, INVALID_DEADLINE, VALID_TAGS, VALID_STAFF);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_NAME, VALID_BUDGET, null, VALID_TAGS, VALID_STAFF);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidStaffList_throwsIllegalValueException() {
        List<JsonAdaptedStaff> invalidStaff = new ArrayList<>(VALID_STAFF);
        invalidStaff.add(new JsonAdaptedStaff(VALID_STAFF_NAME, VALID_STAFF_CONTACT,
                VALID_STAFF_TITLE, VALID_STAFF_DEPARTMENT, INVALID_STAFF_LEAVE, null));
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_NAME, VALID_BUDGET, VALID_DEADLINE, VALID_TAGS, invalidStaff);
        assertThrows(IllegalValueException.class, project::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_NAME, VALID_BUDGET, VALID_DEADLINE, invalidTags, VALID_STAFF);
        assertThrows(IllegalValueException.class, project::toModelType);
    }

}
