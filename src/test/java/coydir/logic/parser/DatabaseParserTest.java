package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static coydir.testutil.Assert.assertThrows;
import static coydir.testutil.TypicalIndexes.ID_FIRST_EMPLOYEE;
import static coydir.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import coydir.logic.commands.AddCommand;
import coydir.logic.commands.BatchAddCommand;
import coydir.logic.commands.ClearCommand;
import coydir.logic.commands.DeleteCommand;
import coydir.logic.commands.EditCommand;
import coydir.logic.commands.EditCommand.EditPersonDescriptor;
import coydir.logic.commands.ExitCommand;
import coydir.logic.commands.FindCommand;
import coydir.logic.commands.HelpCommand;
import coydir.logic.commands.ListCommand;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;
import coydir.model.person.PersonMatchesKeywordsPredicate;
import coydir.testutil.EditPersonDescriptorBuilder;
import coydir.testutil.PersonBuilder;
import coydir.testutil.PersonUtil;

public class DatabaseParserTest {

    private final DatabaseParser parser = new DatabaseParser();

    @Test
    public void parseCommand_add() throws Exception {
        PersonBuilder builder = new PersonBuilder();
        String newId = String.valueOf(EmployeeId.getCount());
        Person person = builder.withEmployeeId(newId).build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(ID_FIRST_EMPLOYEE), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = new ArrayList<>(Arrays.asList("foo", "bar", "baz"));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PersonUtil.getFindCommand(keywords));
        assertEquals(new FindCommand(
                    new PersonMatchesKeywordsPredicate(keywords.get(0), keywords.get(1), keywords.get(2))
                    ), command);
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
    public void parseCommand_batch_add() throws Exception {
        assertTrue(parser.parseCommand(BatchAddCommand.COMMAND_WORD + " coydir.csv") instanceof BatchAddCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        String userinput = "unknownCommand";
        assertThrows(ParseException.class, '"' + userinput + '"' + MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand"));
    }
}
