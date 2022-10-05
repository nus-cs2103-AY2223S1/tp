//package modtrekt.model.task;
//
//import static java.util.Objects.requireNonNull;
//import static modtrekt.commons.util.CollectionUtil.requireAllNonNull;
//
//import java.util.Iterator;
//import java.util.List;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import modtrekt.model.person.Person;
//import modtrekt.model.person.UniquePersonList;
//import modtrekt.model.person.exceptions.DuplicatePersonException;
//import modtrekt.model.person.exceptions.PersonNotFoundException;
//
//public class InternalTaskList implements Iterable<Task> {
//
//    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
//    private final ObservableList<Task> internalUnmodifiableList =
//            FXCollections.unmodifiableObservableList(internalList);
//
//    /**
//     * Returns true if the list contains an equivalent person as the given argument.
//     */
//    public boolean contains(Task toCheck) {
//        requireNonNull(toCheck);
//        return internalList.stream().anyMatch(toCheck::isSameTask);
//    }
//
//    /**
//     * Adds a person to the list.
//     * The person must not already exist in the list.
//     */
//    public void add(Task toAdd) {
//        requireNonNull(toAdd);
//        internalList.add(toAdd);
//    }
//
//    /**
//     * Removes the equivalent person from the list.
//     * The person must exist in the list.
//     */
//    public void remove(Task toRemove) {
//        requireNonNull(toRemove);
//        if (!internalList.remove(toRemove)) {
//            throw new PersonNotFoundException();
//        }
//    }
//    /**
//     * Replaces the contents of this list with {@code persons}.
//     * {@code persons} must not contain duplicate persons.
//     */
//    public void setTasks(List<Task> tasks) {
//        requireAllNonNull(tasks);
//        internalList.setAll(tasks);
//    }
//
//    @Override
//    public Iterator<Task> iterator() {
//        return internalList.iterator();
//    }
//}
