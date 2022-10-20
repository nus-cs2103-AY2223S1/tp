package seedu.studmap.logic.parser;

import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.EditCommand;
import seedu.studmap.logic.commands.EditCommand.EditCommandStudentEditor;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser extends EditStudentCommandParser<EditCommandStudentEditor> {

    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[]{PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG};
    }

    @Override
    public String getUsageMessage() {
        return EditCommand.MESSAGE_USAGE;
    }

    @Override
    public EditStudentCommand<EditCommandStudentEditor> getIndexCommand(
            ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator) throws ParseException {

        EditCommand.EditCommandStudentEditor editor = new EditCommand.EditCommandStudentEditor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editor::setTags);

        return new EditCommand(indexListGenerator, editor);
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
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
