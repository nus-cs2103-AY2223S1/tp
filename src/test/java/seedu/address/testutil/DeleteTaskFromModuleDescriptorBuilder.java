package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_A;
import static seedu.address.testutil.TypicalModules.CS2103T;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand.DeleteTaskFromModuleDescriptor;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building {@code DeleteTaskFromModuleDescriptor} objects.
 */
public class DeleteTaskFromModuleDescriptorBuilder {
    public static final Module DEFAULT_MODULE = CS2103T;
    public static final Index TASK_INDEX_ONE = Index.fromOneBased(1);

    private DeleteTaskFromModuleDescriptor descriptor;

    public DeleteTaskFromModuleDescriptorBuilder() {
        descriptor = new DeleteTaskFromModuleDescriptor();
    }

    public DeleteTaskFromModuleDescriptorBuilder(DeleteTaskFromModuleDescriptor descriptor) {
        this.descriptor = new DeleteTaskFromModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code DeleteTaskFromModuleDescriptor} with fields containing
     * {@code module}'s details.
     */
    public DeleteTaskFromModuleDescriptorBuilder(Module module) {
        descriptor = new DeleteTaskFromModuleDescriptor();
        descriptor.setModuleCodeOfModuleWithTaskToDelete(module.getModuleCode());
        descriptor.setIndexOfTaskToDelete(TASK_INDEX_ONE);
    }

    /**
     * Returns an {@code DeleteTaskFromModuleDescriptor} with fields containing
     * {@code module}'s details and the specified task number.
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
