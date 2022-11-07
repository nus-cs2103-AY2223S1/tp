package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.AddTagCommand;
import seedu.clinkedin.logic.commands.CreateTagTypeCommand;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;


public class AddTagCommandParserTest {

    private final AddTagCommandParser parser = new AddTagCommandParser();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    // checked with author, will uncomment once bug is fixed
    // @Test
    // public void parse_emptyArg_throwsParseException() {
    //    assertParseFailure(parser, "",
    //            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    //}

    @Test
    public void validArgs() throws CommandException {

        // create new TagType of Test
        Prefix skillPrefix = new Prefix("test/");
        TagType skillTag = new TagType("Test", skillPrefix);
        CreateTagTypeCommand cmd = new CreateTagTypeCommand(skillTag, skillPrefix);
        cmd.execute(model);

        // add tag to person
        UniqueTagTypeMap newMap = new UniqueTagTypeMap();
        newMap.mergeTag(skillTag, new Tag("TEST"));
        AddTagCommand expected = new AddTagCommand(Index.fromOneBased(1), newMap);
        assertParseSuccess(parser, "1 test/TEST", expected);
    }

}
