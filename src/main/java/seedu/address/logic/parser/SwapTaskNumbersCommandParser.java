package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NUMBERS_TO_SWAP;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SwapTaskNumbersCommand;
import seedu.address.logic.commands.SwapTaskNumbersCommand.SwapTaskNumbersDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new {@code SwapTaskNumbersCommand} object.
 */
public class SwapTaskNumbersCommandParser implements Parser<SwapTaskNumbersCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code SwapTaskNumbersCommand} and returns a {@code SwapTaskNumbersCommand}
     * object for execution.
     * @throws ParseException if the user input does not conform with the
     *                        expected format.
     */
    public SwapTaskNumbersCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_MODULE_CODE, PREFIX_TASK_NUMBERS_TO_SWAP);
        checkIfAllArgumentsArePresent(argMultimap);
        SwapTaskNumbersDescriptor swapTaskNumbersDescriptor =
                new SwapTaskNumbersCommand.SwapTaskNumbersDescriptor();
        String moduleCodeOfModuleToSwapTasksAsString =
                argMultimap.getValue(PREFIX_MODULE_CODE).get();
        ModuleCode moduleCodeOfModuleToSwapTasks =
                ParserUtil.parseModuleCode(moduleCodeOfModuleToSwapTasksAsString);
        List<Index> taskIndexesToSwap = ParserUtil.parseTaskNumbersToSwap(
                argMultimap.getValue(PREFIX_TASK_NUMBERS_TO_SWAP).get());
        swapTaskNumbersDescriptor.setModuleCodeOfModuleToSwapTasks(moduleCodeOfModuleToSwapTasks);
        swapTaskNumbersDescriptor.setIndexesOfTaskToSwap(taskIndexesToSwap);
        return new SwapTaskNumbersCommand(swapTaskNumbersDescriptor);
    }

    /**
     * Checks if all arguments are present.
     * @param argMultimap {@code ArgumentMultimap} containing the arguments
     *                    given by the user.
     */
    private void checkIfAllArgumentsArePresent(ArgumentMultimap argMultimap) throws ParseException {
        Boolean isModuleCodeAbsent = !arePrefixesPresent(argMultimap,
                PREFIX_MODULE_CODE);
        Boolean isPreamblePresent = !argMultimap.getPreamble().isEmpty();
        Boolean areTaskNumbersToSwapAbsent = !arePrefixesPresent(argMultimap,
                PREFIX_TASK_NUMBERS_TO_SWAP);

        if (isModuleCodeAbsent || isPreamblePresent || areTaskNumbersToSwapAbsent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SwapTaskNumbersCommand.MESSAGE_USAGE));
        }
    }
}
