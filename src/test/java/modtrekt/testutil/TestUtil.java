package modtrekt.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import modtrekt.commons.core.index.Index;
import modtrekt.model.Model;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the task in the {@code model}'s task list.
     */
    public static Index getMidIndexTasks(Model model) {
        return Index.fromOneBased(model.getFilteredTaskList().size() / 2);
    }

    /**
     * Returns the middle index of the task in the {@code model}'s module list.
     */
    public static Index getMidIndexModules(Model model) {
        return Index.fromOneBased(model.getFilteredModuleList().size() / 2);
    }
    /**
     * Returns the last index of the task in the {@code model}'s task list.
     */
    public static Index getLastIndexTasks(Model model) {
        return Index.fromOneBased(model.getFilteredTaskList().size());
    }

    /**
     * Returns the last index of the task in the {@code model}'s module list.
     */
    public static Index getLastIndexModules(Model model) {
        return Index.fromOneBased(model.getFilteredModuleList().size());
    }

    /**
     * Returns the module in the {@code model}'s module list at {@code index}.
     */
    public static Task getTask(Model model, Index index) {
        return model.getFilteredTaskList().get(index.getZeroBased());
    }

    /**
     * Returns the module in the {@code model}'s module list at {@code index}.
     */
    public static Module getModule(Model model, Index index) {
        return model.getFilteredModuleList().get(index.getZeroBased());
    }
}
