package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.OldAddCommand;
import seedu.address.logic.commands.OldClearCommand;
import seedu.address.logic.commands.OldDeleteCommand;
import seedu.address.logic.commands.OldEditCommand;
import seedu.address.logic.commands.OldEditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.OldExitCommand;
import seedu.address.logic.commands.OldFindCommand;
import seedu.address.logic.commands.OldHelpCommand;
import seedu.address.logic.commands.OldListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class OldAddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        OldAddCommand command = (OldAddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new OldAddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(OldClearCommand.COMMAND_WORD) instanceof OldClearCommand);
        assertTrue(parser.parseCommand(OldClearCommand.COMMAND_WORD + " 3") instanceof OldClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        OldDeleteCommand command = (OldDeleteCommand) parser.parseCommand(
                OldDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new OldDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        OldEditCommand command = (OldEditCommand) parser.parseCommand(OldEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new OldEditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(OldExitCommand.COMMAND_WORD) instanceof OldExitCommand);
        assertTrue(parser.parseCommand(OldExitCommand.COMMAND_WORD + " 3") instanceof OldExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        OldFindCommand command = (OldFindCommand) parser.parseCommand(
                OldFindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new OldFindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(OldHelpCommand.COMMAND_WORD) instanceof OldHelpCommand);
        assertTrue(parser.parseCommand(OldHelpCommand.COMMAND_WORD + " 3") instanceof OldHelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(OldListCommand.COMMAND_WORD) instanceof OldListCommand);
        assertTrue(parser.parseCommand(OldListCommand.COMMAND_WORD + " 3") instanceof OldListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                OldHelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
