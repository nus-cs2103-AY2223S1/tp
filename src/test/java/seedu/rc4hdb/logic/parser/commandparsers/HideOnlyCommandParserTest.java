package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand.generateComplementListFrom;
import static seedu.rc4hdb.logic.parser.commandparsers.ColumnManipulatorCommandParser.INTENDED_USAGE_FORMAT;
import static seedu.rc4hdb.logic.parser.commandparsers.ColumnManipulatorCommandParser.INVALID_FIELDS_ENTERED;
import static seedu.rc4hdb.logic.parser.commandparsers.ColumnManipulatorCommandParser.WHITESPACE;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;
import static seedu.rc4hdb.testutil.TypicalColumnManipulatorInputs.ALL_VALID_LETTERS;
import static seedu.rc4hdb.testutil.TypicalColumnManipulatorInputs.DUPLICATE_LETTERS;
import static seedu.rc4hdb.testutil.TypicalColumnManipulatorInputs.EMPTY_STRING;
import static seedu.rc4hdb.testutil.TypicalColumnManipulatorInputs.INVALID_LETTERS;
import static seedu.rc4hdb.testutil.TypicalColumnManipulatorInputs.MIXED_CASE_LETTERS;
import static seedu.rc4hdb.testutil.TypicalColumnManipulatorInputs.VALID_LETTERS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.ALL_VALID_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.DUPLICATE_FIELDS;
import static seedu.rc4hdb.testutil.TypicalFieldLists.VALID_FIELDS;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.residentcommands.HideOnlyCommand;

public class HideOnlyCommandParserTest {

    private final HideOnlyCommandParser parser = new HideOnlyCommandParser();

    // Note that the inputs now are letters representing column names, rather than
    // verbose column names which was used when testing the command classes.

    // Hide valid letters returns HideOnlyCommand
    @Test
    public void parse_hideValidLetters_returnsHideOnlyCommand() {
        List<String> fieldsToShow = generateComplementListFrom(VALID_FIELDS);
        HideOnlyCommand expectedCommand = new HideOnlyCommand(fieldsToShow, VALID_FIELDS);
        assertParseSuccess(parser, VALID_LETTERS, expectedCommand);
    }

    // Hide duplicate letters returns HideOnlyCommand
    @Test
    public void parse_hideDuplicateLetters_returnsHideOnlyCommand() {
        List<String> fieldsToShow = generateComplementListFrom(DUPLICATE_FIELDS);
        HideOnlyCommand expectedCommand = new HideOnlyCommand(fieldsToShow, DUPLICATE_FIELDS);
        assertParseSuccess(parser, DUPLICATE_LETTERS, expectedCommand);
    }

    // Hide mixed case letters returns HideOnlyCommand
    @Test
    public void parse_hideMixedCaseLetters_returnsHideOnlyCommand() {
        List<String> fieldsToShow = generateComplementListFrom(VALID_FIELDS);
        HideOnlyCommand expectedCommand = new HideOnlyCommand(fieldsToShow, VALID_FIELDS);
        assertParseSuccess(parser, MIXED_CASE_LETTERS, expectedCommand);
    }

    // Hide all letters returns HideOnlyCommand (but should throw CommandException during execution)
    @Test
    public void parse_hideAllValidLetters_returnsHideOnlyCommand() {
        List<String> fieldsToShow = generateComplementListFrom(ALL_VALID_FIELDS);
        HideOnlyCommand expectedCommand = new HideOnlyCommand(fieldsToShow, ALL_VALID_FIELDS);
        assertParseSuccess(parser, ALL_VALID_LETTERS, expectedCommand);
    }

    // Hide empty string (i.e. no letters) throws exception
    @Test
    public void parse_hideEmptyString_throwsParseException() {
        assertParseFailure(parser, EMPTY_STRING,
                String.format(INTENDED_USAGE_FORMAT, parser.getCommandWord(),
                        parser.getCommandPresentTense(), parser.getCommandWord()));
    }

    // Hide verbose column names throws exception
    @Test
    public void parse_hideVerboseColumnNames_throwsParseException() {
        String validColumns = new ArrayList<>(VALID_FIELDS).stream().reduce(EMPTY_STRING, (x, y) -> x + WHITESPACE + y);
        assertParseFailure(parser, validColumns, INVALID_FIELDS_ENTERED);
    }

    // Hide invalid letters throws exception
    @Test
    public void parse_hideInvalidLetters_throwsParseException() {
        assertParseFailure(parser, INVALID_LETTERS, INVALID_FIELDS_ENTERED);
    }
}
