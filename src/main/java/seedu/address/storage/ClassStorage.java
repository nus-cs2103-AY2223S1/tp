package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.person.Person;

/**
 * Manages storage of AddressBook class data.
 */
public class ClassStorage {

    private static HashMap<LocalDate, List<Person>> classes;
    private static AddressBook addressBook;

    /**
     * Constructs a {@code ClassStorage} with the given model.
     *
     * @param model Model object.
     */
    public ClassStorage(Model model) {
        this.addressBook = (AddressBook) model.getAddressBook();
        this.classes = initialiseClass();
    }

    /**
     * Initialises HashMap classes field.
     *
     * @return HashMap object.
     */
    public HashMap<LocalDate, List<Person>> initialiseClass() {
        HashMap<LocalDate, List<Person>> map = new HashMap<>();
        ObservableList<Person> listOfPersons = addressBook.getPersonList();
        for (Person person : listOfPersons) {
            Class classOfPerson = person.getAClass();
            if (!classOfPerson.classDateTime.equals("")) {
                if (!map.containsKey(classOfPerson.date)) {
                    List<Person> newListOfPersons = new ArrayList<>();
                    newListOfPersons.add(person);
                    map.put(classOfPerson.date, newListOfPersons);
                } else {
                    map.get(classOfPerson.date).add(person);
                }
            }
        }
        return map;
    }

    /**
     * Saves added classes into storage if there is no conflict between the timings of the classes.
     *
     * @param editedPerson Person object.
     * @param indexOfEditedPerson One-based index of the person in the list.
     * @throws CommandException if there is a conflict between the timings of the classes.
     */
    public static void saveClass(Person editedPerson, int indexOfEditedPerson) throws CommandException {
        LocalDate date = editedPerson.getAClass().date;
        LocalTime start = editedPerson.getAClass().startTime;
        LocalTime end = editedPerson.getAClass().endTime;
        if (!classes.containsKey(date)) {
            List<Person> newListOfPersons = new ArrayList<>();
            newListOfPersons.add(editedPerson);
            classes.put(date, newListOfPersons);
        } else {
            // Gets the list of person who have classes with same date
            List<Person> listOfPerson = classes.get(date);
            for (Person currPerson : listOfPerson) {
                LocalTime startOfCurrClass = currPerson.getAClass().startTime;
                LocalTime endOfCurrClass = currPerson.getAClass().endTime;
                if (hasConflict(start, end, startOfCurrClass, endOfCurrClass)
                        && indexOfEditedPerson != getIndex(currPerson)) {
                    throw new CommandException(EditCommand.MESSAGE_CLASS_CONFLICT);
                }
            }
            listOfPerson.add(editedPerson);
        }
    }

    /**
     * Checks if there is a conflict between class timings.
     *
     * @param start LocalTime object.
     * @param end LocalTime object.
     * @param startOfCurrClass LocalTime object.
     * @param endOfCurrClass LocalTime object.
     * @return True if there is a conflict.
     */
    public static boolean hasConflict(LocalTime start, LocalTime end, LocalTime startOfCurrClass,
                                      LocalTime endOfCurrClass) {
        if (start == null || end == null || startOfCurrClass == null || endOfCurrClass == null) {
            return false;
        }

        return start.equals(startOfCurrClass) || end.equals(endOfCurrClass)
                || start.isAfter(startOfCurrClass) && start.isBefore(endOfCurrClass)
                || start.isBefore(startOfCurrClass) && end.isAfter(startOfCurrClass);
    }

    /**
     * Removes the existing class from storage.
     * This frees up the class slot for other students to take.
     *
     * @param personToEdit Person object.
     */
    public static void removeExistingClass(Person personToEdit) {
        if (!personToEdit.getAClass().classDateTime.equals("")) {
            LocalDate date = personToEdit.getAClass().date;
            // Removes the pre-existing class from storage to prevent future conflicts
            ClassStorage.classes.get(date).remove(personToEdit);
        }
    }

    /**
     * Returns the index of person in the list.
     *
     * @param person Person object.
     * @return int.
     */
    public static int getIndex(Person person) {
        for (int i = 0; i < addressBook.getInternalList().size(); i++) {
            if (addressBook.getInternalList().get(i).isSamePerson(person)) {
                return i + 1;
            }
        }
        return 0;
    }
}
