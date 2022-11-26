package seedu.workbook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.workbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.workbook.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.workbook.testutil.Assert.assertThrows;
import static seedu.workbook.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.workbook.logic.commands.AddCommand;
import seedu.workbook.logic.commands.ClearCommand;
import seedu.workbook.logic.commands.DeleteCommand;
import seedu.workbook.logic.commands.EditCommand;
import seedu.workbook.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.workbook.logic.commands.ExitCommand;
import seedu.workbook.logic.commands.FindCommand;
import seedu.workbook.logic.commands.HelpCommand;
import seedu.workbook.logic.commands.ListCommand;
import seedu.workbook.logic.commands.RedoCommand;
import seedu.workbook.logic.commands.UndoCommand;
import seedu.workbook.logic.parser.exceptions.ParseException;
import seedu.workbook.model.internship.CompanyContainsKeywordsPredicate;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.testutil.EditInternshipDescriptorBuilder;
import seedu.workbook.testutil.InternshipBuilder;
import seedu.workbook.testutil.InternshipUtil;

public class WorkBookParserTest {

    private final WorkBookParser parser = new WorkBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Internship internship = new InternshipBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(InternshipUtil.getAddCommand(internship));
        assertEquals(new AddCommand(internship), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_INTERNSHIP), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Internship internship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(internship).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_INTERNSHIP.getOneBased() + " "
                + InternshipUtil.getEditInternshipDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_INTERNSHIP, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("Amy", "Bee");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + COMPANY_DESC_AMY);
        assertEquals(new FindCommand(new CompanyContainsKeywordsPredicate(keywords)), command);
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

    // CHECKSTYLE.OFF: SeparatorWrap
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE),
                () -> parser.parseCommand(""));
    }
    // CHECKSTYLE.ON: SeparatorWrap

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
