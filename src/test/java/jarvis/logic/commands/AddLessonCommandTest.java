package jarvis.logic.commands;

import static jarvis.testutil.TypicalLessons.DT1;
import static jarvis.testutil.TypicalLessons.DT2;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import jarvis.commons.core.GuiSettings;
import jarvis.commons.core.index.Index;
import jarvis.model.Lesson;
import jarvis.model.LessonBook;
import jarvis.model.Model;
import jarvis.model.ReadOnlyLessonBook;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.ReadOnlyTaskBook;
import jarvis.model.ReadOnlyUserPrefs;
import jarvis.model.Student;
import jarvis.model.StudentBook;
import jarvis.model.Task;
import jarvis.model.TimePeriod;
import jarvis.testutil.TypicalStudents;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public abstract class AddLessonCommandTest {

    public static final TimePeriod VALID_TIME_PERIOD = new TimePeriod(DT1, DT2);
    public static final Set<Index> VALID_STUDENT_INDEX = getFirstStudentIndex();

    public static Set<Index> getFirstStudentIndex() {
        Set<Index> studentIndex = new HashSet<>();
        studentIndex.add(Index.fromOneBased(Integer.parseInt("1")));
        return studentIndex;
    }

    public List<Student> getStudentsInLesson() {
        StudentBook studentBook = TypicalStudents.getTypicalStudentBook();
        List<Student> studentList = new FilteredList<>(studentBook.getStudentList());
        List<Student> studentsInLesson = new ArrayList<>();
        studentsInLesson.add(studentList.get(0)); // VALID_STUDENT_INDEX only gets first student
        return studentsInLesson;
    }

    /**
     * A default model stub that have all methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getStudentBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudentBookFilePath(Path studentBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudentBook(ReadOnlyStudentBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyStudentBook getStudentBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaskBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskBookFilePath(Path taskBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskBook(ReadOnlyTaskBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskBook getTaskBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getLessonBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLessonBookFilePath(Path lessonBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLessonBook(ReadOnlyLessonBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLessonBook getLessonBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLesson(Lesson target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLesson(Lesson target, Lesson editedLesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getFilteredLessonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLessonList(Predicate<Lesson> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPeriodClash(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Lesson and a sample StudentBook.
     */
    public class ModelStubWithLesson extends AddLessonCommandTest.ModelStub {
        private final Lesson lesson;
        private final StudentBook studentBook = TypicalStudents.getTypicalStudentBook();
        private final FilteredList<Student> filteredStudents = new FilteredList<>(studentBook.getStudentList());

        ModelStubWithLesson(Lesson lesson) {
            requireNonNull(lesson);
            this.lesson = lesson;
        }

        @Override
        public boolean hasLesson(Lesson lesson) {
            requireNonNull(lesson);
            return this.lesson.equals(lesson);
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return filteredStudents;
        }

        @Override
        public ObservableList<Lesson> getFilteredLessonList() {
            List<Lesson> lessonList = new ArrayList<>();
            lessonList.add(lesson);
            return new FilteredList<Lesson>(FXCollections.observableList(lessonList));
        }

        // used in add lesson command to highlight lessons in Ui that have conflict in schedule, not used for test
        @Override
        public void setLesson(Lesson targetLesson, Lesson editedLesson) {
            return;
        }

        //used in add lesson commands to highlight lessons in Ui that have conflict in schedule, not used for the test
        @Override
        public void updateFilteredLessonList(Predicate<Lesson> predicate) {
            return;
        }

        // used in AddStudioCommand to get all students in student list, not needed in test as student list unfiltered
        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            return;
        }

        @Override
        public boolean hasPeriodClash(Lesson lessonToAdd) {
            return lesson.hasTimingConflict(lessonToAdd);
        }
    }

    /**
     * A Model stub that contains a sample StudentBook and always accept the lesson being added.
     */
    public class ModelStubAcceptingLessonAdded extends AddLessonCommandTest.ModelStub {
        final ArrayList<Lesson> lessonsAdded = new ArrayList<>();
        private final StudentBook studentBook = TypicalStudents.getTypicalStudentBook();
        private final FilteredList<Student> filteredStudents = new FilteredList<>(studentBook.getStudentList());

        @Override
        public boolean hasLesson(Lesson lesson) {
            requireNonNull(lesson);
            return lessonsAdded.stream().anyMatch(lesson::equals);
        }

        @Override
        public void addLesson(Lesson lesson) {
            requireNonNull(lesson);
            lessonsAdded.add(lesson);
        }

        @Override
        public ReadOnlyLessonBook getLessonBook() {
            return new LessonBook();
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return filteredStudents;
        }

        @Override
        public ObservableList<Lesson> getFilteredLessonList() {
            return null;
        }

        // used in AddStudioCommand to get all students in student list, not needed in test as student list unfiltered
        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            return;
        }

        // used in add lesson command to highlight lessons in Ui that have conflict in schedule, not used for the test
        @Override
        public void setLesson(Lesson targetLesson, Lesson editedLesson) {
            return;
        }

        //used in add lesson commands to highlight lessons in Ui that have conflict in schedule, not used for the test
        @Override
        public void updateFilteredLessonList(Predicate<Lesson> predicate) {
            return;
        }

        @Override
        public boolean hasPeriodClash(Lesson lesson) {
            return false;
        }
    }
}
