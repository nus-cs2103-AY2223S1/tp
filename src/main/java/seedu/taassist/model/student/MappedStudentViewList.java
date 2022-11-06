package seedu.taassist.model.student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/*
 * Adapted from James_D's answer on Stack Overflow, see
 * https://stackoverflow.com/a/32297235.
 */

/**
 * An {@code ObservableList} implementation that maps {@code Student}-s to {@code StudentView}-s. The list
 * stores the parameter of the target session to be queried for each {@code Student} within the source list.
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

    /**
     * Sets the target {@code Session} to be queried for each {@code Student} within the list.
     *
     * @param targetClass The {@code ModuleClass} that contains the {@code targetSession}.
     * @param targetSession The target {@code Session}.
     */
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

    /**
     * Returns a {@code StudentView} instance that queries the current target {@code Session} if it exists.
     *
     * @param student Student of interest.
     * @return A {@code StudentView} instance that queries the targeted {@code Session}.
     */
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

    /**
     * The {@code Change} fired to any listeners of this list if the source list is modified.
     */
    private class Change extends ListChangeListener.Change<StudentView> {
        private final ListChangeListener.Change<? extends Student> change;

        /**
         * Constructs a {@code Change} instance with the provided {@code sourceChange}.
         *
         * @param sourceChange A {@code ListChangeListener.Change} from the source list.
         */
        public Change(ListChangeListener.Change<? extends Student> sourceChange) {
            super(MappedStudentViewList.this);
            change = sourceChange;
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
        protected int[] getPermutation() {
            // This method is only called by the superclass methods
            // wasPermutated() and getPermutation(int), which are
            // both overriden by this class. There is no other way
            // this method can be called.
            throw new AssertionError("Unreachable code");
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
                    .map(MappedStudentViewList.this::getStudentView)
                    .collect(Collectors.toList());
        }
    }

    /**
     * The {@code Change} fired to any listeners of this list if a new target was set through the
     * {@code MappedStudentViewList#setTarget} method. This {@code Change} states that the whole
     * list's elements have been updated.
     */
    private class FullUpdateChange extends ListChangeListener.Change<StudentView> {

        /**
         * Constructs a {@code FullUpdateChange} instance.
         */
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
