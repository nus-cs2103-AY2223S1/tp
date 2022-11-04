package seedu.hrpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_CONTACT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_DEPARTMENT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_LEAVE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_TITLE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_MARK;
import static seedu.hrpro.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.HrPro;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectNameContainsKeywordsPredicate;
import seedu.hrpro.model.task.Task;
import seedu.hrpro.model.task.TaskDescriptionContainsKeywordsPredicate;
import seedu.hrpro.testutil.EditProjectDescriptorBuilder;
import seedu.hrpro.testutil.EditStaffDescriptorBuilder;
import seedu.hrpro.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TASKDESCRIPTION_ALPHA = "Todo 2103 homework";
    public static final String VALID_TASKDESCRIPTION_BRAVO = "Submit 2100 assignment";
    public static final String VALID_TASKDEADLINE_ALPHA = "2022-01-01";
    public static final String VALID_TASKDEADLINE_BRAVO = "2023-02-21";
    public static final String VALID_TASKMARK_ALPHA = "false";
    public static final String VALID_TASKMARK_BRAVO = "true";

    public static final String TASKDESCRIPTION_DESC_ALPHA =
            " " + PREFIX_TASK_DESCRIPTION + VALID_TASKDESCRIPTION_ALPHA;
    public static final String TASKDESCRIPTION_DESC_BRAVO =
            " " + PREFIX_TASK_DESCRIPTION + VALID_TASKDESCRIPTION_BRAVO;
    public static final String TASKDEADLINE_DESC_ALPHA =
            " " + PREFIX_TASK_DEADLINE + VALID_TASKDEADLINE_ALPHA;
    public static final String TASKDEADLINE_DESC_BRAVO =
            " " + PREFIX_TASK_DEADLINE + VALID_TASKDEADLINE_BRAVO;
    public static final String TASKMARK_DESC_ALPHA =
            " " + PREFIX_TASK_MARK + VALID_TASKMARK_ALPHA;
    public static final String TASKMARK_DESC_BRAVO =
            " " + PREFIX_TASK_MARK + VALID_TASKMARK_BRAVO;

    public static final String INVALID_TASKDESCRIPTION_DESC =
            " " + PREFIX_TASK_DESCRIPTION + "Doing?????";
    public static final String INVALID_TASKDEADLINE_DESC =
            " " + PREFIX_TASK_DEADLINE + "yesterday";
    public static final String INVALID_TASKMARK_DESC =
            " " + PREFIX_TASK_MARK + "not sure";

    public static final String VALID_STAFFNAME_ANDY = "Andy Lee";
    public static final String VALID_STAFFNAME_JAY = "Jayden Yap";
    public static final String VALID_STAFFCONTACT_ANDY = "93228146";
    public static final String VALID_STAFFCONTACT_JAY = "80234172";
    public static final String VALID_STAFFTITLE_ANDY = "Senior Software Engineer";
    public static final String VALID_STAFFTITLE_JAY = "Software Engineer";
    public static final String VALID_STAFFDEPARTMENT_ANDY = "IT Department";
    public static final String VALID_STAFFDEPARTMENT_JAY = "idk Department";
    public static final String VALID_STAFFLEAVE_ANDY = "true";
    public static final String VALID_STAFFLEAVE_JAY = "false";

    public static final String STAFFNAME_DESC_ANDY = " " + PREFIX_STAFF_NAME + VALID_STAFFNAME_ANDY;
    public static final String STAFFNAME_DESC_JAY = " " + PREFIX_STAFF_NAME + VALID_STAFFNAME_JAY;
    public static final String STAFFCONTACT_DESC_ANDY = " " + PREFIX_STAFF_CONTACT + VALID_STAFFCONTACT_ANDY;
    public static final String STAFFCONTACT_DESC_JAY = " " + PREFIX_STAFF_CONTACT + VALID_STAFFCONTACT_JAY;
    public static final String STAFFTITLE_DESC_ANDY = " " + PREFIX_STAFF_TITLE + VALID_STAFFTITLE_ANDY;
    public static final String STAFFTITLE_DESC_JAY = " " + PREFIX_STAFF_TITLE + VALID_STAFFTITLE_JAY;
    public static final String STAFFDEPARTMENT_DESC_ANDY = " " + PREFIX_STAFF_DEPARTMENT + VALID_STAFFDEPARTMENT_ANDY;
    public static final String STAFFDEPARTMENT_DESC_JAY = " " + PREFIX_STAFF_DEPARTMENT + VALID_STAFFDEPARTMENT_JAY;
    public static final String STAFFLEAVE_DESC_ANDY = " " + PREFIX_STAFF_LEAVE + VALID_STAFFLEAVE_ANDY;
    public static final String STAFFLEAVE_DESC_JAY = " " + PREFIX_STAFF_LEAVE + VALID_STAFFLEAVE_JAY;

    public static final String INVALID_STAFFNAME_DESC = " " + PREFIX_STAFF_NAME + "S@muel";
    public static final String INVALID_STAFFCONTACT_DESC = " " + PREFIX_STAFF_CONTACT + "9090 A32!";
    public static final String INVALID_STAFFDEPARTMENT_DESC = " " + PREFIX_STAFF_DEPARTMENT + "GREAT department!!";
    public static final String INVALID_STAFFTITLE_DESC = " " + PREFIX_STAFF_TITLE + "THE Best :3";
    public static final String INVALID_STAFFLEAVE_DESC = " " + PREFIX_STAFF_LEAVE + "T";

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_BUDGET_AMY = "11111111";
    public static final String VALID_BUDGET_BOB = "22222222";
    public static final String VALID_DEADLINE_AMY = "2022-01-01";
    public static final String VALID_DEADLINE_BOB = "2022-12-28";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_PROJECT_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_PROJECT_NAME + VALID_NAME_BOB;
    public static final String BUDGET_DESC_AMY = " " + PREFIX_BUDGET + VALID_BUDGET_AMY;
    public static final String BUDGET_DESC_BOB = " " + PREFIX_BUDGET + VALID_BUDGET_BOB;
    public static final String DEADLINE_DESC_AMY = " " + PREFIX_DEADLINE + VALID_DEADLINE_AMY;
    public static final String DEADLINE_DESC_BOB = " " + PREFIX_DEADLINE + VALID_DEADLINE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_PROJECT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_BUDGET_DESC = " " + PREFIX_BUDGET + "911a"; // 'a' not allowed in budget
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "aaaa"; // wrong date format
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditProjectDescriptor DESC_AMY;
    public static final EditCommand.EditProjectDescriptor DESC_BOB;
    public static final EditStaffCommand.EditStaffDescriptor STAFF_DESC_ANDY;

    public static final EditStaffCommand.EditStaffDescriptor STAFF_DESC_JAY;
    public static final EditTaskCommand.EditTaskDescriptor DESC_ALPHA;
    public static final EditTaskCommand.EditTaskDescriptor DESC_BRAVO;
    static {
        DESC_AMY = new EditProjectDescriptorBuilder().withName(VALID_NAME_AMY)
                .withBudget(VALID_BUDGET_AMY).withDeadline(VALID_DEADLINE_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditProjectDescriptorBuilder().withName(VALID_NAME_BOB)
                .withBudget(VALID_BUDGET_BOB).withDeadline(VALID_DEADLINE_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        STAFF_DESC_ANDY = new EditStaffDescriptorBuilder().withName(VALID_STAFFNAME_ANDY)
                .withContact(VALID_STAFFCONTACT_ANDY).withDepartment(VALID_STAFFDEPARTMENT_ANDY)
                .withTitle(VALID_STAFFTITLE_ANDY).withLeave(VALID_STAFFLEAVE_ANDY)
                .withTags(VALID_TAG_FRIEND).build();
        STAFF_DESC_JAY = new EditStaffDescriptorBuilder().withName(VALID_STAFFNAME_JAY)
                .withContact(VALID_STAFFCONTACT_JAY).withDepartment(VALID_STAFFDEPARTMENT_JAY)
                .withTitle(VALID_STAFFTITLE_JAY).withLeave(VALID_STAFFLEAVE_JAY)
                .withTags(VALID_TAG_HUSBAND).build();
        DESC_ALPHA = new EditTaskDescriptorBuilder()
                .withDescription(VALID_TASKDESCRIPTION_ALPHA)
                .withDeadline(VALID_TASKDEADLINE_ALPHA).build();
        DESC_BRAVO = new EditTaskDescriptorBuilder()
                .withDescription(VALID_TASKDESCRIPTION_BRAVO)
                .withDeadline(VALID_TASKDEADLINE_BRAVO).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            System.out.println(ce.toString());
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the hr pro, filtered project list and selected project in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        HrPro expectedHrPro = new HrPro(actualModel.getHrPro());
        List<Project> expectedFilteredList = new ArrayList<>(actualModel.getFilteredProjectList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedHrPro, actualModel.getHrPro());
        assertEquals(expectedFilteredList, actualModel.getFilteredProjectList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the project at the given {@code targetIndex} in the
     * {@code model}'s hr pro.
     */
    public static void showProjectAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProjectList().size());

        Project project = model.getFilteredProjectList().get(targetIndex.getZeroBased());
        final String[] splitName = project.getProjectName().fullName.split("\\s+");
        model.updateFilteredProjectList(new ProjectNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredProjectList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s hr pro.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitDescription = task.getTaskDescription().toString().split("\\s+");
        model.updateFilteredTaskList(new TaskDescriptionContainsKeywordsPredicate(Arrays.asList(splitDescription[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
