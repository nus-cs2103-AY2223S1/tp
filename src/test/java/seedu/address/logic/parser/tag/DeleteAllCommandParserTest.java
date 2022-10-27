package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_2101;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tag.DeleteAllCommand;
import seedu.address.model.tag.Tag;


public class DeleteAllCommandParserTest {

    private DeleteAllCommandParser parser = new DeleteAllCommandParser();
    private Set<Tag> tagsToDelete = new HashSet<>();
    private Tag tagToDelete2101 = new Tag(VALID_TAG_2101);

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        tagsToDelete.add(tagToDelete2101);
        assertParseSuccess(parser, VALID_TAG_2101, new DeleteAllCommand(tagsToDelete));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAllCommand.MESSAGE_USAGE));
    }
}
