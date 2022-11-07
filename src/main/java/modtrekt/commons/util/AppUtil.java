package modtrekt.commons.util;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import modtrekt.MainApp;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.ReadOnlyTaskBook;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;

/**
 * A container for App specific utility functions
 */
public class AppUtil {

    /**
     * Gets an {@code Image} from the specified path.
     */
    public static Image getImage(String imagePath) {
        requireNonNull(imagePath);
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to methods.
     *
     * @throws IllegalArgumentException if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to methods.
     *
     * @throws IllegalArgumentException with {@code errorMessage} if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Checks if any task in {@code tasks} contains a ModCode that does not correspond to any module in {@code modules}.
     */
    public static boolean containsValidModCodes(ReadOnlyTaskBook taskBook, ReadOnlyModuleList moduleList) {
        ObservableList<Task> tasks = taskBook.getTaskList();
        ObservableList<Module> modules = moduleList.getModuleList();
        long numberOfInvalidTasks = tasks.stream()
                        .filter(task -> !moduleListContainsModCode(modules, task.getModule()))
                        .count();
        return numberOfInvalidTasks == 0;
    }

    /**
     * Checks if any module in {@code modules} has the given {@code modCode}.
     */
    private static boolean moduleListContainsModCode(ObservableList<Module> modules, ModCode modCode) {
        return modules.stream().filter(module -> module.getCode().equals(modCode)).count() == 1;
    }
}
