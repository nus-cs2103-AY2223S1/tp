package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.commands.CommandTestUtil.CLASS_DESC_CS1101S;
import static seedu.taassist.logic.commands.CommandTestUtil.CLASS_DESC_CS1231S;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_CLASS_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1101S;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1231S;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taassist.testutil.TestUtil.joinWithSpace;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.DeletecCommand;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.testutil.ModuleClassBuilder;

public class DeletecCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletecCommand.COMMAND_WORD, DeletecCommand.MESSAGE_USAGE);
    private DeletecCommandParser parser = new DeletecCommandParser();


    @Test
    public void parse_emptyUserInput_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, VALID_CLASS_CS1101S, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CLASS_DESC_CS1101S, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_prefixWithEmptyModuleClass_failure() {
        assertParseFailure(parser, " " + PREFIX_MODULE_CLASS + PREAMBLE_WHITESPACE,
                ModuleClass.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidModuleClassName_failure() {
        assertParseFailure(parser, INVALID_CLASS_DESC, ModuleClass.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validSingleModuleClassPresent_success() {
        Set<ModuleClass> moduleClassSet = new HashSet<>();
        ModuleClass moduleClass = new ModuleClassBuilder().withName(VALID_CLASS_CS1231S).build();
        moduleClassSet.add(moduleClass);
        assertParseSuccess(parser, CLASS_DESC_CS1231S, new DeletecCommand(moduleClassSet));
    }

    @Test
    public void parse_multipleModuleClassPresent_success() {
        Set<ModuleClass> moduleClassSet = new HashSet<>();
        ModuleClass cs1231s = new ModuleClassBuilder().withName(VALID_CLASS_CS1231S).build();
        ModuleClass cs1101s = new ModuleClassBuilder().withName(VALID_CLASS_CS1101S).build();
        moduleClassSet.add(cs1231s);
        moduleClassSet.add(cs1101s);
        assertParseSuccess(parser, joinWithSpace(CLASS_DESC_CS1101S, CLASS_DESC_CS1231S),
                new DeletecCommand(moduleClassSet));
    }
}
