package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANNEDMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUSMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVEMOD;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ModuleCommand;
import seedu.address.logic.commands.ModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.ModuleIndexCommand;
import seedu.address.logic.commands.ModuleUserCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Module;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;

/**
 * Parses input arguments and creates a new ModuleCommand object
 */
public class ModuleCommandParser implements Parser<ModuleCommand> {

    private boolean isEdited = false;

    /**
     * Parses the given {@code String} of arguments in the context of the ModuleCommand
     * and returns a ModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CURRENTMOD, PREFIX_PREVIOUSMOD, PREFIX_PLANNEDMOD,
                        PREFIX_REMOVEMOD);

        String preamble = argMultimap.getPreamble();
        isEdited = false;

        if (preamble.equals("user")) {
            EditModuleDescriptor editUserDescriptor = new EditModuleDescriptor();

            parseCurrentModsForEdit(argMultimap.getAllValues(PREFIX_CURRENTMOD))
                    .ifPresent(editUserDescriptor::setCurrModules);
            parsePreviousModsForEdit(argMultimap.getAllValues(PREFIX_PREVIOUSMOD))
                    .ifPresent(editUserDescriptor::setPrevModules);
            parsePlannedModsForEdit(argMultimap.getAllValues(PREFIX_PLANNEDMOD))
                    .ifPresent(editUserDescriptor::setPlanModules);
            parseRemoveModsForEdit(argMultimap.getAllValues(PREFIX_REMOVEMOD))
                    .ifPresent(editUserDescriptor::setModulesToRemove);

            if (!isEdited) {
                throw new ParseException(ModuleCommand.MESSAGE_NOT_EDITED);
            }

            return new ModuleUserCommand(editUserDescriptor);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(preamble);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleCommand.MESSAGE_USAGE), pe);
        }

        EditModuleDescriptor editPersonDescriptor = new EditModuleDescriptor();

        parseCurrentModsForEdit(argMultimap.getAllValues(PREFIX_CURRENTMOD))
                .ifPresent(editPersonDescriptor::setCurrModules);
        parsePreviousModsForEdit(argMultimap.getAllValues(PREFIX_PREVIOUSMOD))
                .ifPresent(editPersonDescriptor::setPrevModules);
        parsePlannedModsForEdit(argMultimap.getAllValues(PREFIX_PLANNEDMOD))
                .ifPresent(editPersonDescriptor::setPlanModules);
        parseRemoveModsForEdit(argMultimap.getAllValues(PREFIX_REMOVEMOD))
                .ifPresent(editPersonDescriptor::setModulesToRemove);

        if (!isEdited) {
            throw new ParseException(ModuleCommand.MESSAGE_NOT_EDITED);
        }

        return new ModuleIndexCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> currMods} into a {@code Set<CurrentModule>}.
     */
    private Optional<Set<CurrentModule>> parseCurrentModsForEdit(Collection<String> currMods) throws ParseException {
        assert currMods != null;

        if (!currMods.isEmpty()) {
            isEdited = true;
        }
        Collection<String> currModSet = currMods;
        return Optional.of(ParserUtil.parseCurrentModules(currModSet));
    }

    /**
     * Parses {@code Collection<String> prevMods} into a {@code Set<PreviousModule>}.
     */
    private Optional<Set<PreviousModule>> parsePreviousModsForEdit(Collection<String> prevMods) throws ParseException {
        assert prevMods != null;

        if (!prevMods.isEmpty()) {
            isEdited = true;
        }
        Collection<String> prevModSet = prevMods;
        return Optional.of(ParserUtil.parsePreviousModules(prevModSet));
    }

    /**
     * Parses {@code Collection<String> planMods} into a {@code Set<CurrentModule>}.
     */
    private Optional<Set<PlannedModule>> parsePlannedModsForEdit(Collection<String> planMods) throws ParseException {
        assert planMods != null;

        if (!planMods.isEmpty()) {
            isEdited = true;
        }
        Collection<String> planModSet = planMods;
        return Optional.of(ParserUtil.parsePlannedModules(planModSet));
    }

    /**
     * Parses {@code Collection<String> modsToRemove} into a {@code Set<Module>}.
     */
    private Optional<Set<Module>> parseRemoveModsForEdit(Collection<String> modsToRemove) throws ParseException {
        assert modsToRemove != null;

        if (!modsToRemove.isEmpty()) {
            isEdited = true;
        }
        Collection<String> modsToRemoveSet = modsToRemove;
        return Optional.of(ParserUtil.parseModules(modsToRemoveSet));
    }
}
