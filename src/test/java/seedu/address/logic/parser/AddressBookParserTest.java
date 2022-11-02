package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STAFF;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteStaffCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.address.logic.commands.EditStaffCommand;
import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindStaffCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCompleteCommand;
import seedu.address.logic.commands.SortTaskCommand;
import seedu.address.logic.commands.UnmarkTaskCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.ProjectNameContainsKeywordsPredicate;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffNameContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescriptionContainsKeywordsPredicate;
import seedu.address.testutil.EditProjectDescriptorBuilder;
import seedu.address.testutil.EditStaffDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.ProjectUtil;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StaffUtil;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Project project = new ProjectBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ProjectUtil.getAddCommand(project));
        assertEquals(new AddCommand(project), command);
    }

    @Test
    public void parseCommand_addStaff() throws Exception {
        Staff staff = new StaffBuilder().build();
        AddStaffCommand command = (AddStaffCommand) parser.parseCommand(StaffUtil.getAddCommand(staff));
        assertEquals(new AddStaffCommand(staff, INDEX_FIRST_PROJECT), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(TaskUtil.getAddCommand(task));
        assertEquals(new AddTaskCommand(task), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " 3") instanceof SortCommand);
    }

    @Test
    public void parseCommand_sortTask() throws Exception {
        assertTrue(parser.parseCommand(SortTaskCommand.COMMAND_WORD) instanceof SortTaskCommand);
        assertTrue(parser.parseCommand(SortTaskCommand.COMMAND_WORD + " 3") instanceof SortTaskCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PROJECT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PROJECT), command);
    }

    @Test
    public void parseCommand_deleteStaff() throws Exception {
        ProjectName projectName = new ProjectName("CS2103T TP");
        DeleteStaffCommand command = (DeleteStaffCommand) parser.parseCommand(
                DeleteStaffCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_STAFF.getOneBased() + " pn/" + projectName.fullName
        );
        Index index = Index.fromZeroBased(0);
        assertEquals(new DeleteStaffCommand(index, projectName), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                  DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased()
        );
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Project project = new ProjectBuilder().build();
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(project).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PROJECT.getOneBased() + " " + ProjectUtil.getEditProjectDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PROJECT, descriptor), command);
    }

    @Test
    public void parseCommand_editTask() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(EditTaskCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditTaskCommand(INDEX_FIRST_TASK, descriptor), command);
    }

    @Test
    public void parseCommand_editStaffTask() throws Exception {
        Project dummyProject = new ProjectBuilder().withName("dummy").build();
        dummyProject.getStaffList().add(new StaffBuilder().withStaffName("Andy").build());
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withName("Andy Lau")
                .build();

        EditStaffCommand command = (EditStaffCommand) parser.parseCommand(
                EditStaffCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " "
                + PREFIX_PROJECT_NAME + dummyProject.getProjectName()
                + " " + StaffUtil.getEditStaffDescriptorDetails(descriptor));
        assertEquals(new EditStaffCommand(dummyProject.getProjectName(), INDEX_FIRST_TASK, descriptor),
                command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_sortComplete() throws Exception {
        assertTrue(parser.parseCommand(SortCompleteCommand.COMMAND_WORD) instanceof SortCompleteCommand);
        assertTrue(parser.parseCommand(
                SortCompleteCommand.COMMAND_WORD + " RANDOM INPUT 123!!") instanceof SortCompleteCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new ProjectNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findStaff() throws Exception {
        List<String> keywords = Arrays.asList("Andy", "Lau", "de", "hua");
        FindStaffCommand command = (FindStaffCommand) parser.parseCommand(
                FindStaffCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStaffCommand(new StaffNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findTask() throws Exception {
        List<String> keywords = Arrays.asList("too", "lar", "laz");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(FindTaskCommand.COMMAND_WORD + " "
                + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTaskCommand(new TaskDescriptionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_markTask() throws Exception {
        MarkTaskCommand command = (MarkTaskCommand) parser.parseCommand(
                MarkTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new MarkTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_unmarkTask() throws Exception {
        UnmarkTaskCommand command = (UnmarkTaskCommand) parser.parseCommand(
                UnmarkTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new UnmarkTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_PROJECT.getOneBased());
        assertEquals(new ViewCommand(INDEX_FIRST_PROJECT), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
