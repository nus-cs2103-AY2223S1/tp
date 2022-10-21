package seedu.address.model.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.exam.exceptions.ExamIdentityModifiedException;
import seedu.address.model.exam.exceptions.ExamNotFoundException;
import seedu.address.model.module.Module;





/**
 * This class represents a list which contains Exam objects which are distinct from
 * each other. Exam objects are distinct from each other when they have different module
 * codes and different description and different dates.
 */
public class DistinctExamList implements Iterable<Exam> {

    public final ObservableList<Exam> examList = FXCollections.observableArrayList();
    public final ObservableList<Exam> unmodifiableExamList = FXCollections
            .unmodifiableObservableList(examList);

    /**
     * Returns true if the exam list contains an equivalent exam as the given argument.
     */
    public boolean contains(Exam toCheck) {
        requireNonNull(toCheck);
        return examList.stream().anyMatch(toCheck::isSameExam);
    }

    /**
     * Returns true if the exam list contains an exam with an equivalent module as the given argument.
     */
    public boolean containsModule(Module toCheck) {
        requireNonNull(toCheck);

        return examList.stream().map(Exam::getModule).anyMatch(toCheck::isSameModule);
    }

    /**
     * Adds the exam to the examList.
     * The exam must not already exist in the list.
     *
     * @param examAdded The exam to be added.
     */
    public void addExam(Exam examAdded) {
        requireNonNull(examAdded);
        if (contains(examAdded)) {
            throw new DuplicateExamException();
        }
        examList.add(examAdded);
    }

    /**
     * Replaces the exam {@code target} in the exam list with {@code editedExam}.
     * {@code target} must exist in the exam list.
     * The exam identity of {@code editedExam} should be the same as exam identity of {@code target}.
     */
    public void setExam(Exam target, Exam editedExam) {
        requireAllNonNull(target, editedExam);

        int index = examList.indexOf(target);
        if (index == -1) {
            throw new ExamNotFoundException();
        }

        if (!target.isSameExam(editedExam)) {
            throw new ExamIdentityModifiedException();
        }

        examList.set(index, editedExam);
    }

    /**
     * Replaces the given exam {@code target} with {@code editedExam}.
     * {@code target} must exist in the exam list.
     *
     * @throws DuplicateExamException if task identity of {@code editedExam} is the same as another exam
     *     in the exam list (other than {@code target}).
     */
    public void replaceExam(Exam target, Exam editedExam) throws DuplicateExamException {
        requireAllNonNull(target, editedExam);

        int index = examList.indexOf(target);
        if (index == -1) {
            throw new ExamNotFoundException();
        }
        if (contains(editedExam) && !editedExam.isSameExam(target)) {
            throw new DuplicateExamException();
        }
        examList.set(index, editedExam);
    }

    /**
     * Removes the equivalent exam from the exam list.
     * The exam must exist in the list.
     */
    public void remove(Exam toRemove) {
        requireNonNull(toRemove);
        if (!examList.remove(toRemove)) {
            throw new ExamNotFoundException();
        }
    }

    @Override
    public Iterator<Exam> iterator() {
        return examList.iterator();
    }

    @Override
    public boolean equals(Object otherExam) {
        return otherExam == this
                || (otherExam instanceof DistinctExamList
                && examList.equals(((DistinctExamList) otherExam).examList));
    }

    public ObservableList<Exam> getUnmodifiableExamList() {
        return unmodifiableExamList;
    }

    public void setExams(List<Exam> exams) {
        requireNonNull(exams);
        examList.setAll(exams);
    }
}
