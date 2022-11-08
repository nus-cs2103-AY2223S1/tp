package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_AMBIGUOUS_TITLE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditNoteCommand;
import seedu.address.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditNoteCommand object
 */
public class EditNoteCommandParser implements Parser<EditNoteCommand> {

    private Model model;

    /**
     * Constructs a {@code EditCommandParser}
     * @param model the model of the current state
     */
    public EditNoteCommandParser(Model model) {
        this.model = model;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditNoteCommand
     * and returns an EditNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTES_TITLE, PREFIX_NOTES_CONTENT, PREFIX_TAG);

        String preamble = argMultimap.getPreamble();
        Index index;

        try {
            index = ParserUtil.parseIndex(preamble);
        } catch (ParseException pe) {
            filterNotesByTitle(preamble, pe);
            index = Index.fromOneBased(1);
        }

        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();

        if (argMultimap.getValue(PREFIX_NOTES_TITLE).isPresent()) {
            editNoteDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_NOTES_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_NOTES_CONTENT).isPresent()) {
            editNoteDescriptor.setContent(ParserUtil.parseContent(argMultimap.getValue(PREFIX_NOTES_CONTENT).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editNoteDescriptor::setTags);

        if (!editNoteDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditNoteCommand.MESSAGE_NOT_EDITED);
        }

        return new EditNoteCommand(index, editNoteDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet, model));
    }

    /**
     * Filters the {@code ObservableList<Note>} by title
     * @param preamble the name to search for, by complete word
     * @param pe the ParseException to throw on failure
     * @throws ParseException if there is nobody found by the find command, or there exist
     *      an ambiguity
     */
    private void filterNotesByTitle(String preamble, ParseException pe) throws ParseException {
        try {
            new FindNoteCommandParser().parse(preamble).execute(model);
        } catch (ParseException ignored) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE), pe);
        }

        ObservableList<Note> filteredNoteList = model.getFilteredNoteList();

        String splitPreamble = Arrays.stream(preamble.split(" "))
                .map(x -> "\"" + x.trim() + "\"")
                .collect(Collectors.joining(" or "));

        if (filteredNoteList.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_TITLE, splitPreamble), pe);
        } else if (filteredNoteList.size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_AMBIGUOUS_TITLE, splitPreamble), pe);
        }
    }

}
