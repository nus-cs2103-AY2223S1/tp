package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.*;
import seedu.address.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.ProjectNameContainsKeywordsPredicate;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffName;
import seedu.address.testutil.*;

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
        assertEquals(new AddStaffCommand(staff, new ProjectName("CS2103T TP")), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PROJECT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PROJECT), command);
    }

    @Test
    public void parseCommand_deleteStaff() throws Exception {
        Staff staff = new StaffBuilder().build();
        StaffName staffName = staff.getStaffName();
        ProjectName projectName = new ProjectName("CS2103T TP");
        DeleteStaffCommand command = (DeleteStaffCommand) parser.parseCommand(
                DeleteStaffCommand.COMMAND_WORD + " pn/" + projectName.fullName +" sn/" + staffName.staffName
        );
        assertEquals(new DeleteStaffCommand(staffName, projectName), command);
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
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new ProjectNameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
