package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand.DeleteTaskFromModuleDescriptor;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building {@code DeleteTaskFromModuleDescriptor} objects.
 */
public class DeleteTaskFromModuleDescriptorBuilder {
    public static final Index TASK_INDEX_ONE = Index.fromOneBased(1);

    private DeleteTaskFromModuleDescriptor descriptor;

    /**
     * Returns a {@code DeleteTaskFromModuleDescriptor} with the given
     * {@code module} as the target module.
     */
    public DeleteTaskFromModuleDescriptorBuilder(Module module) {
        descriptor = new DeleteTaskFromModuleDescriptor();
        descriptor.setModuleCodeOfModuleWithTaskToDelete(module.getModuleCode());
        descriptor.setIndexOfTaskToDelete(TASK_INDEX_ONE);
    }

    /**
     * Returns a {@code DeleteTaskFromModuleDescriptor} with the given
     * {@code module} as the target module and the specified task number as
     * the task to delete.
     */
    public DeleteTaskFromModuleDescriptorBuilder(Module module,
                                                 Index taskNumber) {
        descriptor = new DeleteTaskFromModuleDescriptor();
        descriptor.setModuleCodeOfModuleWithTaskToDelete(module.getModuleCode());
        descriptor.setIndexOfTaskToDelete(taskNumber);
    }

    public DeleteTaskFromModuleDescriptor build() {
        return descriptor;
    }
}
