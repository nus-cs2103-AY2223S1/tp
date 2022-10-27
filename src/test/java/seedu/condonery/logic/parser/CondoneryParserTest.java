package seedu.condonery.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.ExitCommand;
import seedu.condonery.logic.commands.HelpCommand;
import seedu.condonery.logic.commands.client.FindClientCommand;
import seedu.condonery.logic.commands.property.AddPropertyCommand;
import seedu.condonery.logic.commands.property.ClearPropertyCommand;
import seedu.condonery.logic.commands.property.DeletePropertyCommand;
import seedu.condonery.logic.commands.property.EditPropertyCommand;
import seedu.condonery.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.condonery.logic.commands.property.FindPropertyCommand;
import seedu.condonery.logic.commands.property.ListPropertyCommand;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.client.ClientNameContainsKeywordsPredicate;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.condonery.testutil.EditPropertyDescriptorBuilder;
import seedu.condonery.testutil.PropertyBuilder;
import seedu.condonery.testutil.PropertyUtil;

public class CondoneryParserTest {

    private final CondoneryParser parser = new CondoneryParser();

    @Test
    public void parseCommand_addProperty() throws Exception {
        Property property = new PropertyBuilder().build();
        AddPropertyCommand command = (AddPropertyCommand) parser.parseCommand(PropertyUtil.getAddCommand(property));
        assertEquals(new AddPropertyCommand(property), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearPropertyCommand.COMMAND_WORD) instanceof ClearPropertyCommand);
        assertTrue(parser.parseCommand(ClearPropertyCommand.COMMAND_WORD + " 3") instanceof ClearPropertyCommand);
    }

    @Test
    public void parseCommand_deleteProperty() throws Exception {
        DeletePropertyCommand command = (DeletePropertyCommand) parser.parseCommand(
            DeletePropertyCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeletePropertyCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editProperty() throws Exception {
        Property property = new PropertyBuilder().build();
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(property).build();
        EditPropertyCommand command = (EditPropertyCommand) parser.parseCommand(EditPropertyCommand.COMMAND_WORD + " "
            + INDEX_FIRST.getOneBased() + " " + PropertyUtil.getEditPropertyDescriptorDetails(descriptor));
        assertEquals(new EditPropertyCommand(INDEX_FIRST, descriptor).getEditPropertyDescriptor(),
                command.getEditPropertyDescriptor());
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findProperty() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPropertyCommand command = (FindPropertyCommand) parser.parseCommand(
            FindPropertyCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPropertyCommand(new PropertyNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findClient() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindClientCommand command = (FindClientCommand) parser.parseCommand(
                FindClientCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindClientCommand(new ClientNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listProperty() throws Exception {
        assertTrue(parser.parseCommand(ListPropertyCommand.COMMAND_WORD) instanceof ListPropertyCommand);
        assertTrue(parser.parseCommand(ListPropertyCommand.COMMAND_WORD + " 3") instanceof ListPropertyCommand);
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
