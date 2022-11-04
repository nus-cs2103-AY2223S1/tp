package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static friday.logic.parser.CliSyntax.PREFIX_REMARK;
import static friday.testutil.Assert.assertThrows;
import static friday.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//import friday.model.Friday;
import org.junit.jupiter.api.Test;

import friday.logic.commands.AddCommand;
import friday.logic.commands.ClearCommand;
import friday.logic.commands.DeleteCommand;
import friday.logic.commands.EditCommand;
import friday.logic.commands.EditCommand.EditStudentDescriptor;
import friday.logic.commands.ExitCommand;
import friday.logic.commands.FindCommand;
import friday.logic.commands.GradeCommand;
import friday.logic.commands.GradeCommand.EditGradeDescriptor;
import friday.logic.commands.HelpCommand;
import friday.logic.commands.ListCommand;
import friday.logic.commands.RemarkCommand;
import friday.logic.parser.exceptions.ParseException;
import friday.model.Model;
import friday.model.ModelManager;
import friday.model.student.NameContainsKeywordsPredicate;
import friday.model.student.Remark;
import friday.model.student.Student;
import friday.testutil.EditGradeDescriptorBuilder;
import friday.testutil.EditStudentDescriptorBuilder;
import friday.testutil.StudentBuilder;
import friday.testutil.StudentUtil;

public class FridayParserTest {

    private final FridayParser parser = new FridayParser();
    private final Model model = new ModelManager();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student), model);
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, model) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", model) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased(), model);
        assertEquals(new DeleteCommand(INDEX_FIRST_STUDENT), command);
    }


    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor),
                model);
        assertEquals(new EditCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, model) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", model) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")),
                model);
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, model) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", model) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, model) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3", model) instanceof ListCommand);
    }

    // @@author HowSuen-reused
    // Reused from https://nus-cs2103-ay2223s1.github.io/tp/tutorials/AddRemark.html
    // with minor modifications
    @Test
    public void parseCommand_remark() throws Exception {
        final Remark remark = new Remark("Some remark.");
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + PREFIX_REMARK + remark.value, model);
        assertEquals(new RemarkCommand(INDEX_FIRST_STUDENT, remark), command);
    }

    @Test
    public void parseCommand_grade() throws Exception {
        Student student = new StudentBuilder().build();
        EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder(student.getGradesList()).build();
        GradeCommand command = (GradeCommand) parser.parseCommand(GradeCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditGradesDescriptorDetails(descriptor),
                model);
        assertEquals(new GradeCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", model));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand",
                model));
    }
}
