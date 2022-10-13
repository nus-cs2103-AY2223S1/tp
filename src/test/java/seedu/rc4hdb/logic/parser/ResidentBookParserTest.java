package seedu.rc4hdb.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.NAME_DESC_AMY;
import static seedu.rc4hdb.logic.commands.storagemodelcommands.StorageCommandTestUtil.VALID_FILE_NAME_PATH;
import static seedu.rc4hdb.logic.commands.storagemodelcommands.StorageCommandTestUtil.VALID_FILE_NAME_STRING;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_FIRST_RESIDENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.misccommands.ExitCommand;
import seedu.rc4hdb.logic.commands.misccommands.HelpCommand;
import seedu.rc4hdb.logic.commands.modelcommands.AddCommand;
import seedu.rc4hdb.logic.commands.modelcommands.ClearCommand;
import seedu.rc4hdb.logic.commands.modelcommands.DeleteCommand;
import seedu.rc4hdb.logic.commands.modelcommands.EditCommand;
import seedu.rc4hdb.logic.commands.modelcommands.FilterCommand;
import seedu.rc4hdb.logic.commands.modelcommands.FindCommand;
import seedu.rc4hdb.logic.commands.modelcommands.ListCommand;
import seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands.FileCommand;
import seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands.FileCreateCommand;
import seedu.rc4hdb.logic.parser.commandparsers.ListCommandParser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.predicates.NameContainsKeywordsPredicate;
import seedu.rc4hdb.testutil.ResidentBuilder;
import seedu.rc4hdb.testutil.ResidentDescriptorBuilder;
import seedu.rc4hdb.testutil.ResidentUtil;

public class ResidentBookParserTest {

    private final ResidentBookParser parser = new ResidentBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Resident resident = new ResidentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ResidentUtil.getAddCommand(resident));
        assertEquals(new AddCommand(resident), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_RESIDENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_RESIDENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Resident resident = new ResidentBuilder().build();
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder(resident).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_RESIDENT.getOneBased() + " " + ResidentUtil.getResidentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_RESIDENT, descriptor), command);
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
        assertThrows(ParseException.class, String.format(ListCommandParser.INTENDED_USAGE), ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_filter() throws Exception {
        assertTrue(parser.parseCommand(FilterCommand.COMMAND_WORD + NAME_DESC_AMY) instanceof FilterCommand);
        Resident resident = new ResidentBuilder().build();
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder(resident).build();
        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD
                + " " + ResidentUtil.getResidentDescriptorDetails(descriptor));
        assertEquals(new FilterCommand(descriptor), command);
    }

    @Test
    public void parseCommand_file() throws Exception {
        assertTrue(parser.parseCommand(FileCommand.COMMAND_WORD + " " + FileCreateCommand.COMMAND_WORD + " "
                + VALID_FILE_NAME_STRING) instanceof FileCommand);
        FileCommand fileCommand = (FileCommand) parser.parseCommand(FileCommand.COMMAND_WORD
                + " " + FileCreateCommand.COMMAND_WORD + " " + VALID_FILE_NAME_STRING);
        assertEquals(new FileCreateCommand(VALID_FILE_NAME_PATH), fileCommand);
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
