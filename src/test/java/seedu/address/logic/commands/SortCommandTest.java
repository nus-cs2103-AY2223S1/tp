package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        studentModel.sortList(Model.ListType.STUDENT_LIST, SortCommand.SortBy.ALPHA);
        studentList.sort(Comparator.comparing(student -> student.getName().fullName));
        assertEquals(studentList, studentModel.getFilteredStudentList());
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
        studentModel.sortList(Model.ListType.STUDENT_LIST, SortCommand.SortBy.REVERSE);
        Collections.reverse(studentList);
        assertEquals(studentList,studentModel.getFilteredStudentList());
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
        for (Student s : studentList) {
            studentModel.addPerson(s);
        }
        studentModel.sortList(Model.ListType.STUDENT_LIST, SortCommand.SortBy.ALPHA);
        studentModel.sortList(Model.ListType.STUDENT_LIST, SortCommand.SortBy.REVERSE);
        studentModel.sortList(Model.ListType.STUDENT_LIST, SortCommand.SortBy.DEFAULT);
        assertEquals(studentList, studentModel.getFilteredStudentList());
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
        for (Tutor t : tutorList) {
            tutorModel.addPerson(t);
        }
        tutorModel.sortList(Model.ListType.TUTOR_LIST, SortCommand.SortBy.ALPHA);
        tutorList.sort(Comparator.comparing(tutor -> tutor.getName().fullName));
        assertEquals(tutorList, tutorModel.getFilteredTutorList());
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
        for (Tutor s : tutorList) {
            tutorModel.addPerson(s);
        }
        tutorModel.sortList(Model.ListType.TUTOR_LIST, SortCommand.SortBy.REVERSE);
        Collections.reverse(tutorList);
        assertEquals(tutorList,tutorModel.getFilteredTutorList());
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
        for (Tutor t : tutorList) {
            tutorModel.addPerson(t);
        }
        tutorModel.sortList(Model.ListType.TUTOR_LIST, SortCommand.SortBy.ALPHA);
        tutorModel.sortList(Model.ListType.TUTOR_LIST, SortCommand.SortBy.REVERSE);
        tutorModel.sortList(Model.ListType.TUTOR_LIST, SortCommand.SortBy.DEFAULT);
        assertEquals(tutorList, tutorModel.getFilteredTutorList());
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
        for (TuitionClass tc : tuitionClassList) {
            tuitionClassModel.addTuitionClass(tc);
        }
        tuitionClassModel.sortList(Model.ListType.TUITIONCLASS_LIST, SortCommand.SortBy.ALPHA);
        tuitionClassList.sort(Comparator.comparing(tuitionClass -> tuitionClass.getName().name));
        assertEquals(tuitionClassList, tuitionClassModel.getFilteredTuitionClassList());
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
        for (TuitionClass tc : tuitionClassList) {
            tuitionClassModel.addTuitionClass(tc);
        }
        tuitionClassModel.sortList(Model.ListType.TUITIONCLASS_LIST, SortCommand.SortBy.REVERSE);
        Collections.reverse(tuitionClassList);
        assertEquals(tuitionClassList, tuitionClassModel.getFilteredTuitionClassList());
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
        for (TuitionClass tc : tuitionClassList) {
            tuitionClassModel.addTuitionClass(tc);
        }
        tuitionClassModel.sortList(Model.ListType.TUITIONCLASS_LIST, SortCommand.SortBy.ALPHA);
        tuitionClassModel.sortList(Model.ListType.TUITIONCLASS_LIST, SortCommand.SortBy.REVERSE);
        tuitionClassModel.sortList(Model.ListType.TUITIONCLASS_LIST, SortCommand.SortBy.DEFAULT);
        assertEquals(tuitionClassList, tuitionClassModel.getFilteredTuitionClassList());
    }
}
