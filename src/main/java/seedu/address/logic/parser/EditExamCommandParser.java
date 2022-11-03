package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EXAM_INDEX;
import static seedu.address.logic.commands.EditExamCommand.MESSAGE_NO_FIELDS_PROVIDED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.ParserUtil.parseIndex;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
/**
 * Parses input arguments and creates a new EditExamCommand object
 */
public class EditExamCommandParser implements Parser<EditExamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditExamCommand
     * and returns an EditExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditExamCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_EXAM_DESCRIPTION, PREFIX_EXAM_DATE);

        Index index;

        try {
            Integer.parseInt(argMultimap.getPreamble());
        } catch (NumberFormatException ne) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditExamCommand.MESSAGE_USAGE));
        }

        try {
            index = parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_EXAM_INDEX);
        }

        EditExamCommand.EditExamDescriptor editExamDescriptor = new EditExamCommand.EditExamDescriptor();
        if (argMultimap.getValue(PREFIX_EXAM_DESCRIPTION).isPresent()) {
            editExamDescriptor.setDescription(ParserUtil
                    .parseExamDescription(argMultimap.getValue(PREFIX_EXAM_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            editExamDescriptor.setModule(new Module(ParserUtil
                    .parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get())));
        }
        if (argMultimap.getValue(PREFIX_EXAM_DATE).isPresent()) {
            editExamDescriptor.setExamDate(ParserUtil
                    .parseExamDate(argMultimap.getValue(PREFIX_EXAM_DATE).get()));
        }

        if (!editExamDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NO_FIELDS_PROVIDED);
        }

        return new EditExamCommand(index, editExamDescriptor);
    }

}
