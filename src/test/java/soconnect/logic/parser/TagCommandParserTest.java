package soconnect.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static soconnect.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import soconnect.logic.commands.TagAddCommand;
import soconnect.logic.commands.TagCreateCommand;
import soconnect.logic.commands.TagEditCommand;
import soconnect.model.SoConnect;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;
import soconnect.testutil.PersonBuilder;

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
        Model model = new ModelManager(new SoConnect(), new UserPrefs());
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
