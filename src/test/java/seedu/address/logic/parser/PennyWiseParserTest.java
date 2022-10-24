package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SummaryCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.GraphType;
import seedu.address.model.entry.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditEntryDescriptorBuilder;
import seedu.address.testutil.ExpenditureBuilder;
import seedu.address.testutil.EntryUtil;

public class PennyWiseParserTest {

    private final PennyWiseParser parser = new PennyWiseParser();
    private final EntryType expenditureType = new EntryType(CommandTestUtil.VALID_TYPE_EXPENDITURE);
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
                        + CommandTestUtil.VALID_TYPE_EXPENDITURE);
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
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_summary() throws Exception {
        assertTrue(parser.parseCommand(SummaryCommand.COMMAND_WORD) instanceof SummaryCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand viewCommand = new ViewCommand(
                new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE),
                new GraphType(GraphType.GRAPH_TYPE_CATEGORY));
        assertEquals(parser.parseCommand(
                ViewCommand.COMMAND_WORD
                        + " "
                        + CliSyntax.PREFIX_TYPE
                        + EntryType.ENTRY_TYPE_EXPENDITURE
                        + " " + CliSyntax.PREFIX_GRAPH
                        + GraphType.GRAPH_TYPE_CATEGORY), viewCommand);
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
