package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.clinkedin.testutil.Assert.assertThrows;
import static seedu.clinkedin.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.AddCommand;
import seedu.clinkedin.logic.commands.AddLinkCommand;
import seedu.clinkedin.logic.commands.AddNoteCommand;
import seedu.clinkedin.logic.commands.AddRateCommand;
import seedu.clinkedin.logic.commands.AddTagCommand;
import seedu.clinkedin.logic.commands.ClearCommand;
import seedu.clinkedin.logic.commands.CreateTagTypeCommand;
import seedu.clinkedin.logic.commands.DeleteCommand;
import seedu.clinkedin.logic.commands.DeleteLinkCommand;
import seedu.clinkedin.logic.commands.DeleteNoteCommand;
import seedu.clinkedin.logic.commands.DeleteRateCommand;
import seedu.clinkedin.logic.commands.DeleteTagCommand;
import seedu.clinkedin.logic.commands.DeleteTagTypeCommand;
import seedu.clinkedin.logic.commands.EditCommand;
import seedu.clinkedin.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.clinkedin.logic.commands.EditTagTypeCommand;
import seedu.clinkedin.logic.commands.ExitCommand;
import seedu.clinkedin.logic.commands.ExportCommand;
import seedu.clinkedin.logic.commands.FindCommand;
import seedu.clinkedin.logic.commands.HelpCommand;
import seedu.clinkedin.logic.commands.ImportCommand;
import seedu.clinkedin.logic.commands.ListCommand;
import seedu.clinkedin.logic.commands.RedoCommand;
import seedu.clinkedin.logic.commands.SortCommand;
import seedu.clinkedin.logic.commands.StatsCommand;
import seedu.clinkedin.logic.commands.UndoCommand;
import seedu.clinkedin.logic.commands.ViewCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.DetailsContainKeywordsPredicate;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;
import seedu.clinkedin.testutil.EditPersonDescriptorBuilder;
import seedu.clinkedin.testutil.PersonBuilder;
import seedu.clinkedin.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));

        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new DetailsContainKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " "
                + ListCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_addNote() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        Note note = new Note("Gold Medalist.");
        AddNoteCommand command = (AddNoteCommand) parser
                .parseCommand(AddNoteCommand.COMMAND_WORD + " "
                        + index.getOneBased() + " note/" + note.toString());
        assertEquals(new AddNoteCommand(index, note), command);
    }

    @Test
    public void parseCommand_deleteNote() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        DeleteNoteCommand command = (DeleteNoteCommand) parser.parseCommand(
                DeleteNoteCommand.COMMAND_WORD + " " + index.getOneBased());
        assertEquals(new DeleteNoteCommand(index), command);
    }

    @Test
    public void parseCommand_addLink() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        Link link = new Link(new URL("https://telegram.com"));
        Set<Link> links = new HashSet<>();
        links.add(link);
        AddLinkCommand command = (AddLinkCommand) parser.parseCommand(
                AddLinkCommand.COMMAND_WORD + " " + index.getOneBased() + " l/" + link);
        assertEquals(new AddLinkCommand(index, links), command);
    }

    @Test
    public void parseCommand_deleteLink() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        DeleteLinkCommand command = (DeleteLinkCommand) parser.parseCommand(
                DeleteLinkCommand.COMMAND_WORD + " " + index.getOneBased());
        assertEquals(new DeleteLinkCommand(index), command);
    }

    @Test
    public void parseCommand_stats() throws Exception {
        assertTrue(parser.parseCommand(StatsCommand.COMMAND_WORD) instanceof StatsCommand);
        assertTrue(parser.parseCommand(StatsCommand.COMMAND_WORD + " 1") instanceof StatsCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " 1") instanceof SortCommand);
    }

    @Test
    public void parseCommand_addTag() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        Tag tag = new Tag("Java");
        TagType tagType = new TagType("Skills", new Prefix("st/"));
        UniqueTagTypeMap tagTypeMap = new UniqueTagTypeMap();
        tagTypeMap.mergeTag(tagType, tag);
        AddTagCommand command = (AddTagCommand) parser.parseCommand(
                AddTagCommand.COMMAND_WORD + " " + index.getOneBased()
                        + " " + tagType.getPrefix() + tag.getTagName());
        assertEquals(new AddTagCommand(index, tagTypeMap), command);
    }

    @Test
    public void parseCommand_deleteTag() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        Tag tag = new Tag("Java");
        TagType tagType = new TagType("Skills", new Prefix("st/"));
        UniqueTagTypeMap tagTypeMap = new UniqueTagTypeMap();
        tagTypeMap.mergeTag(tagType, tag);
        DeleteTagCommand command = (DeleteTagCommand) parser.parseCommand(
                DeleteTagCommand.COMMAND_WORD + " " + index.getOneBased()
                        + " " + tagType.getPrefix() + tag.getTagName());
        assertEquals(new DeleteTagCommand(index, tagTypeMap), command);
    }

    @Test
    public void parseCommand_createTagType() throws Exception {
        Prefix prefix = new Prefix("st/");
        TagType tagType = new TagType("Skills", prefix);
        CreateTagTypeCommand command = (CreateTagTypeCommand) parser.parseCommand(
                CreateTagTypeCommand.COMMAND_WORD + " " + tagType.getTagTypeName() + " " + "st");
        assertEquals(new CreateTagTypeCommand(tagType, prefix), command);
    }

    @Test
    public void parseCommand_deleteTagType() throws Exception {
        TagType tagType = new TagType("Skills", new Prefix("st/"));
        DeleteTagTypeCommand command = (DeleteTagTypeCommand) parser.parseCommand(
                DeleteTagTypeCommand.COMMAND_WORD + " " + tagType.getTagTypeName());
        assertEquals(new DeleteTagTypeCommand(tagType), command);
    }

    @Test
    public void parseCommand_editTagType() throws Exception {
        Prefix prefixToEdit = new Prefix("grdt/");
        TagType tagTypeToEdit = new TagType("Grade", prefixToEdit);
        Prefix prefixEditTo = new Prefix("mrkt/");
        TagType tagTypeEditTo = new TagType("Marks", prefixEditTo);
        EditTagTypeCommand command = (EditTagTypeCommand) parser.parseCommand(
                EditTagTypeCommand.COMMAND_WORD + " " + tagTypeToEdit + "-" + tagTypeEditTo
                        + " " + "grdt-mrkt");
        assertEquals(new EditTagTypeCommand(prefixToEdit, tagTypeToEdit, prefixEditTo, tagTypeEditTo), command);
    }

    @Test
    public void parseCommand_addRate() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        Rating rate = new Rating("5");
        AddRateCommand command = (AddRateCommand) parser
                .parseCommand(AddRateCommand.COMMAND_WORD + " "
                        + index.getOneBased() + " rate/" + rate);
        assertEquals(new AddRateCommand(index, rate), command);
    }

    @Test
    public void parseCommand_deleteRate() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        DeleteRateCommand command = (DeleteRateCommand) parser.parseCommand(
                DeleteRateCommand.COMMAND_WORD + " " + index.getOneBased());
        assertEquals(new DeleteRateCommand(index), command);
    }

    @Test
    public void parseCommand_export() throws Exception {
        String path = "data.csv";
        ExportCommand command = (ExportCommand) parser
                .parseCommand(ExportCommand.COMMAND_WORD + " " + "path/" + path);
        assertEquals(new ExportCommand(path, ParserUtil.FileType.CSV), command);
    }

    @Test
    public void parseCommand_import() throws Exception {
        String path = "data.csv";
        ImportCommand command = (ImportCommand) parser
                .parseCommand(ImportCommand.COMMAND_WORD + " " + "path/" + path);
        assertEquals(new ImportCommand(path, ParserUtil.FileType.CSV), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + index.getOneBased());
        assertEquals(new ViewCommand(index), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
