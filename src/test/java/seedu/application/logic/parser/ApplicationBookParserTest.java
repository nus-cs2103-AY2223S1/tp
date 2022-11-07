package seedu.application.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.application.logic.parser.CliSyntax.PREFIX_REVERSE;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.AddCommand;
import seedu.application.logic.commands.ArchiveCommand;
import seedu.application.logic.commands.ClearCommand;
import seedu.application.logic.commands.DeleteCommand;
import seedu.application.logic.commands.EditCommand;
import seedu.application.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.application.logic.commands.ExitCommand;
import seedu.application.logic.commands.FindCommand;
import seedu.application.logic.commands.HelpCommand;
import seedu.application.logic.commands.ListArchiveCommand;
import seedu.application.logic.commands.ListCommand;
import seedu.application.logic.commands.RedoCommand;
import seedu.application.logic.commands.RetrieveCommand;
import seedu.application.logic.commands.SortByPositionCommand;
import seedu.application.logic.commands.SortCommand;
import seedu.application.logic.commands.UndoCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.application.Application;
import seedu.application.model.application.CompanyContainsKeywordsPredicate;
import seedu.application.model.application.PositionContainsKeywordsPredicate;
import seedu.application.testutil.ApplicationBuilder;
import seedu.application.testutil.ApplicationUtil;
import seedu.application.testutil.EditApplicationDescriptorBuilder;

public class ApplicationBookParserTest {

    private final ApplicationBookParser parser = new ApplicationBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Application application = new ApplicationBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ApplicationUtil.getAddCommand(application));
        assertEquals(new AddCommand(application), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Application application = new ApplicationBuilder().build();
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(application).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICATION.getOneBased() + " "
                + ApplicationUtil.getEditApplicationDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_APPLICATION, descriptor), command);
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
                FindCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindCommand(new CompanyContainsKeywordsPredicate(keywords),
                new PositionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_ORDER + "position " + PREFIX_REVERSE);
        assertEquals(new SortByPositionCommand(true), command);
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
    public void parseCommand_listArchive() throws Exception {
        assertTrue(parser.parseCommand(ListArchiveCommand.COMMAND_WORD) instanceof ListArchiveCommand);
        assertTrue(parser.parseCommand(ListArchiveCommand.COMMAND_WORD + " 3") instanceof ListArchiveCommand);
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
    public void parseCommand_archive() throws Exception {
        ArchiveCommand command = (ArchiveCommand) parser.parseCommand(
                ArchiveCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new ArchiveCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_retrieve() throws Exception {
        RetrieveCommand command = (RetrieveCommand) parser.parseCommand(
                RetrieveCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new RetrieveCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
