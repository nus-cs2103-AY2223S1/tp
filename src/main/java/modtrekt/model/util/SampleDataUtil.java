package modtrekt.model.util;

import modtrekt.model.ModuleList;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.ReadOnlyTaskBook;
import modtrekt.model.TaskBook;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.model.module.ModTaskCount;
import modtrekt.model.module.Module;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * Contains utility methods for populating {@code ModuleList} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[]{
            new Module(new ModCode("CS2109S"), new ModName("Intro to AI and machine learning"), new ModCredit("4"),
                new ModTaskCount("2")),
            new Module(new ModCode("CS2040S"), new ModName("Data Structures and Algorithms"), new ModCredit("4"),
                    new ModTaskCount("2"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[]{
            new Task(new Description("Assignment 1"), new ModCode("CS2109S"), false, Task.Priority.MEDIUM),
            new Task(new Description("Attend lecture"), new ModCode("CS2040S"), false, Task.Priority.NONE),
            new Task(new Description("Attend exam"), new ModCode("CS2040S"), true, Task.Priority.LOW),
            new Task(new Description("Study for exam"), new ModCode("CS2109S"), false, Task.Priority.HIGH),
        };
    }

    public static ReadOnlyModuleList getSampleModuleList() {
        ModuleList sampleAb = new ModuleList();
        for (Module sampleModule : getSampleModules()) {
            sampleAb.addModule(sampleModule);
        }
        return sampleAb;
    }

    public static ReadOnlyTaskBook getSampleTaskBook() {
        TaskBook sampleAb = new TaskBook();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
    }
}
