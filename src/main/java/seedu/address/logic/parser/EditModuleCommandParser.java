package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;
import static seedu.address.logic.commands.EditModuleCommand.MESSAGE_NO_FIELDS_PROVIDED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;

/**
 * Parses input arguments and creates a new EditModuleCommand object.
 */
public class EditModuleCommandParser implements Parser<EditModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditModuleCommand
     * and returns an EditModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MOD_CODE, PREFIX_MOD_NAME, PREFIX_MOD_CREDIT);

        try {
            Integer.parseInt(argMultimap.getPreamble());
        } catch (NumberFormatException ne) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        EditModuleCommand.EditModuleDescriptor editModuleDescriptor = new EditModuleCommand.EditModuleDescriptor();

        if (argMultimap.getValue(PREFIX_MOD_CODE).isPresent()) {
            ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MOD_CODE).get());
            editModuleDescriptor.setModuleCode(moduleCode);
        }

        if (argMultimap.getValue(PREFIX_MOD_NAME).isPresent()) {
            ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_MOD_NAME).get());
            editModuleDescriptor.setModuleName(moduleName);
        }

        if (argMultimap.getValue(PREFIX_MOD_CREDIT).isPresent()) {
            ModuleCredit moduleCredit = ParserUtil.parseModuleCredit(argMultimap.getValue(PREFIX_MOD_CREDIT).get());
            editModuleDescriptor.setModuleCredit(moduleCredit);
        }

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NO_FIELDS_PROVIDED);
        }
        return new EditModuleCommand(index, editModuleDescriptor);
    }

}

