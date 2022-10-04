package tracko.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static tracko.testutil.Assert.assertThrows;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.AddOrderCommand;
import tracko.logic.commands.ClearCommand;
import tracko.logic.commands.order.DeleteOrderCommand;
import tracko.logic.commands.order.EditOrderCommand;
import tracko.logic.commands.order.EditOrderCommand.EditPersonDescriptor;
import tracko.logic.commands.ExitCommand;
import tracko.logic.commands.order.FindOrderCommand;
import tracko.logic.commands.HelpCommand;
import tracko.logic.commands.order.ListOrdersCommand;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.order.NameContainsKeywordsPredicate;
import tracko.model.person.Person;
import tracko.testutil.EditPersonDescriptorBuilder;
import tracko.testutil.PersonBuilder;
import tracko.testutil.PersonUtil;

public class AddressBookParserTest {

    private final TrackOParser parser = new TrackOParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddOrderCommand command = (AddOrderCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddOrderCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteOrderCommand command = (DeleteOrderCommand) parser.parseCommand(
                DeleteOrderCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteOrderCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditOrderCommand command = (EditOrderCommand) parser.parseCommand(EditOrderCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditOrderCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindOrderCommand command = (FindOrderCommand) parser.parseCommand(
                FindOrderCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindOrderCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListOrdersCommand.COMMAND_WORD) instanceof ListOrdersCommand);
        assertTrue(parser.parseCommand(ListOrdersCommand.COMMAND_WORD + " 3") instanceof ListOrdersCommand);
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
