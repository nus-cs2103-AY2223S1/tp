package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagAddCommand;
import seedu.address.logic.commands.TagCreateCommand;
import seedu.address.logic.commands.TagEditCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

class TagCommandParserTest {

    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parseCommand_create() throws Exception {
        Tag tag = new Tag("nothing");
        TagCreateCommand command = (TagCreateCommand) parser.parse("create t/nothing");
        assertEquals(new TagCreateCommand(tag), command);
    }

    @Test
    @Disabled
    public void parseCommand_add() throws Exception {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        Tag tag = new Tag("nothing");
        Person person = new PersonBuilder().build();
        model.addTag(new Tag("nothing"));
        model.addPerson(person);
        TagAddCommand command = (TagAddCommand) parser.parse("add "
                + INDEX_FIRST_PERSON.getOneBased()
                + " t/nothing");
        assertEquals(new TagAddCommand(INDEX_FIRST_PERSON, tag), command);
    }

    @Test
    @Disabled
    public void parseCommand_edit() throws Exception {
        Tag oldTag = new Tag("nothing");
        Tag newTag = new Tag("something");
        TagEditCommand command = (TagEditCommand) parser.parse("edit t/nothing t/something");
        assertEquals(new TagEditCommand(oldTag, newTag), command);
    }

}
