package seedu.studmap.logic.parser;

import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.DelTagCommand;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.tag.Tag;

/**
 * Parses input arguments and creates a new DelTagCommand object
 */
public class DelTagCommandParser extends EditStudentCommandParser<DelTagCommand.DelTagCommandStudentEditor> {

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contains only one element which is an empty string, it will be parsed into a
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
        return DelTagCommand.MESSAGE_USAGE;
    }

    @Override
    public EditStudentCommand<DelTagCommand.DelTagCommandStudentEditor> getIndexCommand(
            ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator)
            throws ParseException {

        Set<Tag> tags;
        tags = parseTagsSet(argMultimap.getAllValues(PREFIX_TAG));

        DelTagCommand.DelTagCommandStudentEditor editor = new DelTagCommand.DelTagCommandStudentEditor();
        editor.setTags(tags);

        return new DelTagCommand(indexListGenerator, editor);
    }
}
