package modtrekt.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import modtrekt.commons.core.index.Index;
import modtrekt.model.Model;
<<<<<<< HEAD
import modtrekt.model.task.Task;
=======
import modtrekt.model.module.Module;
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands

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
    public static Index getMidIndex(Model model) {
<<<<<<< HEAD
        return Index.fromOneBased(model.getFilteredTaskList().size() / 2);
=======
        return Index.fromOneBased(model.getFilteredModuleList().size() / 2);
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands
    }

    /**
     * Returns the last index of the task in the {@code model}'s task list.
     */
    public static Index getLastIndex(Model model) {
<<<<<<< HEAD
        return Index.fromOneBased(model.getFilteredTaskList().size());
=======
        return Index.fromOneBased(model.getFilteredModuleList().size());
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands
    }

    /**
     * Returns the person in the {@code model}'s person list at {@code index}.
     */
<<<<<<< HEAD
    public static Task getPerson(Model model, Index index) {
        return model.getFilteredTaskList().get(index.getZeroBased());
=======
    public static Module getModule(Model model, Index index) {
        return model.getFilteredModuleList().get(index.getZeroBased());
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands
    }
}
