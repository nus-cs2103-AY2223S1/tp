package seedu.address.storage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
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
    public static final String MESSAGE_INITIALIZE_CLASS_STORAGE_FAILURE =
            "Failed to initialize class due to class conflict.";

    private static HashMap<LocalDate, List<Student>> classes = new HashMap<>();
    private static TeachersPet teachersPet;
    private static Model model;

    /**
     * Constructs a {@code ClassStorage} with the given model.
     *
     * @param model Model object.
     * @throws DataConversionException if class conflict detected while initializing the file.
     */
    public ClassStorage(Model model) throws DataConversionException {
        this.model = model;
        this.teachersPet = (TeachersPet) model.getTeachersPet();
        try {
            this.classes = initialiseClass();
        } catch (IOException e) {
            throw new DataConversionException(e);
        }
    }

    /**
     * Gets the classes.
     *
     * @return HashMap with Key: LocalDate objects and Value: List of Student objects.
     */
    public HashMap<LocalDate, List<Student>> getClasses() {
        return classes;
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
     * If there is class conflict in file, DataConversionException will be thrown.
     *
     * @return HashMap object.
     */
    public static HashMap<LocalDate, List<Student>> initialiseClass() throws IOException {
        HashMap<LocalDate, List<Student>> map = new HashMap<>();
        ObservableList<Student> listOfStudents = teachersPet.getStudentList();
        for (Student student : listOfStudents) {
            Class classOfStudent = student.getAClass();
            if (!classOfStudent.isEmpty()) {
                LocalDate date = classOfStudent.date;
                LocalTime start = classOfStudent.startTime;
                LocalTime end = classOfStudent.endTime;
                if (!map.containsKey(date)) {
                    addNewList(student, date, map);
                } else {
                    // Gets the list of students who have classes with same date
                    List<Student> list = map.get(date);
                    checkIfClassesHasConflict(list, start, end);
                    list.add(student);
                }
            }
        }
        return map;
    }

    /**
     * Checks if the initialized classes has conflict timings.
     *
     * @param list List of Student objects.
     * @param start LocalTime object. It stores the start time of the class being compared against.
     * @param end LocalTime object. It stores the end time of the class being compared against.
     * @throws CommandException if there is a conflict between the timings of the classes.
     */
    public static void checkIfClassesHasConflict(List<Student> list, LocalTime start, LocalTime end)
            throws IOException {
        for (Student currStudent : list) {
            Class currClass = currStudent.getAClass();
            LocalTime startOfCurrClass = currClass.startTime;
            LocalTime endOfCurrClass = currClass.endTime;
            if (hasConflict(start, end, startOfCurrClass, endOfCurrClass)) {
                throw new IOException(MESSAGE_INITIALIZE_CLASS_STORAGE_FAILURE);
            }
        }
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
            addNewList(editedStudent, date, classes);
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
     * Adds a new list into the hashmap classes.
     *
     * @param student Student object that will be added into the list.
     * @param date Key of the hashmap.
     * @param map Hashmap object.
     */
    public static void addNewList(Student student, LocalDate date, HashMap<LocalDate, List<Student>> map) {
        List<Student> newListOfStudents = new ArrayList<>();
        newListOfStudents.add(student);
        map.put(date, newListOfStudents);
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
    public static void refresh(Model model) throws IOException {
        ClassStorage.model = model;
        ClassStorage.teachersPet = (TeachersPet) model.getTeachersPet();
        ClassStorage.classes = ClassStorage.initialiseClass();
    }
}
