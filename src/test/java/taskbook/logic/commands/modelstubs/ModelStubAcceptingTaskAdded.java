package taskbook.logic.commands.modelstubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.TaskBook;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.Task;

/**
 * A Model stub that always accept the person being added.
 */
public class ModelStubAcceptingTaskAdded extends ModelStub {
    final ArrayList<Person> personsAdded = new ArrayList<>();
    final ArrayList<Task> tasksAdded = new ArrayList<>();

    public ModelStubAcceptingTaskAdded(Person person) {
        this.personsAdded.add(person);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> getTasks() {
        return (ArrayList<Task>) this.tasksAdded.clone(); //clone list
    }

    @Override
    public void addTask(Task task) {
        tasksAdded.add(task);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personsAdded.stream().anyMatch(person::isSamePerson);
    }

    @Override
    public void addPerson(Person person) {
        requireNonNull(person);
        personsAdded.add(person);
    }

    @Override
    public Person findPerson(Name name) {
        requireNonNull(name);
        return personsAdded.stream().filter(person -> person.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return new TaskBook();
    }
}
