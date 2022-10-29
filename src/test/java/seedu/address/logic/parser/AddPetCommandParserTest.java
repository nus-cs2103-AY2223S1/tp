package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MatchCommand;
import seedu.address.logic.commands.addcommands.AddPetCommand;
import seedu.address.logic.parser.addcommandparser.AddPetCommandParser;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.PetBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

public class AddPetCommandParserTest {
    private AddPetCommandParser parser = new AddPetCommandParser();

    @Test
    public void parse_emptyString_parseFailure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPetCommand.MESSAGE_USAGE_EXISTING_SUPPLIER);
        String input = "";
        assertParseFailure(parser, input, expected);
    }

    @Test
    public void parse_spaces_parseFailure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPetCommand.MESSAGE_USAGE_EXISTING_SUPPLIER);
        String input = "          \n";
        assertParseFailure(parser, input, expected);
    }

    @Test
    public void parse_invalidArgs_parseFailure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPetCommand.MESSAGE_USAGE_EXISTING_SUPPLIER);
        String input = "this is an invalid argument";
        assertParseFailure(parser, input, expected);
    }

    //TODO: add more test cases
}
