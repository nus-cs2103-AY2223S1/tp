package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_CLASS;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_TUTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.commands.EditCommand.EditTuitionClassDescriptor;
import seedu.address.logic.commands.EditCommand.EditTutorDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ListTuitionClassCommand;
import seedu.address.logic.commands.ListTutorCommand;
import seedu.address.logic.commands.NextOfKinCommand;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.nextofkin.NextOfKin;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.ClassUtil;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTuitionClassDescriptorBuilder;
import seedu.address.testutil.EditTutorDescriptorBuilder;
import seedu.address.testutil.NextOfKinBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();
    //listType only relevant for those that uses index, for now only edit matters
    private final Model.ListType listType = Model.ListType.STUDENT_LIST;

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(student), listType);
        assertEquals(AddCommand.of(student), command);

        Tutor tutor = new TutorBuilder().build();
        command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(tutor), listType);
        assertEquals(AddCommand.of(tutor), command);

        TuitionClass tuitionClass = new TuitionClassBuilder().build();
        command = (AddCommand) parser.parseCommand(ClassUtil.getAddCommand(tuitionClass), listType);
        assertEquals(AddCommand.of(tuitionClass), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, listType) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", listType) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), listType);
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_show() throws Exception {
        ShowCommand command = (ShowCommand) parser.parseCommand(
                ShowCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), listType);
        assertEquals(new ShowCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor studentDescriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand studentCommand = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " "
                        + PersonUtil.getEditStudentDescriptorDetails(studentDescriptor),
                listType);
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, studentDescriptor), studentCommand);

        Tutor tutor = new TutorBuilder().build();
        EditTutorDescriptor tutorDescriptor = new EditTutorDescriptorBuilder(tutor).build();
        EditCommand tutorCommand = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditTutorDescriptorDetails(tutorDescriptor),
                Model.ListType.TUTOR_LIST);
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, tutorDescriptor), tutorCommand);

        TuitionClass tuitionClass = new TuitionClassBuilder().build();
        EditTuitionClassDescriptor tuitionClassDescriptor = new EditTuitionClassDescriptorBuilder(tuitionClass).build();
        EditCommand tuitionClassCommand = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " "
                        + ClassUtil.getEditTuitionClassDescriptorDetails(tuitionClassDescriptor),
                Model.ListType.TUITIONCLASS_LIST);
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, tuitionClassDescriptor), tuitionClassCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, listType) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", listType) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "foo");
        keywords.put(PREFIX_ADDRESS, "bar");
        keywords.put(PREFIX_EMAIL, "baz");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + "n/" + keywords.get(PREFIX_NAME) + " a/" + keywords.get(PREFIX_ADDRESS)
                        + " e/" + keywords.get(PREFIX_EMAIL), listType);
        assertEquals(new FindCommand(keywords), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, listType) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listStudent() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + ENTITY_DESC_STUDENT, listType)
                instanceof ListStudentCommand);
        //assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD + " 3", listType)
        // instanceof ListStudentCommand);
    }

    @Test
    public void parseCommand_listTutor() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + ENTITY_DESC_TUTOR, listType)
                instanceof ListTutorCommand);
        //assertTrue(parser.parseCommand(ListTutorCommand.COMMAND_WORD + " 3", listType) instanceof ListTutorCommand);
    }

    @Test
    public void parseCommand_listTuitionClass() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + ENTITY_DESC_CLASS, listType)
                        instanceof ListTuitionClassCommand);
    }

    @Test
    public void parseCommand_assign() throws ParseException {
        assertTrue(parser.parseCommand(
                AssignCommand.COMMAND_WORD + " 1" + " n/p1math", listType) instanceof AssignCommand);
    }

    @Test
    public void parseCommand_unassign() throws ParseException {
        assertTrue(parser.parseCommand(
                UnassignCommand.COMMAND_WORD + " 1" + " n/p1math", listType) instanceof UnassignCommand);
    }

    @Test
    public void parseCommand_nok() throws Exception {
        //without any arguments
        assertTrue(parser.parseCommand(NextOfKinCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), listType)
                instanceof NextOfKinCommand);
        //with arguments
        NextOfKin nextOfKin = new NextOfKinBuilder().build();
        assertTrue(parser.parseCommand(PersonUtil.getNokCommand(nextOfKin), listType)
                instanceof NextOfKinCommand);
    }

    @Test
    public void parseCommand_sortCommand() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " " + SortCommand.SortBy.ALPHA, listType)
                instanceof SortCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand("", listType));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(
                ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand", listType));
    }
}
