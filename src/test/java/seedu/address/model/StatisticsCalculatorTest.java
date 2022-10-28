package seedu.address.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.AVA;
import static seedu.address.testutil.TypicalPersons.BEN;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class StatisticsCalculatorTest {

    private final TeachersPetStub teachersPetStub = new TeachersPetStub();
    private final StatisticsCalculator statisticsCalculator = new StatisticsCalculator(teachersPetStub);

    @Test
    public void calculates_emptyTeachersPet_size() {
        assertEquals(0, statisticsCalculator.getSize());
    }

    @Test
    public void calculates_emptyTeachersPet_moneyOwed() {
        assertEquals("$0", statisticsCalculator.getAmountOwed());
    }

    @Test
    public void calculates_emptyTeachersPet_moneyPaid() {
        assertEquals("$0", statisticsCalculator.getAmountPaid());
    }

    @Test
    public void calculates_filledTeachersPet_size() {
        List<Student> newStudents = Arrays.asList(AVA, BEN);
        StatisticsCalculatorTest.TeachersPetStub newData = new StatisticsCalculatorTest.TeachersPetStub(newStudents);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals(2, newCalculator.getSize());
    }

    @Test
    public void calculates_filledTeachersPet_moneyOwed() {
        List<Student> newStudents = Arrays.asList(AVA, BEN);
        StatisticsCalculatorTest.TeachersPetStub newData = new StatisticsCalculatorTest.TeachersPetStub(newStudents);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals("$80", newCalculator.getAmountOwed());
    }

    @Test
    public void calculates_filledTeachersPet_moneyPaid() {
        List<Student> newStudents = Arrays.asList(AVA, BEN);
        StatisticsCalculatorTest.TeachersPetStub newData = new StatisticsCalculatorTest.TeachersPetStub(newStudents);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals("$700", newCalculator.getAmountPaid());
    }

    @Test
    public void calculates_amountOwedOverflow() {
        // Edits Ava to have the maximum possible amount of money owed by a single student.
        Student editedAva = new StudentBuilder(AVA).withMoneyOwed(Integer.MAX_VALUE).build();
        Student editedBen = new StudentBuilder(BEN).withMoneyOwed(1).build();
        List<Student> newStudents = Arrays.asList(editedAva, editedBen);
        StatisticsCalculatorTest.TeachersPetStub newData = new StatisticsCalculatorTest.TeachersPetStub(newStudents);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals("Owed amount too large to calculate.", newCalculator.getAmountOwed());

    }

    @Test
    public void calculates_amountPaidOverflow() {
        // Edits Ava to have the maximum possible amount of money paid by a single student.
        Student editedAva = new StudentBuilder(AVA).withMoneyPaid(Integer.MAX_VALUE).build();
        Student editedBen = new StudentBuilder(BEN).withMoneyPaid(1).build();
        List<Student> newStudents = Arrays.asList(editedAva, editedBen);
        StatisticsCalculatorTest.TeachersPetStub newData = new StatisticsCalculatorTest.TeachersPetStub(newStudents);
        StatisticsCalculator newCalculator = new StatisticsCalculator(newData);

        assertEquals("Paid amount too large to calculate.", newCalculator.getAmountPaid());

    }

    /**
     * A stub ReadOnlyTeachersPet that contains a students list.
     */
    private static class TeachersPetStub implements ReadOnlyTeachersPet {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Student> schedule = FXCollections.observableArrayList();
        TeachersPetStub(Collection<Student> students) {
            this.students.setAll(students);
            this.schedule.setAll(students);
        }

        TeachersPetStub() {
            this(Collections.emptyList());
        }
        @Override
        public ObservableList<Student> getPersonList() {
            return students;
        }
        @Override
        public ObservableList<Student> getScheduleList() {
            return schedule;
        }
        @Override
        public void sortPersons(Comparator<Student> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
