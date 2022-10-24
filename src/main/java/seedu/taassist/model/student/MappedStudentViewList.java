package seedu.taassist.model.student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/**
 * Adapted from James_D's answer on Stack Overflow, see
 * https://stackoverflow.com/questions/32294055/mirror-one-observablelist-to-another.
 */
public class MappedStudentViewList extends TransformationList<StudentView, Student> {

    private ModuleClass targetClass = null;
    private Session targetSession = null;

    public MappedStudentViewList(ObservableList<? extends Student> source) {
        super(source);
    }

    @Override
    protected void sourceChanged(ListChangeListener.Change<? extends Student> c) {
        fireChange(new Change(c));
    }

    public void setTarget(ModuleClass targetClass, Session targetSession) {
        this.targetClass = targetClass;
        this.targetSession = targetSession;
        fireChange(new FullUpdateChange());
    }

    @Override
    public int getSourceIndex(int index) {
        return index;
    }

    @Override
    public int getViewIndex(int index) {
        return index;
    }

    @Override
    public StudentView get(int index) {
        return getStudentView(getSource().get(index));
    }

    private StudentView getStudentView(Student student) {
        StudentView studentView = new StudentView(student);
        if (targetClass == null || targetSession == null) {
            return studentView;
        }
        return studentView.withSession(targetClass, targetSession);
    }

    @Override
    public int size() {
        return getSource().size();
    }

    private class Change extends ListChangeListener.Change<StudentView> {
        ListChangeListener.Change<? extends Student> change;

        public Change(ListChangeListener.Change<? extends Student> c) {
            super(MappedStudentViewList.this);
            change = c;
        }

        @Override
        public boolean wasAdded() {
            return change.wasAdded();
        }

        @Override
        public boolean wasRemoved() {
            return change.wasRemoved();
        }

        @Override
        public boolean wasReplaced() {
            return change.wasReplaced();
        }

        @Override
        public boolean wasUpdated() {
            return change.wasUpdated();
        }

        @Override
        public boolean wasPermutated() {
            return change.wasPermutated();
        }

        @Override
        public int getPermutation(int i) {
            return change.getPermutation(i);
        }

        @Override
        public boolean next() {
            return change.next();
        }

        @Override
        public void reset() {
            change.reset();
        }

        @Override
        public int getFrom() {
            return change.getFrom();
        }

        @Override
        public int getTo() {
            return change.getTo();
        }

        @Override
        public List<StudentView> getRemoved() {
            return change.getRemoved().stream()
                    .map(student -> MappedStudentViewList.this.getStudentView(student))
                    .collect(Collectors.toList());
        }

        @Override
        protected int[] getPermutation() {
            // This method is only called by the superclass methods
            // wasPermutated() and getPermutation(int), which are
            // both overriden by this class. There is no other way
            // this method can be called.
            throw new AssertionError("Unreachable code");
        }
    }

    private class FullUpdateChange extends ListChangeListener.Change<StudentView> {

        public FullUpdateChange() {
            super(MappedStudentViewList.this);
        }

        @Override
        public boolean wasAdded() {
            return false;
        }

        @Override
        public boolean wasRemoved() {
            return false;
        }

        @Override
        public boolean wasReplaced() {
            return false;
        }

        @Override
        public boolean wasUpdated() {
            return true;
        }

        @Override
        public boolean wasPermutated() {
            return false;
        }

        @Override
        public boolean next() {
            return false;
        }

        @Override
        public void reset() {
        }

        @Override
        public int getFrom() {
            return 0;
        }

        @Override
        public int getTo() {
            return getList().size();
        }

        @Override
        public List<StudentView> getRemoved() {
            return new ArrayList<>();
        }

        @Override
        protected int[] getPermutation() {
            // This method is only called by the superclass methods
            // wasPermutated() and getPermutation(int), which are
            // both overriden by this class. There is no other way
            // this method can be called.
            throw new AssertionError("Unreachable code");
        }
    }
}
