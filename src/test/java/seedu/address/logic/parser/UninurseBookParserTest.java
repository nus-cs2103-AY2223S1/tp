package seedu.uninurse.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.AddPatientCommand;
import seedu.uninurse.logic.commands.ClearCommand;
import seedu.uninurse.logic.commands.DeletePatientCommand;
import seedu.uninurse.logic.commands.EditPatientCommand;
import seedu.uninurse.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.uninurse.logic.commands.ExitCommand;
import seedu.uninurse.logic.commands.FindCommand;
import seedu.uninurse.logic.commands.HelpCommand;
import seedu.uninurse.logic.commands.ListCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.person.NameContainsKeywordsPredicate;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.testutil.EditPatientDescriptorBuilder;
import seedu.uninurse.testutil.PersonBuilder;
import seedu.uninurse.testutil.PersonUtil;

public class UninurseBookParserTest {

    private final UninurseBookParser parser = new UninurseBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Patient person = new PersonBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddPatientCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser.parseCommand(
                DeletePatientCommand.COMMAND_WORD + " -p " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePatientCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Patient person = new PersonBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(person).build();
        EditPatientCommand command = (EditPatientCommand) parser.parseCommand(EditPatientCommand.COMMAND_WORD + " -p "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditPatientCommand(INDEX_FIRST_PERSON, descriptor), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
