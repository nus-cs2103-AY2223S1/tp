package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ModAddCommand;
import seedu.address.logic.commands.ModCommand;
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
    public void parse_oneMod_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = ModAddCommand.COMMAND_WORD + " " + targetIndex.getOneBased()
                + " " + VALID_MOD_STRING_CS2103T;

        Set<Mod> mods = Collections.singleton(new Mod(VALID_MOD_STRING_CS2103T));
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

        Set<Mod> mods = new HashSet<>();
        mods.add(new Mod(VALID_MOD_STRING_CS2103T));
        mods.add(new Mod(VALID_MOD_STRING_CS2101));
        mods.add(new Mod(VALID_MOD_STRING_CS2100));
        ModAddCommand expectedCommand = new ModAddCommand(targetIndex, mods);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
