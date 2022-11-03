package seedu.taassist.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.commons.core.Messages.MESSAGE_EMPTY_COMMAND;
import static seedu.taassist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TestUtil.joinWithSpace;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.AddCommand;
import seedu.taassist.logic.commands.AddcCommand;
import seedu.taassist.logic.commands.AddsCommand;
import seedu.taassist.logic.commands.AssignCommand;
import seedu.taassist.logic.commands.ClearCommand;
import seedu.taassist.logic.commands.DeleteCommand;
import seedu.taassist.logic.commands.DeletecCommand;
import seedu.taassist.logic.commands.DeletesCommand;
import seedu.taassist.logic.commands.EditCommand;
import seedu.taassist.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.taassist.logic.commands.ExitCommand;
import seedu.taassist.logic.commands.FindCommand;
import seedu.taassist.logic.commands.FocusCommand;
import seedu.taassist.logic.commands.GradeCommand;
import seedu.taassist.logic.commands.HelpCommand;
import seedu.taassist.logic.commands.ListCommand;
import seedu.taassist.logic.commands.ListcCommand;
import seedu.taassist.logic.commands.UnassignCommand;
import seedu.taassist.logic.commands.UnfocusCommand;
import seedu.taassist.logic.commands.ViewCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.predicate.NameContainsKeywordsPredicate;
import seedu.taassist.testutil.EditStudentDescriptorBuilder;
import seedu.taassist.testutil.ModuleClassBuilder;
import seedu.taassist.testutil.SessionBuilder;
import seedu.taassist.testutil.StudentBuilder;
import seedu.taassist.testutil.StudentUtil;

public class TaAssistParserTest {

    private final TaAssistParser parser = new TaAssistParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_addc() throws Exception {
        ModuleClass moduleClass = new ModuleClassBuilder().build();
        String commandString = joinWithSpace(AddcCommand.COMMAND_WORD,
                PREFIX_MODULE_CLASS + moduleClass.getClassName());
        AddcCommand command = (AddcCommand) parser.parseCommand(commandString);
        assertEquals(new AddcCommand(new HashSet<>(Arrays.asList(moduleClass))), command);
    }

    @Test
    public void parseCommand_deletec() throws Exception {
        ModuleClass moduleClass = new ModuleClassBuilder().build();
        String commandString = joinWithSpace(DeletecCommand.COMMAND_WORD,
                PREFIX_MODULE_CLASS + moduleClass.getClassName());
        DeletecCommand command = (DeletecCommand) parser.parseCommand(commandString);
        assertEquals(new DeletecCommand(new HashSet<>(Arrays.asList(moduleClass))), command);
    }

    @Test
    public void parseCommand_focus() throws Exception {
        ModuleClass moduleClass = new ModuleClassBuilder().build();
        String commandString = joinWithSpace(FocusCommand.COMMAND_WORD,
                PREFIX_MODULE_CLASS + moduleClass.getClassName());
        FocusCommand command = (FocusCommand) parser.parseCommand(commandString);
        assertEquals(new FocusCommand(moduleClass), command);
    }

    @Test
    public void parseCommand_unfocus() throws Exception {
        assertTrue(parser.parseCommand(UnfocusCommand.COMMAND_WORD) instanceof UnfocusCommand);
        assertTrue(parser.parseCommand(UnfocusCommand.COMMAND_WORD + " 3") instanceof UnfocusCommand);
    }

    @Test
    public void parseCommand_assign() throws Exception {
        ModuleClass moduleClass = new ModuleClassBuilder().build();
        List<Index> indices = new ArrayList<>(List.of(INDEX_FIRST_STUDENT, INDEX_THIRD_STUDENT));
        String commandString = joinWithSpace(AssignCommand.COMMAND_WORD, INDEX_FIRST_STUDENT.toString(),
                INDEX_THIRD_STUDENT.toString(), PREFIX_MODULE_CLASS + moduleClass.getClassName());
        AssignCommand command = (AssignCommand) parser.parseCommand(commandString);
        assertEquals(new AssignCommand(indices, moduleClass), command);
    }

    @Test
    public void parseCommand_unassign() throws Exception {
        ModuleClass moduleClass = new ModuleClassBuilder().build();
        List<Index> indices = new ArrayList<>(List.of(INDEX_FIRST_STUDENT, INDEX_THIRD_STUDENT));
        String commandString = joinWithSpace(UnassignCommand.COMMAND_WORD, INDEX_FIRST_STUDENT.toString(),
                INDEX_THIRD_STUDENT.toString(), PREFIX_MODULE_CLASS + moduleClass.getClassName());
        UnassignCommand command = (UnassignCommand) parser.parseCommand(commandString);
        assertEquals(new UnassignCommand(indices, moduleClass), command);
    }

    @Test
    public void parseCommand_listc() throws Exception {
        assertTrue(parser.parseCommand(ListcCommand.COMMAND_WORD) instanceof ListcCommand);
        assertTrue(parser.parseCommand(joinWithSpace(ListcCommand.COMMAND_WORD, "3")) instanceof ListcCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(joinWithSpace(ClearCommand.COMMAND_WORD, "3")) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        String commandString = joinWithSpace(EditCommand.COMMAND_WORD, INDEX_FIRST_STUDENT.toString(),
                StudentUtil.getEditStudentDescriptorDetails(descriptor));
        EditCommand command = (EditCommand) parser.parseCommand(commandString);
        assertEquals(new EditCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_session() throws Exception {
        Session session = new SessionBuilder().build();
        String commandString = joinWithSpace(AddsCommand.COMMAND_WORD,
                PREFIX_SESSION + session.getSessionName());
        AddsCommand command = (AddsCommand) parser.parseCommand(commandString);
        assertEquals(new AddsCommand(new HashSet<>(List.of(session))), command);
    }

    @Test
    public void parseCommand_deletes() throws Exception {
        Session session = new SessionBuilder().build();
        String commandString = joinWithSpace(DeletesCommand.COMMAND_WORD,
                PREFIX_SESSION + session.getSessionName());
        DeletesCommand command = (DeletesCommand) parser.parseCommand(commandString);
        assertEquals(new DeletesCommand(new HashSet<>(Arrays.asList(session))), command);
    }

    @Test
    public void parseCommand_grade() throws Exception {
        Session session = new SessionBuilder().build();
        Index index = INDEX_FIRST_STUDENT;
        Double value = 1.0;
        String commandString = joinWithSpace(GradeCommand.COMMAND_WORD, index.toString(),
                PREFIX_GRADE + value.toString(), PREFIX_SESSION + session.getSessionName());
        GradeCommand command = (GradeCommand) parser.parseCommand(commandString);
        assertEquals(new GradeCommand(new ArrayList<>(List.of(index)), session, value), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        Index index = INDEX_FIRST_STUDENT;
        String commandString = joinWithSpace(ViewCommand.COMMAND_WORD, index.toString());
        ViewCommand command = (ViewCommand) parser.parseCommand(commandString);
        assertEquals(new ViewCommand(index), command);
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
        assertThrows(ParseException.class, MESSAGE_EMPTY_COMMAND, () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
