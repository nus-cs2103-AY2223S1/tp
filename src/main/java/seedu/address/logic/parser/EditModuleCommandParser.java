package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignmentdetails.AssignmentDetails;


/**
 * Parses input arguments and creates a new EditModuleCommand object
 */
public class EditModuleCommandParser implements Parser<EditModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditModuleCommand
     * and returns an EditModuleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer
                .tokenize(args, PREFIX_MODULE, PREFIX_LECTURE, PREFIX_TUTORIAL, PREFIX_ZOOM, PREFIX_ASSIGNMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE), pe);
        }

        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            editModuleDescriptor.setModuleCode(ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get()));
        }
        if (argMultimap.getValue(PREFIX_LECTURE).isPresent()) {
            editModuleDescriptor.setLecture(ParserUtil.parseLectureDetails(argMultimap.getValue(PREFIX_LECTURE).get()));
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            editModuleDescriptor.setTutorial(
                ParserUtil.parseTutorialDetails(argMultimap.getValue(PREFIX_TUTORIAL).get()));
        }
        if (argMultimap.getValue(PREFIX_ZOOM).isPresent()) {
            editModuleDescriptor.setZoomLink(ParserUtil.parseZoomLink(argMultimap.getValue(PREFIX_ZOOM).get()));
        }
        parseAssignmentsForEdit(argMultimap
            .getAllValues(PREFIX_ASSIGNMENT)).ifPresent(editModuleDescriptor::setAssignments);

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditModuleCommand.MESSAGE_NOT_EDITED);
        }

        return new EditModuleCommand(index, editModuleDescriptor);
    }

    /**
     * Parses {@code Collection<String> assignments} into a
     * {@code Set<AssignmentsDetails>} if {@code assignments} is non-empty.
     * If {@code assignments} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<AssignmentDetails>} containing zero assignments.
     */
    //TODO fix this checkstyle issue
    private Optional<
        Set<AssignmentDetails>> parseAssignmentsForEdit(Collection<String> assignments) throws ParseException {
        assert assignments != null;

        if (assignments.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> assignmentSet =
            assignments.size() == 1 && assignments.contains("") ? Collections.emptySet() : assignments;
        return Optional.of(ParserUtil.parseAssignmentDetails(assignmentSet));
    }

}
