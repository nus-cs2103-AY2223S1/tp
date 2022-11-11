package seedu.address.testutil;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SwapTaskNumbersCommand.SwapTaskNumbersDescriptor;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building {@code SwapTaskNumbersDescriptor} objects.
 */
public class SwapTaskNumbersDescriptorBuilder {
    public static final Index TASK_INDEX_ONE = Index.fromOneBased(1);
    public static final Index TASK_INDEX_TWO = Index.fromOneBased(2);

    private final SwapTaskNumbersDescriptor descriptor;

    /**
     * Returns a {@code SwapTaskNumbersDescriptor} with the given
     * {@code module} as the target module.
     */
    public SwapTaskNumbersDescriptorBuilder(Module module) {
        descriptor = new SwapTaskNumbersDescriptor();
        descriptor.setModuleCodeOfModuleToSwapTasks(module.getModuleCode());
        descriptor.setIndexesOfTaskToSwap(Arrays.asList(TASK_INDEX_ONE, TASK_INDEX_TWO));
    }

    /**
     * Returns a {@code SwapTaskNumbersDescriptor} with the given {@code module}
     * as the target module and the specified task numbers as the {@code Task}s
     * to swap.
     */
    public SwapTaskNumbersDescriptorBuilder(Module module,
             Index firstTaskNumber, Index secondTaskNumber) {
        descriptor = new SwapTaskNumbersDescriptor();
        descriptor.setModuleCodeOfModuleToSwapTasks(module.getModuleCode());
        descriptor.setIndexesOfTaskToSwap(Arrays.asList(firstTaskNumber, secondTaskNumber));
    }

    public SwapTaskNumbersDescriptor build() {
        return descriptor;
    }
}
