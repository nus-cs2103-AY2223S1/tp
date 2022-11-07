package seedu.rc4hdb.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.NAME_DESC_AMY;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.VALID_ALL_SPECIFIER_DESC;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.VALID_ANY_SPECIFIER_DESC;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.VALID_NAME_AMY;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.VALID_FILE_NAME_STRING;
import static seedu.rc4hdb.logic.parser.commandparsers.FileCommandParser.DATA_DIR_PATH;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalColumnManipulatorInputs.INVALID_LETTERS;
import static seedu.rc4hdb.testutil.TypicalColumnManipulatorInputs.VALID_LETTERS;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_FIRST_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalSpecifiers.ALL_SPECIFIER;
import static seedu.rc4hdb.testutil.TypicalSpecifiers.ANY_SPECIFIER;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_STRING;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_VENUE_NAME;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.filecommands.FileCommand;
import seedu.rc4hdb.logic.commands.filecommands.FileCreateCommand;
import seedu.rc4hdb.logic.commands.misccommands.ExitCommand;
import seedu.rc4hdb.logic.commands.misccommands.HelpCommand;
import seedu.rc4hdb.logic.commands.residentcommands.AddCommand;
import seedu.rc4hdb.logic.commands.residentcommands.ClearCommand;
import seedu.rc4hdb.logic.commands.residentcommands.DeleteCommand;
import seedu.rc4hdb.logic.commands.residentcommands.EditCommand;
import seedu.rc4hdb.logic.commands.residentcommands.FilterCommand;
import seedu.rc4hdb.logic.commands.residentcommands.FindCommand;
import seedu.rc4hdb.logic.commands.residentcommands.HideOnlyCommand;
import seedu.rc4hdb.logic.commands.residentcommands.ListCommand;
import seedu.rc4hdb.logic.commands.residentcommands.ResetCommand;
import seedu.rc4hdb.logic.commands.residentcommands.ShowOnlyCommand;
import seedu.rc4hdb.logic.commands.venuecommands.VenueCommand;
import seedu.rc4hdb.logic.commands.venuecommands.VenueViewCommand;
import seedu.rc4hdb.logic.parser.commandparsers.HideOnlyCommandParser;
import seedu.rc4hdb.logic.parser.commandparsers.ShowOnlyCommandParser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.resident.predicates.NameContainsKeywordsPredicate;
import seedu.rc4hdb.testutil.ResidentBuilder;
import seedu.rc4hdb.testutil.ResidentDescriptorBuilder;
import seedu.rc4hdb.testutil.ResidentStringDescriptorBuilder;
import seedu.rc4hdb.testutil.ResidentUtil;

/**
 * Unit tests for {@link Rc4hdbParser}.
 */
public class Rc4hdbParserTest {

    private final Rc4hdbParser parser = new Rc4hdbParser();

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
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " /i") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " /e") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " /i n p f x") instanceof ListCommand);
    }

    @Test
    public void parseCommand_showOnly() throws Exception {
        assertThrows(ParseException.class, String.format(ShowOnlyCommandParser.INTENDED_USAGE_FORMAT,
                ShowOnlyCommand.COMMAND_WORD, ShowOnlyCommand.COMMAND_PRESENT_TENSE, ShowOnlyCommand.COMMAND_WORD), ()
                -> parser.parseCommand(ShowOnlyCommand.COMMAND_WORD));
        assertThrows(ParseException.class, ShowOnlyCommandParser.INVALID_FIELDS_ENTERED, ()
                -> parser.parseCommand(ShowOnlyCommand.COMMAND_WORD + " " + INVALID_LETTERS));
        assertTrue(parser.parseCommand(ShowOnlyCommand.COMMAND_WORD + " " + VALID_LETTERS) instanceof ShowOnlyCommand);
    }

    @Test
    public void parseCommand_hideOnly() throws Exception {
        assertThrows(ParseException.class, String.format(HideOnlyCommandParser.INTENDED_USAGE_FORMAT,
                HideOnlyCommand.COMMAND_WORD, HideOnlyCommand.COMMAND_PRESENT_TENSE, HideOnlyCommand.COMMAND_WORD), ()
                -> parser.parseCommand(HideOnlyCommand.COMMAND_WORD));
        assertThrows(ParseException.class, HideOnlyCommandParser.INVALID_FIELDS_ENTERED, ()
                -> parser.parseCommand(HideOnlyCommand.COMMAND_WORD + " " + INVALID_LETTERS));
        assertTrue(parser.parseCommand(HideOnlyCommand.COMMAND_WORD + " " + VALID_LETTERS) instanceof HideOnlyCommand);
    }

    @Test
    public void parseCommand_reset() throws Exception {
        assertTrue(parser.parseCommand(ResetCommand.COMMAND_WORD) instanceof ResetCommand);
        assertTrue(parser.parseCommand(ResetCommand.COMMAND_WORD + " 3") instanceof ResetCommand);
    }

    @Test
    public void parseCommand_filterAll() throws Exception {
        assertTrue(parser.parseCommand(FilterCommand.COMMAND_WORD + VALID_ALL_SPECIFIER_DESC
                + NAME_DESC_AMY) instanceof FilterCommand);
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder().withName(VALID_NAME_AMY)
                        .withEmail(VALID_EMAIL_AMY).build();
        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD
                + VALID_ALL_SPECIFIER_DESC + " " + ResidentUtil.getResidentDescriptorDetails(descriptor));
        FilterCommand newCommand = new FilterCommand(descriptor, ALL_SPECIFIER);
        assertEquals(newCommand, command);
    }

    @Test
    public void parseCommand_filterAny() throws Exception {
        assertTrue(parser.parseCommand(FilterCommand.COMMAND_WORD + VALID_ANY_SPECIFIER_DESC
                + NAME_DESC_AMY) instanceof FilterCommand);
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder().withName(VALID_NAME_AMY)
                        .withEmail(VALID_EMAIL_AMY).build();
        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD
                + VALID_ANY_SPECIFIER_DESC + " " + ResidentUtil.getResidentDescriptorDetails(descriptor));
        assertEquals(new FilterCommand(descriptor, ANY_SPECIFIER), command);
    }

    @Test
    public void parseCommand_fileCommand() throws Exception {
        assertTrue(parser.parseCommand(FileCommand.COMMAND_WORD + " " + FileCreateCommand.COMMAND_WORD + " "
                + VALID_FILE_NAME_STRING) instanceof FileCommand);
        FileCommand fileCommand = (FileCommand) parser.parseCommand(FileCommand.COMMAND_WORD
                + " " + FileCreateCommand.COMMAND_WORD + " " + VALID_FILE_NAME_STRING);
        assertEquals(new FileCreateCommand(DATA_DIR_PATH, VALID_FILE_NAME_STRING), fileCommand);
    }

    @Test
    public void parseCommand_venueCommand() throws Exception {
        assertTrue(parser.parseCommand(VenueCommand.COMMAND_WORD + " " + VenueViewCommand.COMMAND_WORD + " "
                + MEETING_ROOM_STRING) instanceof VenueViewCommand);
        VenueCommand venueCommand = (VenueCommand) parser.parseCommand(VenueCommand.COMMAND_WORD + " "
                + VenueViewCommand.COMMAND_WORD + " " + MEETING_ROOM_STRING);
        assertEquals(new VenueViewCommand(MEETING_ROOM_VENUE_NAME), venueCommand);
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
