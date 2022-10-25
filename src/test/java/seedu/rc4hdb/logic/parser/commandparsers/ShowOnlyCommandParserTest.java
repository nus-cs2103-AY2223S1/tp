package seedu.rc4hdb.logic.parser.commandparsers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static seedu.rc4hdb.logic.commands.modelcommands.ColumnManipulatorCommand.generateComplementListFrom;
import seedu.rc4hdb.logic.commands.modelcommands.HideOnlyCommand;
import seedu.rc4hdb.logic.commands.modelcommands.ShowOnlyCommand;
import static seedu.rc4hdb.logic.parser.commandparsers.ColumnManipulatorCommandParser.*;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;
import static seedu.rc4hdb.testutil.TypicalColumnManipulatorInputs.*;
import static seedu.rc4hdb.testutil.TypicalFieldLists.*;

public class ShowOnlyCommandParserTest {

    private final ShowOnlyCommandParser parser = new ShowOnlyCommandParser();

    // Note that the inputs now are letters representing column names, rather than
    // verbose column names which was used when testing the command classes.

    // Show valid letters returns ShowOnlyCommand
    @Test
    public void parse_showValidLetters_returnsShowOnlyCommand() {
        List<String> fieldsToHide = generateComplementListFrom(VALID_FIELDS);
        ShowOnlyCommand expectedCommand = new ShowOnlyCommand(VALID_FIELDS, fieldsToHide);
        assertParseSuccess(parser, VALID_LETTERS, expectedCommand);
    }

    // Show duplicate letters returns ShowOnlyCommand
    @Test
    public void parse_showDuplicateLetters_returnsShowOnlyCommand() {
        List<String> fieldsToHide = generateComplementListFrom(DUPLICATE_FIELDS);
        ShowOnlyCommand expectedCommand = new ShowOnlyCommand(DUPLICATE_FIELDS, fieldsToHide);
        assertParseSuccess(parser, DUPLICATE_LETTERS, expectedCommand);
    }

    // Show mixed case letters returns ShowOnlyCommand
    @Test
    public void parse_showMixedCaseLetters_returnsShowOnlyCommand() {
        List<String> fieldsToHide = generateComplementListFrom(VALID_FIELDS);
        ShowOnlyCommand expectedCommand = new ShowOnlyCommand(VALID_FIELDS, fieldsToHide);
        assertParseSuccess(parser, MIXED_CASE_LETTERS, expectedCommand);
    }

    // Show all letters returns ShowOnlyCommand (but should throw CommandException during execution)
    @Test
    public void parse_showAllValidLetters_returnsShowOnlyCommand() {
        List<String> fieldsToHide = generateComplementListFrom(ALL_VALID_FIELDS);
        ShowOnlyCommand expectedCommand = new ShowOnlyCommand(ALL_VALID_FIELDS, fieldsToHide);
        assertParseSuccess(parser, ALL_VALID_LETTERS, expectedCommand);
    }

    // Show empty string (i.e. no letters) throws exception
    @Test
    public void parse_showEmptyString_throwsParseException() {
        assertParseFailure(parser, EMPTY_STRING, String.format(INTENDED_USAGE_FORMAT,
                parser.getCommandWord(), parser.getCommandPresentTense(), parser.getCommandWord()));
    }

    // Show verbose column names throws exception
    @Test
    public void parse_showVerboseColumnNames_throwsParseException() {
        String validColumns = new ArrayList<>(VALID_FIELDS).stream().reduce(EMPTY_STRING, (x, y) -> x + WHITESPACE + y);
        assertParseFailure(parser, validColumns, INVALID_FIELDS_ENTERED);
    }

    // Show invalid letters throws exception
    @Test
    public void parse_showInvalidLetters_throwsParseException() {
        assertParseFailure(parser, INVALID_LETTERS, INVALID_FIELDS_ENTERED);
    }
}
