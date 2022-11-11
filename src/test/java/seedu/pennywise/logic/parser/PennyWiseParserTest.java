package seedu.pennywise.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pennywise.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_MONTH_APRIL;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.pennywise.testutil.Assert.assertThrows;
import static seedu.pennywise.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.pennywise.logic.commands.AddCommand;
import seedu.pennywise.logic.commands.ClearCommand;
import seedu.pennywise.logic.commands.DeleteCommand;
import seedu.pennywise.logic.commands.EditCommand;
import seedu.pennywise.logic.commands.ExitCommand;
import seedu.pennywise.logic.commands.HelpCommand;
import seedu.pennywise.logic.commands.SummaryCommand;
import seedu.pennywise.logic.commands.ViewCommand;
import seedu.pennywise.logic.parser.exceptions.ParseException;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.testutil.EditEntryDescriptorBuilder;
import seedu.pennywise.testutil.EntryUtil;
import seedu.pennywise.testutil.ExpenditureBuilder;
import seedu.pennywise.testutil.ViewEntriesDescriptorBuilder;

public class PennyWiseParserTest {

    private final PennyWiseParser parser = new PennyWiseParser();
    private final EntryType expenditureType = new EntryType(VALID_TYPE_EXPENDITURE);
    @Test
    public void parseCommand_add() throws Exception {
        Entry expenditure = new ExpenditureBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EntryUtil.getAddCommand(expenditureType, expenditure));
        assertEquals(new AddCommand(expenditure, expenditureType), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD
                        + " " + INDEX_FIRST_ENTRY.getOneBased()
                        + " "
                        + CliSyntax.PREFIX_TYPE
                        + VALID_TYPE_EXPENDITURE);
        assertEquals(new DeleteCommand(INDEX_FIRST_ENTRY, expenditureType), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Entry expenditure = new ExpenditureBuilder().build();
        EditCommand.EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder(expenditure, expenditureType).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ENTRY.getOneBased() + " " + EntryUtil.getEditEntryDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_ENTRY, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }


    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }


    @Test
    public void parseCommand_summary() throws Exception {
        assertTrue(parser.parseCommand(SummaryCommand.COMMAND_WORD) instanceof SummaryCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand.ViewEntriesDescriptor viewEntriesDescriptor = new ViewEntriesDescriptorBuilder()
                .withEntryType(VALID_TYPE_EXPENDITURE)
                .build();
        ViewCommand viewCommand = new ViewCommand(viewEntriesDescriptor);
        assertEquals(parser.parseCommand(
                ViewCommand.COMMAND_WORD
                        + " "
                        + CliSyntax.PREFIX_TYPE
                        + VALID_TYPE_EXPENDITURE), viewCommand);

        ViewCommand.ViewEntriesDescriptor viewEntriesDescriptorWithMonth = new ViewEntriesDescriptorBuilder()
                .withEntryType(VALID_TYPE_EXPENDITURE)
                .withMonth(VALID_MONTH_APRIL)
                .build();
        ViewCommand viewCommandWithMonth = new ViewCommand(viewEntriesDescriptorWithMonth);
        assertEquals(parser.parseCommand(
                ViewCommand.COMMAND_WORD
                        + " "
                        + CliSyntax.PREFIX_TYPE
                        + VALID_TYPE_EXPENDITURE
                        + " " + CliSyntax.PREFIX_MONTH
                        + VALID_MONTH_APRIL), viewCommandWithMonth);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
