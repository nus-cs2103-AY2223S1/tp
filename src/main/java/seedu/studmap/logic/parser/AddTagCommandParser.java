package seedu.studmap.logic.parser;

import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.AddTagCommand;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagCommand object
 */
public class AddTagCommandParser extends EditStudentCommandParser<AddTagCommand.AddTagCommandStudentEditor> {

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Set<Tag> parseTagsSet(Collection<String> tags) throws ParseException {
        assert tags != null && !tags.isEmpty();

        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return ParserUtil.parseTags(tagSet);
    }

    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[]{PREFIX_TAG};
    }

    @Override
    public String getUsageMessage() {
        return AddTagCommand.MESSAGE_USAGE;
    }

    @Override
    public EditStudentCommand<AddTagCommand.AddTagCommandStudentEditor> getIndexCommand(
            ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator)
            throws ParseException {

        Set<Tag> tags;
        tags = parseTagsSet(argMultimap.getAllValues(PREFIX_TAG));

        AddTagCommand.AddTagCommandStudentEditor editor = new AddTagCommand.AddTagCommandStudentEditor();
        editor.setTags(tags);

        return new AddTagCommand(indexListGenerator, editor);

    }
}
