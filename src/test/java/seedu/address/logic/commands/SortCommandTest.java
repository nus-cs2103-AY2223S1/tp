package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.ALPHA_SORT_SUCCESS;
import static seedu.address.logic.commands.SortCommand.DEFAULT_SORT_SUCCESS;
import static seedu.address.logic.commands.SortCommand.REVERSE_SORT_SUCCESS;
import static seedu.address.model.Model.ListType.TUITIONCLASS_LIST;
import static seedu.address.model.Model.ListType.TUTOR_LIST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

public class SortCommandTest {
    @Test
    public void executeForStudentList_sortAlpha() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new StudentBuilder().withName("Mary").build());
        studentList.add(new StudentBuilder().withName("Charlie").build());
        studentList.add(new StudentBuilder().withName("Sam").build());
        studentList.add(new StudentBuilder().withName("Wendy").build());
        studentList.add(new StudentBuilder().withName("Mike").build());

        Model studentModel = new ModelManager();
        for (Student s : studentList) {
            studentModel.addPerson(s);
        }
        studentList.sort(Comparator.comparing(student -> student.getName().fullName));
        SortCommand sortCommand = new SortCommand(SortCommand.SortBy.ALPHA);
        Model expectedStudentModel = new ModelManager();
        for (Student s : studentList) {
            expectedStudentModel.addPerson(s);
        }
        assertCommandSuccess(sortCommand, studentModel,
                String.format(SortCommand.MESSAGE_SUCCESS, ALPHA_SORT_SUCCESS), expectedStudentModel);
    }

    @Test
    public void executeForStudentList_sortReverse() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new StudentBuilder().withName("Mary").build());
        studentList.add(new StudentBuilder().withName("Charlie").build());
        studentList.add(new StudentBuilder().withName("Sam").build());
        studentList.add(new StudentBuilder().withName("Wendy").build());
        studentList.add(new StudentBuilder().withName("Mike").build());

        Model studentModel = new ModelManager();
        for (Student s : studentList) {
            studentModel.addPerson(s);
        }
        Collections.reverse(studentList);
        SortCommand sortCommand = new SortCommand(SortCommand.SortBy.REVERSE);
        Model expectedStudentModel = new ModelManager();
        for (Student s : studentList) {
            expectedStudentModel.addPerson(s);
        }
        assertCommandSuccess(sortCommand, studentModel,
                String.format(SortCommand.MESSAGE_SUCCESS, REVERSE_SORT_SUCCESS), expectedStudentModel);
    }

    @Test
    public void executeForStudentList_sortDefault() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new StudentBuilder().withName("Mary").build());
        studentList.add(new StudentBuilder().withName("Charlie").build());
        studentList.add(new StudentBuilder().withName("Sam").build());
        studentList.add(new StudentBuilder().withName("Wendy").build());
        studentList.add(new StudentBuilder().withName("Mike").build());

        Model studentModel = new ModelManager();
        Model expectedStudentModel = new ModelManager();
        for (Student s : studentList) {
            studentModel.addPerson(s);
            expectedStudentModel.addPerson(s);
        }
        SortCommand sortCommand = new SortCommand(SortCommand.SortBy.DEFAULT);
        assertCommandSuccess(sortCommand, studentModel,
                String.format(SortCommand.MESSAGE_SUCCESS, DEFAULT_SORT_SUCCESS), expectedStudentModel);
    }

    @Test
    public void executeForTutorList_sortAlpha() {
        List<Tutor> tutorList = new ArrayList<>();
        tutorList.add(new TutorBuilder().withName("Mary").build());
        tutorList.add(new TutorBuilder().withName("Charlie").build());
        tutorList.add(new TutorBuilder().withName("Sam").build());
        tutorList.add(new TutorBuilder().withName("Wendy").build());
        tutorList.add(new TutorBuilder().withName("Mike").build());

        Model tutorModel = new ModelManager();
        tutorModel.updateCurrentListType(TUTOR_LIST);
        for (Tutor t : tutorList) {
            tutorModel.addPerson(t);
        }
        tutorList.sort(Comparator.comparing(tutor -> tutor.getName().fullName));
        SortCommand sortCommand = new SortCommand(SortCommand.SortBy.ALPHA);
        Model expectedTutorModel = new ModelManager();
        expectedTutorModel.updateCurrentListType(TUTOR_LIST);
        for (Tutor t : tutorList) {
            expectedTutorModel.addPerson(t);
        }
        assertCommandSuccess(sortCommand, tutorModel,
                String.format(SortCommand.MESSAGE_SUCCESS, ALPHA_SORT_SUCCESS), expectedTutorModel);
    }

    @Test
    public void executeForTutorList_sortReverse() {
        List<Tutor> tutorList = new ArrayList<>();
        tutorList.add(new TutorBuilder().withName("Mary").build());
        tutorList.add(new TutorBuilder().withName("Charlie").build());
        tutorList.add(new TutorBuilder().withName("Sam").build());
        tutorList.add(new TutorBuilder().withName("Wendy").build());
        tutorList.add(new TutorBuilder().withName("Mike").build());

        Model tutorModel = new ModelManager();
        tutorModel.updateCurrentListType(TUTOR_LIST);
        for (Tutor t : tutorList) {
            tutorModel.addPerson(t);
        }
        Collections.reverse(tutorList);
        SortCommand sortCommand = new SortCommand(SortCommand.SortBy.REVERSE);
        Model expectedTutorModel = new ModelManager();
        expectedTutorModel.updateCurrentListType(TUTOR_LIST);
        for (Tutor t : tutorList) {
            expectedTutorModel.addPerson(t);
        }
        assertCommandSuccess(sortCommand, tutorModel,
                String.format(SortCommand.MESSAGE_SUCCESS, REVERSE_SORT_SUCCESS), expectedTutorModel);
    }

    @Test
    public void executeForTutorList_sortDefault() {
        List<Tutor> tutorList = new ArrayList<>();
        tutorList.add(new TutorBuilder().withName("Mary").build());
        tutorList.add(new TutorBuilder().withName("Charlie").build());
        tutorList.add(new TutorBuilder().withName("Sam").build());
        tutorList.add(new TutorBuilder().withName("Wendy").build());
        tutorList.add(new TutorBuilder().withName("Mike").build());

        Model tutorModel = new ModelManager();
        tutorModel.updateCurrentListType(TUTOR_LIST);
        Model expectedTutorModel = new ModelManager();
        expectedTutorModel.updateCurrentListType(TUTOR_LIST);
        for (Tutor t : tutorList) {
            tutorModel.addPerson(t);
            expectedTutorModel.addPerson(t);
        }
        SortCommand sortCommand = new SortCommand(SortCommand.SortBy.DEFAULT);
        assertCommandSuccess(sortCommand, tutorModel,
                String.format(SortCommand.MESSAGE_SUCCESS, DEFAULT_SORT_SUCCESS), expectedTutorModel);
    }

    @Test
    public void executeForTuitionClassList_sortAlpha() {
        List<TuitionClass> tuitionClassList = new ArrayList<>();
        tuitionClassList.add(new TuitionClassBuilder().withName("P5MATH").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P2SCIENCE").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P4ENG").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("SEC2GEOG").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P1ARTS").build());

        Model tuitionClassModel = new ModelManager();
        tuitionClassModel.updateCurrentListType(TUITIONCLASS_LIST);
        for (TuitionClass c : tuitionClassList) {
            tuitionClassModel.addTuitionClass(c);
        }
        tuitionClassList.sort(Comparator.comparing(tuitionClass -> tuitionClass.getName().name));
        SortCommand sortCommand = new SortCommand(SortCommand.SortBy.ALPHA);
        Model expectedTuitionClassModel = new ModelManager();
        expectedTuitionClassModel.updateCurrentListType(TUITIONCLASS_LIST);
        for (TuitionClass c : tuitionClassList) {
            expectedTuitionClassModel.addTuitionClass(c);
        }
        assertCommandSuccess(sortCommand, tuitionClassModel,
                String.format(SortCommand.MESSAGE_SUCCESS, ALPHA_SORT_SUCCESS), expectedTuitionClassModel);
    }

    @Test
    public void executeForTuitionClassList_sortReverse() {
        List<TuitionClass> tuitionClassList = new ArrayList<>();
        tuitionClassList.add(new TuitionClassBuilder().withName("P5MATH").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P2SCIENCE").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P4ENG").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("SEC2GEOG").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P1ARTS").build());

        Model tuitionClassModel = new ModelManager();
        tuitionClassModel.updateCurrentListType(TUITIONCLASS_LIST);
        for (TuitionClass c : tuitionClassList) {
            tuitionClassModel.addTuitionClass(c);
        }
        Collections.reverse(tuitionClassList);
        SortCommand sortCommand = new SortCommand(SortCommand.SortBy.REVERSE);
        Model expectedTuitionClassModel = new ModelManager();
        expectedTuitionClassModel.updateCurrentListType(TUITIONCLASS_LIST);
        for (TuitionClass c : tuitionClassList) {
            expectedTuitionClassModel.addTuitionClass(c);
        }
        assertCommandSuccess(sortCommand, tuitionClassModel,
                String.format(SortCommand.MESSAGE_SUCCESS, REVERSE_SORT_SUCCESS), expectedTuitionClassModel);
    }

    @Test
    public void executeForTuitionClassList_sortDefault() {
        List<TuitionClass> tuitionClassList = new ArrayList<>();
        tuitionClassList.add(new TuitionClassBuilder().withName("P5MATH").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P2SCIENCE").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P4ENG").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("SEC2GEOG").build());
        tuitionClassList.add(new TuitionClassBuilder().withName("P1ARTS").build());

        Model tuitionClassModel = new ModelManager();
        Model expectedTuitionClassModel = new ModelManager();
        tuitionClassModel.updateCurrentListType(TUITIONCLASS_LIST);
        expectedTuitionClassModel.updateCurrentListType(TUITIONCLASS_LIST);
        for (TuitionClass c : tuitionClassList) {
            tuitionClassModel.addTuitionClass(c);
            expectedTuitionClassModel.addTuitionClass(c);

        }
        SortCommand sortCommand = new SortCommand(SortCommand.SortBy.DEFAULT);
        assertCommandSuccess(sortCommand, tuitionClassModel,
                String.format(SortCommand.MESSAGE_SUCCESS, DEFAULT_SORT_SUCCESS), expectedTuitionClassModel);
    }
}
