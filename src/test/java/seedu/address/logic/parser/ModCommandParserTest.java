package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ModAddCommand;
import seedu.address.logic.commands.ModCommand;
import seedu.address.logic.commands.ModDeleteCommand;
import seedu.address.logic.commands.ModMarkCommand;
import seedu.address.model.person.Mod;

public class ModCommandParserTest {
    private static final String INVALID_MOD_COMMAND = "bad";
    private static final String INVALID_MOD_STRING = "#CS2103T";
    private static final String VALID_MOD_STRING_CS2103T = "CS2103T";
    private static final String VALID_MOD_STRING_CS2101 = "CS2101";
    private static final String VALID_MOD_STRING_CS2100 = "CS2100";
    private final ModCommandParser parser = new ModCommandParser();

    @Test
    public void parse_emptyArg_throwParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ModCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_unknownCommand_throwParseException() {
        assertParseFailure(parser,
                INVALID_MOD_COMMAND + " " + INDEX_FIRST_PERSON.getOneBased() + " " + VALID_MOD_STRING_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ModCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noIndex_throwParseException() {
        assertParseFailure(parser, ModAddCommand.COMMAND_WORD + " " + VALID_MOD_STRING_CS2103T,
                ModCommand.MESSAGE_INDEX_EMPTY);
    }

    @Test
    public void parse_noMods_throwParseException() {
        assertParseFailure(parser, ModAddCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(),
                ModCommand.MESSAGE_MODS_EMPTY);
    }

    @Test
    public void parse_invalidMod_throwParseException() {
        assertParseFailure(parser,
                ModAddCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + INVALID_MOD_STRING,
                Mod.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidIndex_throwParseException() {
        assertParseFailure(parser, ModAddCommand.COMMAND_WORD + " -1 " + VALID_MOD_STRING_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModAddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneMod_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = ModAddCommand.COMMAND_WORD + " " + targetIndex.getOneBased()
                + " " + VALID_MOD_STRING_CS2103T;

        ObservableList<Mod> mods = FXCollections.singletonObservableList(new Mod(VALID_MOD_STRING_CS2103T));
        ModAddCommand expectedCommand = new ModAddCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_manyMods_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = ModAddCommand.COMMAND_WORD + " " + targetIndex.getOneBased()
                + " " + VALID_MOD_STRING_CS2103T
                + " " + VALID_MOD_STRING_CS2101
                + " " + VALID_MOD_STRING_CS2100;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModAddCommand expectedCommand = new ModAddCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of mod delete when index is absent.
     */
    @Test
    public void parse_noIndexModDelete_throwParseException() {
        assertParseFailure(parser, ModDeleteCommand.COMMAND_WORD + " " + VALID_MOD_STRING_CS2103T,
                ModCommand.MESSAGE_INDEX_EMPTY);
    }

    /**
     * Tests the behaviour of mod delete when mod is absent.
     */
    @Test
    public void parse_noModsModDelete_throwParseException() {
        assertParseFailure(parser, ModDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(),
                ModCommand.MESSAGE_MODS_EMPTY);
    }

    /**
     * Tests the behaviour of mod delete when mod is invalid.
     */
    @Test
    public void parse_invalidModModDelete_throwParseException() {
        assertParseFailure(parser,
                ModDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + INVALID_MOD_STRING,
                Mod.MESSAGE_CONSTRAINTS);
    }

    /**
     * Tests the behaviour of mod delete when index is invalid.
     */
    @Test
    public void parse_invalidIndexModDelete_throwParseException() {
        assertParseFailure(parser, ModDeleteCommand.COMMAND_WORD + " -1 " + VALID_MOD_STRING_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModAddCommand.MESSAGE_USAGE));
    }

    /**
     * Tests the behaviour of deleting 1 mod.
     */
    @Test
    public void parse_deleteOneMod_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = ModDeleteCommand.COMMAND_WORD + " " + targetIndex.getOneBased()
                + " " + VALID_MOD_STRING_CS2103T;

        ObservableList<Mod> mods = FXCollections.singletonObservableList(new Mod(VALID_MOD_STRING_CS2103T));
        ModDeleteCommand expectedCommand = new ModDeleteCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of deleting multiple mods.
     */
    @Test
    public void parse_deleteMultipleMods_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = ModDeleteCommand.COMMAND_WORD + " " + targetIndex.getOneBased()
                + " " + VALID_MOD_STRING_CS2103T
                + " " + VALID_MOD_STRING_CS2101
                + " " + VALID_MOD_STRING_CS2100;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModDeleteCommand expectedCommand = new ModDeleteCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of mod mark when index is absent.
     */
    @Test
    public void parse_noIndexModMark_throwParseException() {
        assertParseFailure(parser, ModMarkCommand.COMMAND_WORD + " " + VALID_MOD_STRING_CS2103T,
                ModCommand.MESSAGE_INDEX_EMPTY);
    }

    /**
     * Tests the behaviour of mod mark when mod is absent.
     */
    @Test
    public void parse_noModsModMark_throwParseException() {
        assertParseFailure(parser, ModMarkCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(),
                ModCommand.MESSAGE_MODS_EMPTY);
    }

    /**
     * Tests the behaviour of mod mark when mod is invalid.
     */
    @Test
    public void parse_invalidModModMark_throwParseException() {
        assertParseFailure(parser,
                ModMarkCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + INVALID_MOD_STRING,
                Mod.MESSAGE_CONSTRAINTS);
    }

    /**
     * Tests the behaviour of mod mark when index is invalid.
     */
    @Test
    public void parse_invalidIndexModMark_throwParseException() {
        assertParseFailure(parser, ModMarkCommand.COMMAND_WORD + " -1 " + VALID_MOD_STRING_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModAddCommand.MESSAGE_USAGE));
    }

    /**
     * Tests the behaviour of marking 1 mod as taken.
     */
    @Test
    public void parse_markOneMod_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = ModMarkCommand.COMMAND_WORD + " " + targetIndex.getOneBased()
                + " " + VALID_MOD_STRING_CS2103T;

        ObservableList<Mod> mods = FXCollections.singletonObservableList(new Mod(VALID_MOD_STRING_CS2103T));
        ModMarkCommand expectedCommand = new ModMarkCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of marking multiple mods as taken.
     */
    @Test
    public void parse_markMultipleMods_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = ModMarkCommand.COMMAND_WORD + " " + targetIndex.getOneBased()
                + " " + VALID_MOD_STRING_CS2103T
                + " " + VALID_MOD_STRING_CS2101
                + " " + VALID_MOD_STRING_CS2100;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModMarkCommand expectedCommand = new ModMarkCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
