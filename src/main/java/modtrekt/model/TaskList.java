//package modtrekt.model;
//
//import static java.util.Objects.requireNonNull;
//
//import java.util.List;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import modtrekt.model.person.Person;
//import modtrekt.model.task.InternalTaskList;
//import modtrekt.model.task.Task;
//
//public class TaskList implements ReadOnlyTaskList{
//    private final InternalTaskList tasks;
//
//    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
//    private final ObservableList<Task> internalUnmodifiableList =
//            FXCollections.unmodifiableObservableList(internalList);
//
//    /*
//     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
//     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
//     *
//     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
//     *   among constructors.
//     */
//    {
//        tasks = new InternalTaskList();
//    }
//
//    public TaskList() {}
//
//    /**
//     * Creates an AddressBook using the Persons in the {@code toBeCopied}
//     */
//    public TaskList(ReadOnlyTaskList toBeCopied) {
//        this();
//        resetData(toBeCopied);
//    }
//
//    //// list overwrite operations
//
//    /**
//     * Replaces the contents of the person list with {@code persons}.
//     * {@code persons} must not contain duplicate persons.
//     */
//    public void setTasks(List<Task> tasks) {
//        this.tasks.setTasks(tasks);
//    }
//
//    /**
//     * Resets the existing data of this {@code AddressBook} with {@code newData}.
//     */
//    public void resetData(ReadOnlyTaskList newData) {
//        requireNonNull(newData);
//
//        setTasks(newData.getTaskList());
//    }
//
//    /**
//     * Adds a person to the address book.
//     * The person must not already exist in the address book.
//     */
//    public void addTask(Task t) {
//        tasks.add(t);
//    }
//
////    /**
////     * Replaces the given person {@code target} in the list with {@code editedPerson}.
////     * {@code target} must exist in the address book.
////     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
////     */
////    public void setPerson(Person target, Person editedPerson) {
////        requireNonNull(editedPerson);
////
////        persons.setPerson(target, editedPerson);
////    }
//
//    /**
//     * Removes {@code key} from this {@code AddressBook}.
//     * {@code key} must exist in the address book.
//     */
//    public void removeTask(Task key) {
//        tasks.remove(key);
//    }
//
//    //// util methods
//
////    @Override
////    public String toString() {
////        return tasks.asUnmodifiableObservableList().size() + " persons";
////        // TODO: refine later
////    }
////
////    @Override
////    public ObservableList<Task> getPersonList() {
////        return tasks.asUnmodifiableObservableList();
////    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof TaskList // instanceof handles nulls
//                && tasks.equals(((TaskList) other).tasks));
//    }
//
//    @Override
//    public int hashCode() {
//        return tasks.hashCode();
//    }
//
//    @Override
//    public ObservableList<Task> getTaskList() {
//        return null;
//    }
//}
