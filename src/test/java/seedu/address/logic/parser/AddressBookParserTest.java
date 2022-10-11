package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddUserCommand;
import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.DeleteUserByNameCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditUserByNameCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindUserCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.FullNamePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addgroup() throws Exception {
        String groupname = "Group";
        GroupName groupName = new GroupName(groupname);
        Group group = new Group(groupName, new HashSet<>());
        AddGroupCommand command = (AddGroupCommand) parser.parseCommand(
                AddGroupCommand.COMMAND_WORD + " " + PREFIX_GROUP + groupname);
        assertEquals(new AddGroupCommand(group), command);
    }

    @Test
    public void parseCommand_adduser() throws Exception {
        Person person = new PersonBuilder().build();
        AddUserCommand command = (AddUserCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddUserCommand(person), command);
    }

    @Test
    public void parseCommand_assigntask() throws Exception {
        String name = "Alex";
        String group = "Group1";
        String task = "assignment 0";
        AssignTaskCommand command = (AssignTaskCommand) parser.parseCommand(
                AssignTaskCommand.COMMAND_WORD + " " + name + " "
                        + PREFIX_GROUP + group + " "
                        + PREFIX_TASK + task);
        assertEquals(new AssignTaskCommand(new Name(name), group, new Assignment(task)), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deletegroup() throws Exception {
        String groupname = "Group";
        GroupName groupName = new GroupName(groupname);
        Group group = new Group(groupName, new HashSet<>());
        DeleteGroupCommand command = (DeleteGroupCommand) parser.parseCommand(
                DeleteGroupCommand.COMMAND_WORD + " " + PREFIX_GROUP + groupname);
        assertEquals(new DeleteGroupCommand(group), command);
    }

    @Test
    public void parseCommand_deletetask() throws Exception {
        String name = "Alex";
        String group = "Group1";
        String task = "assignment 0";
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + name + " "
                        + PREFIX_GROUP + group + " "
                        + PREFIX_TASK + task);
        assertEquals(new DeleteTaskCommand(new Name(name), group, new Assignment(task)), command);
    }

    @Test
    public void parseCommand_deleteuserbyname() throws Exception {
        String name = "Alex";
        DeleteUserByNameCommand command = (DeleteUserByNameCommand) parser.parseCommand(
                DeleteUserByNameCommand.COMMAND_WORD + " " + name);
        FullNamePredicate pred = new FullNamePredicate(name);
        assertEquals(new DeleteUserByNameCommand(pred), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editByName() throws Exception {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditUserByNameCommand command = (EditUserByNameCommand) parser.parseCommand(
                EditUserByNameCommand.COMMAND_WORD + " "
                        + "Bob" + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditUserByNameCommand(new FullNamePredicate("Bob"), descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindUserCommand command = (FindUserCommand) parser.parseCommand(
                FindUserCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindUserCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
