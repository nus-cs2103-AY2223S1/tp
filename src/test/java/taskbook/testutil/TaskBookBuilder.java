package taskbook.testutil;

import taskbook.model.TaskBook;
import taskbook.model.person.Person;

/**
 * A utility class to help with building taskBook objects.
 * Example usage: <br>
 *     {@code TaskBook ab = new TaskBookBuilder().withPerson("John", "Doe").build();}
 */
public class TaskBookBuilder {

    private TaskBook taskBook;

    public TaskBookBuilder() {
        taskBook = new TaskBook();
    }

    public TaskBookBuilder(TaskBook taskBook) {
        this.taskBook = taskBook;
    }

    /**
     * Adds a new {@code Person} to the {@code TaskBook} that we are building.
     */
    public TaskBookBuilder withPerson(Person person) {
        taskBook.addPerson(person);
        return this;
    }

    public TaskBook build() {
        return taskBook;
    }
}
