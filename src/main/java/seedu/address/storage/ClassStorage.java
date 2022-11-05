package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TeachersPet;
import seedu.address.model.student.Class;
import seedu.address.model.student.Student;

/**
 * Manages storage of TeachersPet class data.
 */
public class ClassStorage {

    private static HashMap<LocalDate, List<Student>> classes;
    private static TeachersPet teachersPet;
    private static Model model;

    /**
     * Constructs a {@code ClassStorage} with the given model.
     *
     * @param model Model object.
     */
    public ClassStorage(Model model) {
        this.model = model;
        this.teachersPet = (TeachersPet) model.getTeachersPet();
        this.classes = initialiseClass();
    }

    /**
     * Updates the student in the List of Student in the HashMap classes.
     *
     * @param studentToEdit Student object without the edited fields.
     * @param editedStudent Student object with the edited fields.
     */
    public static void updateStudent(Student studentToEdit, Student editedStudent) {
        if (!studentToEdit.hasEmptyClass()) {
            classes.get(studentToEdit.getAClass().date).remove(studentToEdit);
            classes.get(studentToEdit.getAClass().date).add(editedStudent);
        }
    }

    /**
     * Gets a list of students who are attending classes on a particular date.
     *
     * @param date LocalDate object.
     * @return List of Student objects.
     */
    public static List<Student> getListOfStudent(LocalDate date) {
        return classes.get(date);
    }

    /**
     * Initialises HashMap classes field.
     *
     * @return HashMap object.
     */
    public static HashMap<LocalDate, List<Student>> initialiseClass() {
        HashMap<LocalDate, List<Student>> map = new HashMap<>();
        ObservableList<Student> listOfStudents = teachersPet.getStudentList();
        for (Student student : listOfStudents) {
            Class classOfStudent = student.getAClass();
            if (!classOfStudent.isEmpty()) {
                if (!map.containsKey(classOfStudent.date)) {
                    List<Student> newListOfStudents = new ArrayList<>();
                    newListOfStudents.add(student);
                    map.put(classOfStudent.date, newListOfStudents);
                } else {
                    map.get(classOfStudent.date).add(student);
                }
            }
        }
        return map;
    }

    /**
     * Saves added classes into storage if there is no conflict between the timings of the classes.
     *
     * @param editedStudent Student object.
     * @param indexOfEditedStudent One-based index of the student in the list.
     * @throws CommandException if there is a conflict between the timings of the classes.
     */
    public static void saveClass(Student editedStudent, int indexOfEditedStudent) throws CommandException {
        LocalDate date = editedStudent.getAClass().date;
        LocalTime start = editedStudent.getAClass().startTime;
        LocalTime end = editedStudent.getAClass().endTime;
        if (!classes.containsKey(date)) {
            List<Student> newListOfStudents = new ArrayList<>();
            newListOfStudents.add(editedStudent);
            classes.put(date, newListOfStudents);
        } else {
            // Gets the list of student who have classes with same date
            List<Student> listOfStudents = classes.get(date);
            for (Student currStudent : listOfStudents) {
                LocalTime startOfCurrClass = currStudent.getAClass().startTime;
                LocalTime endOfCurrClass = currStudent.getAClass().endTime;
                if (hasConflict(start, end, startOfCurrClass, endOfCurrClass)
                        && indexOfEditedStudent != getIndex(currStudent)) {
                    throw new CommandException(String.format("%s\n" + "%s currently has a class on %s.",
                            EditCommand.MESSAGE_CLASS_CONFLICT, currStudent.getName(), currStudent.getAClass()));
                }
            }
            listOfStudents.add(editedStudent);
        }
    }

    /**
     * Checks if there is a conflict between class timings.
     *
     * @param start LocalTime object.
     * @param end LocalTime object.
     * @param startOfCurrClass LocalTime object.
     * @param endOfCurrClass LocalTime object.
     * @return true if there is a conflict.
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
     * @param studentToEdit Student object.
     */
    public static void removeExistingClass(Student studentToEdit) {
        if (!studentToEdit.hasEmptyClass()) {
            LocalDate date = studentToEdit.getAClass().date;
            // Removes the pre-existing class from storage to prevent future conflicts
            ClassStorage.classes.get(date).remove(studentToEdit);
        }
    }

    /**
     * Returns the index of student in the current list shown on left UI panel.
     *
     * @param student Student object.
     * @return int.
     */
    public static int getIndex(Student student) {
        for (int i = 0; i < model.getFilteredStudentList().size(); i++) {
            if (model.getFilteredStudentList().get(i).isSameStudent(student)) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * Refreshes the class storage with the current in the given {@code model}.
     */
    public static void refresh(Model model) {
        ClassStorage.model = model;
        ClassStorage.teachersPet = (TeachersPet) model.getTeachersPet();
        ClassStorage.classes = ClassStorage.initialiseClass();
    }
}
