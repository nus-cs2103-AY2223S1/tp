package seedu.classify.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.classify.commons.core.Messages;
import seedu.classify.commons.core.index.Index;
import seedu.classify.logic.commands.EditCommand;
import seedu.classify.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.exam.Exam;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_STUDENT_NAME, PREFIX_ID, PREFIX_CLASS, PREFIX_PARENT_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_EXAM);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_STUDENT_NAME).isPresent()) {
            editStudentDescriptor.setStudentName(ParserUtil.parseName(argMultimap
                    .getValue(CliSyntax.PREFIX_STUDENT_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ID).isPresent()) {
            editStudentDescriptor.setId(ParserUtil.parseId(argMultimap.getValue(CliSyntax.PREFIX_ID).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_CLASS).isPresent()) {
            editStudentDescriptor.setClassName(ParserUtil.parseClass(argMultimap
                    .getValue(CliSyntax.PREFIX_CLASS).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PARENT_NAME).isPresent()) {
            editStudentDescriptor.setParentName(ParserUtil.parseName(
                    argMultimap.getValue(CliSyntax.PREFIX_PARENT_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get()));
        }

        parseExamsForEdit(argMultimap.getAllValues(PREFIX_EXAM)).ifPresent(editStudentDescriptor::setExams);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor);
    }

    /**
     * Parses {@code Collection<String> exams} into a {@code Set<Exam>} if {@code exams} is non-empty.
     * If {@code exams} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Exam>} containing zero exams.
     */
    private Optional<Set<Exam>> parseExamsForEdit(Collection<String> exams) throws ParseException {
        assert exams != null;

        if (exams.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseExams(exams));
    }

}
