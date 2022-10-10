package seedu.address.storage;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassStorage {

    public static HashMap<LocalDate, List<Person>> classes = new HashMap<>();

    /**
     * Saves added classes into storage if there is no conflict between the timings of the classes.
     *
     * @param editedPerson Person object.
     * @throws CommandException if there is a conflict between the timings of the classes.
     */
    public static void saveClass(Person editedPerson) throws CommandException {
        LocalDate date = editedPerson.getAClass().date;
        LocalTime start = editedPerson.getAClass().startTime;
        LocalTime end = editedPerson.getAClass().endTime;
        if (!classes.containsKey(date)) {
            List<Person> ls = new ArrayList<>();
            ls.add(editedPerson);
            classes.put(date, ls);
        } else {
            // Gets the list of person who have classes with same date
            List<Person> listOfPerson = classes.get(date);
            for(int i = 0; i < listOfPerson.size(); i++) {
                Person currPerson = listOfPerson.get(i);
                LocalTime startOfCurrClass = currPerson.getAClass().startTime;
                LocalTime endOfCurrClass = currPerson.getAClass().endTime;
                if (hasConflict(start, end, startOfCurrClass, endOfCurrClass) && !currPerson.allEqualsExceptClass(editedPerson)) {
                    throw new CommandException(EditCommand.MESSAGE_CLASS_CONFLICT);
                }
            }
            listOfPerson.add(editedPerson);
        }
    }

    public static boolean hasConflict(LocalTime start, LocalTime end, LocalTime startOfCurrClass,
                                      LocalTime endOfCurrClass) {
        return start.equals(startOfCurrClass) || end.equals(endOfCurrClass) ||
                start.isAfter(startOfCurrClass) && start.isBefore(endOfCurrClass) ||
                start.isBefore(startOfCurrClass) && end.isAfter(startOfCurrClass);
    }

    public static void removeExistingClass(Person personToEdit) {
        if (!personToEdit.getAClass().classDateTime.equals("")) {
            LocalDate date = personToEdit.getAClass().date;
            LocalTime start = personToEdit.getAClass().startTime;
            LocalTime end = personToEdit.getAClass().endTime;
            // Removes the pre-existing class from storage to prevent future conflicts
            ClassStorage.classes.get(date).remove(personToEdit);
        }

    }
}
