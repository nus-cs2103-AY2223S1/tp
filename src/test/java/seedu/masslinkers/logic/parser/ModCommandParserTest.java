package seedu.masslinkers.logic.parser;

import static seedu.masslinkers.commons.core.Messages.MESSAGE_INCOMPLETE_COMMAND_FORMAT;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.masslinkers.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.masslinkers.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.ModAddCommand;
import seedu.masslinkers.logic.commands.ModCommand;
import seedu.masslinkers.logic.commands.ModDeleteCommand;
import seedu.masslinkers.logic.commands.ModFindCommand;
import seedu.masslinkers.logic.commands.ModMarkCommand;
import seedu.masslinkers.logic.commands.ModUnmarkCommand;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.ModContainsKeywordsPredicate;
import seedu.masslinkers.model.student.ModTakenContainsKeywordsPredicate;
import seedu.masslinkers.model.student.ModTakingContainsKeywordsPredicate;

public class ModCommandParserTest {
    private static final String INVALID_MOD_COMMAND = "bad";
    private static final String INVALID_MOD_STRING = "#CS2103T";
    private static final String VALID_MOD_STRING_CS2103T = "CS2103T";
    private static final String VALID_MOD_STRING_CS2101 = "CS2101";
    private static final String VALID_MOD_STRING_CS2100 = "CS2100";
    private static final String WHITESPACE = " ";
    private final ModCommandParser parser = new ModCommandParser();
    //@@author jonasgwt
    /**
     * Tests the behaviour of mod commands when there is an empty command input by user.
     */
    @Test
    public void parse_emptyArg_throwParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INCOMPLETE_COMMAND_FORMAT,
                ModCommand.MESSAGE_USAGE));
    }

    /**
     * Tests the behaviour of mod commands when there is an unknown command input by user.
     */
    @Test
    public void parse_unknownCommand_throwParseException() {
        assertParseFailure(parser,
                INVALID_MOD_COMMAND + WHITESPACE + INDEX_FIRST_STUDENT.getOneBased() + WHITESPACE
                        + VALID_MOD_STRING_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ModCommand.MESSAGE_USAGE));
    }

    //// ------------------------------------ MOD ADD COMMAND -----------------------------------------------////

    /**
     * Tests the behaviour of mod add when index is absent.
     */
    @Test
    public void parse_noIndex_throwParseException() {
        assertParseFailure(parser, ModAddCommand.COMMAND_WORD + WHITESPACE + VALID_MOD_STRING_CS2103T,
                ModCommand.MESSAGE_INDEX_EMPTY);
    }

    /**
     * Tests the behaviour of mod add when mod is absent.
     */
    @Test
    public void parse_noMods_throwParseException() {
        assertParseFailure(parser, ModAddCommand.COMMAND_WORD + WHITESPACE + INDEX_FIRST_STUDENT.getOneBased(),
                ModCommand.MESSAGE_MODS_EMPTY);
    }

    /**
     * Tests the behaviour of mod add when mod is invalid.
     */
    @Test
    public void parse_invalidMod_throwParseException() {
        assertParseFailure(parser,
                ModAddCommand.COMMAND_WORD
                        + WHITESPACE + INDEX_FIRST_STUDENT.getOneBased()
                        + WHITESPACE + INVALID_MOD_STRING,
                Mod.MESSAGE_CONSTRAINTS);
    }

    /**
     * Tests the behaviour of mod add when index is invalid.
     */
    @Test
    public void parse_invalidIndex_throwParseException() {
        assertParseFailure(parser, ModAddCommand.COMMAND_WORD + " -1 " + VALID_MOD_STRING_CS2103T,
                String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, ModAddCommand.MESSAGE_USAGE));
    }

    /**
     * Tests the behaviour of adding 1 mod.
     */
    @Test
    public void parse_oneMod_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModAddCommand.COMMAND_WORD + WHITESPACE + targetIndex.getOneBased()
                + WHITESPACE + VALID_MOD_STRING_CS2103T;

        ObservableList<Mod> mods = FXCollections.singletonObservableList(new Mod(VALID_MOD_STRING_CS2103T));
        ModAddCommand expectedCommand = new ModAddCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of adding 1 mod with additional whitespace.
     */
    @Test
    public void parse_oneModWithExtraWhitespace_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModAddCommand.COMMAND_WORD + WHITESPACE + WHITESPACE + WHITESPACE
                + targetIndex.getOneBased()
                + WHITESPACE + WHITESPACE + WHITESPACE
                + VALID_MOD_STRING_CS2103T;

        ObservableList<Mod> mods = FXCollections.singletonObservableList(new Mod(VALID_MOD_STRING_CS2103T));
        ModAddCommand expectedCommand = new ModAddCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of adding multiple mods.
     */
    @Test
    public void parse_manyMods_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModAddCommand.COMMAND_WORD + WHITESPACE + targetIndex.getOneBased()
                + WHITESPACE + VALID_MOD_STRING_CS2103T
                + WHITESPACE + VALID_MOD_STRING_CS2101
                + WHITESPACE + VALID_MOD_STRING_CS2100;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModAddCommand expectedCommand = new ModAddCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of adding multiple mods with additional whitespace
     */
    @Test
    public void parse_manyModsWithExtraWhitespace_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModAddCommand.COMMAND_WORD + WHITESPACE + WHITESPACE
                + targetIndex.getOneBased()
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2103T
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2101
                + WHITESPACE + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2100;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModAddCommand expectedCommand = new ModAddCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    //// ------------------------------------ MOD DELETE COMMAND -----------------------------------------------////
    //@@author ElijahS67
    /**
     * Tests the behaviour of mod delete when index is absent.
     */
    @Test
    public void parse_noIndexModDelete_throwParseException() {
        assertParseFailure(parser, ModDeleteCommand.COMMAND_WORD + WHITESPACE + VALID_MOD_STRING_CS2103T,
                ModCommand.MESSAGE_INDEX_EMPTY);
    }

    /**
     * Tests the behaviour of mod delete when mod is absent.
     */
    @Test
    public void parse_noModsModDelete_throwParseException() {
        assertParseFailure(parser, ModDeleteCommand.COMMAND_WORD + WHITESPACE + INDEX_FIRST_STUDENT.getOneBased(),
                ModCommand.MESSAGE_MODS_EMPTY);
    }

    /**
     * Tests the behaviour of mod delete when mod is invalid.
     */
    @Test
    public void parse_invalidModModDelete_throwParseException() {
        assertParseFailure(parser,
                ModDeleteCommand.COMMAND_WORD
                        + WHITESPACE + INDEX_FIRST_STUDENT.getOneBased()
                        + WHITESPACE + INVALID_MOD_STRING,
                Mod.MESSAGE_CONSTRAINTS);
    }

    /**
     * Tests the behaviour of mod delete when index is invalid.
     */
    @Test
    public void parse_invalidIndexModDelete_throwParseException() {
        assertParseFailure(parser, ModDeleteCommand.COMMAND_WORD + " -1 " + VALID_MOD_STRING_CS2103T,
                MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Tests the behaviour of deleting 1 mod.
     */
    @Test
    public void parse_deleteOneMod_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModDeleteCommand.COMMAND_WORD + WHITESPACE + targetIndex.getOneBased()
                + WHITESPACE + VALID_MOD_STRING_CS2103T;

        ObservableList<Mod> mods = FXCollections.singletonObservableList(new Mod(VALID_MOD_STRING_CS2103T));
        ModDeleteCommand expectedCommand = new ModDeleteCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of deleting multiple mods.
     */
    @Test
    public void parse_deleteMultipleMods_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModDeleteCommand.COMMAND_WORD + WHITESPACE + targetIndex.getOneBased()
                + WHITESPACE + VALID_MOD_STRING_CS2103T
                + WHITESPACE + VALID_MOD_STRING_CS2101
                + WHITESPACE + VALID_MOD_STRING_CS2100;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModDeleteCommand expectedCommand = new ModDeleteCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of deleting multiple mods with additional whitespace.
     */
    @Test
    public void parse_deleteMultipleModsWithExtraWhitespace_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModDeleteCommand.COMMAND_WORD + WHITESPACE + WHITESPACE
                + targetIndex.getOneBased()
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2103T
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2101
                + WHITESPACE + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2100;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModDeleteCommand expectedCommand = new ModDeleteCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    //// ------------------------------------ MOD FIND COMMAND -----------------------------------------------////

    /**
     * Tests the behaviour of mod find when no module is entered.
     */
    @Test
    public void parse_findEmptyString_throwsParseException() {
        assertParseFailure(parser, ModFindCommand.COMMAND_WORD + WHITESPACE, String.format(
                ModCommand.MESSAGE_MODS_EMPTY));
    }

    /**
     * Tests the behaviour of mod find taken when no module is entered.
     */
    @Test
    public void parse_findTakenEmptyString_throwsParseException() {
        assertParseFailure(parser, ModFindCommand.COMMAND_WORD + WHITESPACE + ModFindCommand.COMMAND_WORD_TAKEN
                + "     ", String.format(ModCommand.MESSAGE_MODS_EMPTY));
    }

    /**
     * Tests the behaviour of mod find.
     */
    @Test
    public void parse_modFind_success() {
        String userInput = ModFindCommand.COMMAND_WORD
                + WHITESPACE + VALID_MOD_STRING_CS2100;
        ModFindCommand expectedCommand = new ModFindCommand(
                new ModContainsKeywordsPredicate(Arrays.asList(VALID_MOD_STRING_CS2100)));
        assertParseSuccess(parser, userInput,
                expectedCommand);
    }

    /**
     * Tests the behaviour of mod find taken.
     */
    @Test
    public void parse_modFindTaken_success() {
        String userInput = ModFindCommand.COMMAND_WORD
                + WHITESPACE + ModFindCommand.COMMAND_WORD_TAKEN
                + WHITESPACE + VALID_MOD_STRING_CS2100;
        ModFindCommand expectedCommand = new ModFindCommand(
                new ModTakenContainsKeywordsPredicate(Arrays.asList(VALID_MOD_STRING_CS2100)));
        assertParseSuccess(parser, userInput,
                expectedCommand);
    }

    /**
     * Tests the behaviour of mod find taken with whitespace.
     */
    @Test
    public void parse_modFindTakenWithWhitespace_success() {
        String userInput = ModFindCommand.COMMAND_WORD
                + WHITESPACE + WHITESPACE + ModFindCommand.COMMAND_WORD_TAKEN
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2100 + WHITESPACE;
        ModFindCommand expectedCommand = new ModFindCommand(
                new ModTakenContainsKeywordsPredicate(Arrays.asList(VALID_MOD_STRING_CS2100)));
        assertParseSuccess(parser, userInput,
                expectedCommand);
    }

    /**
     * Tests the behaviour of mod find taking.
     */
    @Test
    public void parse_modFindTaking_success() {
        String userInput = ModFindCommand.COMMAND_WORD
                + WHITESPACE + ModFindCommand.COMMAND_WORD_TAKING
                + WHITESPACE + VALID_MOD_STRING_CS2100;
        ModFindCommand expectedCommand = new ModFindCommand(
                new ModTakingContainsKeywordsPredicate(Arrays.asList(VALID_MOD_STRING_CS2100)));
        assertParseSuccess(parser, userInput,
                expectedCommand);
    }

    /**
     * Tests the behaviour of mod find taking with whitespace.
     */
    @Test
    public void parse_modFindTakingWithWhitespace_success() {
        String userInput = ModFindCommand.COMMAND_WORD
                + WHITESPACE + WHITESPACE + ModFindCommand.COMMAND_WORD_TAKING
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2100 + WHITESPACE;
        ModFindCommand expectedCommand = new ModFindCommand(
                new ModTakingContainsKeywordsPredicate(Arrays.asList(VALID_MOD_STRING_CS2100)));
        assertParseSuccess(parser, userInput,
                expectedCommand);
    }

    //// ------------------------------------ MOD MARK COMMAND -----------------------------------------------////
    //@@author carriezhengjr
    /**
     * Tests the behaviour of mod mark when index is absent.
     */
    @Test
    public void parse_noIndexModMark_throwParseException() {
        assertParseFailure(parser, ModMarkCommand.COMMAND_WORD + WHITESPACE + VALID_MOD_STRING_CS2103T,
                ModCommand.MESSAGE_INDEX_EMPTY);
    }

    /**
     * Tests the behaviour of mod mark when mod is absent.
     */
    @Test
    public void parse_noModsModMark_throwParseException() {
        assertParseFailure(parser, ModMarkCommand.COMMAND_WORD + WHITESPACE + INDEX_FIRST_STUDENT.getOneBased(),
                ModCommand.MESSAGE_MODS_EMPTY);
    }

    /**
     * Tests the behaviour of mod mark when mod is invalid.
     */
    @Test
    public void parse_invalidModModMark_throwParseException() {
        assertParseFailure(parser,
                ModMarkCommand.COMMAND_WORD
                        + WHITESPACE + INDEX_FIRST_STUDENT.getOneBased()
                        + WHITESPACE + INVALID_MOD_STRING,
                Mod.MESSAGE_CONSTRAINTS);
    }

    /**
     * Tests the behaviour of mod mark when index is invalid.
     */
    @Test
    public void parse_invalidIndexModMark_throwParseException() {
        assertParseFailure(parser, ModMarkCommand.COMMAND_WORD + " -1 " + VALID_MOD_STRING_CS2103T,
                MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Tests the behaviour of marking 1 mod as taken.
     */
    @Test
    public void parse_markOneMod_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModMarkCommand.COMMAND_WORD + WHITESPACE + targetIndex.getOneBased()
                + WHITESPACE + VALID_MOD_STRING_CS2103T;

        ObservableList<Mod> mods = FXCollections.singletonObservableList(new Mod(VALID_MOD_STRING_CS2103T));
        ModMarkCommand expectedCommand = new ModMarkCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of marking 1 mod as taken with whitespace.
     */
    @Test
    public void parse_markOneModWithWhitespace_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModMarkCommand.COMMAND_WORD + WHITESPACE + WHITESPACE
                + targetIndex.getOneBased()
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2103T + WHITESPACE;

        ObservableList<Mod> mods = FXCollections.singletonObservableList(new Mod(VALID_MOD_STRING_CS2103T));
        ModMarkCommand expectedCommand = new ModMarkCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of marking multiple mods as taken.
     */
    @Test
    public void parse_markMultipleMods_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModMarkCommand.COMMAND_WORD + WHITESPACE + targetIndex.getOneBased()
                + WHITESPACE + VALID_MOD_STRING_CS2103T
                + WHITESPACE + VALID_MOD_STRING_CS2101
                + WHITESPACE + VALID_MOD_STRING_CS2100;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModMarkCommand expectedCommand = new ModMarkCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of marking multiple mods as taken with whitespace.
     */
    @Test
    public void parse_markMultipleModsWithWhitespace_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModMarkCommand.COMMAND_WORD + WHITESPACE + WHITESPACE
                + targetIndex.getOneBased()
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2103T
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2101
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2100 + WHITESPACE;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModMarkCommand expectedCommand = new ModMarkCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    //// ------------------------------------ MOD UNMARK COMMAND -----------------------------------------------////

    /**
     * Tests the behaviour of mod unmark when index is absent.
     */
    @Test
    public void parse_noIndexModUnmark_throwParseException() {
        assertParseFailure(parser, ModUnmarkCommand.COMMAND_WORD + WHITESPACE + VALID_MOD_STRING_CS2103T,
                ModCommand.MESSAGE_INDEX_EMPTY);
    }

    /**
     * Tests the behaviour of mod unmark when mod is absent.
     */
    @Test
    public void parse_noModsModUnmark_throwParseException() {
        assertParseFailure(parser, ModUnmarkCommand.COMMAND_WORD + WHITESPACE + INDEX_FIRST_STUDENT.getOneBased(),
                ModCommand.MESSAGE_MODS_EMPTY);
    }

    /**
     * Tests the behaviour of mod unmark when mod is invalid.
     */
    @Test
    public void parse_invalidModModUnmark_throwParseException() {
        assertParseFailure(parser,
                ModUnmarkCommand.COMMAND_WORD
                        + WHITESPACE + INDEX_FIRST_STUDENT.getOneBased()
                        + WHITESPACE + INVALID_MOD_STRING,
                Mod.MESSAGE_CONSTRAINTS);
    }

    /**
     * Tests the behaviour of mod unmark when index is invalid.
     */
    @Test
    public void parse_invalidIndexModUnmark_throwParseException() {
        assertParseFailure(parser, ModUnmarkCommand.COMMAND_WORD + " -1 " + VALID_MOD_STRING_CS2103T,
                MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Tests the behaviour of unmarking 1 mod as not taken.
     */
    @Test
    public void parse_unmarkOneMod_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModUnmarkCommand.COMMAND_WORD + WHITESPACE + targetIndex.getOneBased()
                + WHITESPACE + VALID_MOD_STRING_CS2103T;

        ObservableList<Mod> mods = FXCollections.singletonObservableList(new Mod(VALID_MOD_STRING_CS2103T));
        ModUnmarkCommand expectedCommand = new ModUnmarkCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of unmarking multiple mods as not taken.
     */
    @Test
    public void parse_unmarkMultipleMods_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModUnmarkCommand.COMMAND_WORD + WHITESPACE + targetIndex.getOneBased()
                + WHITESPACE + VALID_MOD_STRING_CS2103T
                + WHITESPACE + VALID_MOD_STRING_CS2101
                + WHITESPACE + VALID_MOD_STRING_CS2100;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModUnmarkCommand expectedCommand = new ModUnmarkCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the behaviour of unmarking multiple mods as not taken with whitespace.
     */
    @Test
    public void parse_unmarkMultipleModsWithWhitespace_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = ModUnmarkCommand.COMMAND_WORD + WHITESPACE + WHITESPACE
                + targetIndex.getOneBased()
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2103T
                + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2101
                + WHITESPACE + WHITESPACE + WHITESPACE + VALID_MOD_STRING_CS2100 + WHITESPACE;

        ObservableList<Mod> mods = FXCollections.observableArrayList();
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        ModUnmarkCommand expectedCommand = new ModUnmarkCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
