package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UID_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class HealthcareXpressParserTest {

    private final HealthcareXpressParser parser = new HealthcareXpressParser();

    @Test
    public void parseCommand_add() throws Exception {
        ModelManager modelManager = new ModelManager(); // to create uid for new person to be added
        Person person = new PersonBuilder().withUniversalUid().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person), modelManager);
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        ModelManager modelManager = new ModelManager();
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, modelManager) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", modelManager) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        ModelManager modelManager = new ModelManager();
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + PREFIX_UID + VALID_UID_AMY, modelManager);
        assertEquals(new DeleteCommand(new Uid(VALID_UID_AMY)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        ModelManager modelManager = new ModelManager();
        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        descriptor.setPhone(new Phone("20039109"));
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " " + PREFIX_UID
                + VALID_UID_AMY + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor), modelManager);

        assertEquals(new EditCommand(new Uid(VALID_UID_AMY), descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        ModelManager modelManager = new ModelManager();
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, modelManager) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", modelManager) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        ModelManager modelManager = new ModelManager();
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")),
                modelManager);
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        ModelManager modelManager = new ModelManager();
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, modelManager) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", modelManager) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        ModelManager modelManager = new ModelManager();
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, modelManager) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3", modelManager) instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        ModelManager modelManager = new ModelManager();
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), () ->
                        parser.parseCommand("", modelManager));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        ModelManager modelManager = new ModelManager();
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand", modelManager));
    }
}
