package gim.logic.commands;

import static gim.logic.parser.CliSyntax.PREFIX_DATE;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_REPS;
import static gim.logic.parser.CliSyntax.PREFIX_SETS;
import static gim.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static gim.model.Model.PREDICATE_SHOW_ALL_EXERCISES;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import gim.commons.core.Messages;
import gim.commons.core.index.Index;
import gim.commons.util.CollectionUtil;
import gim.logic.commands.exceptions.CommandException;
import gim.model.Model;
import gim.model.date.Date;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;



/**
 * Edits the details of an existing exercise in the exercise tracker.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the exercise identified "
            + "by the index number used in the displayed exercise list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_WEIGHT + "WEIGHT] "
            + "[" + PREFIX_SETS + "SETS] "
            + "[" + PREFIX_REPS + "REP] "
            + "[" + PREFIX_DATE + "DATE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_WEIGHT + "91234567 "
            + PREFIX_SETS + "johndoe@example.com";

    public static final String MESSAGE_EDIT_EXERCISE_SUCCESS = "Edited Exercise: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the exercise tracker.";

    private final Index index;
    private final EditExerciseDescriptor editExerciseDescriptor;

    /**
     * @param index of the exercise in the filtered exercise list to edit
     * @param editExerciseDescriptor details to edit the exercise with
     */
    public EditCommand(Index index, EditExerciseDescriptor editExerciseDescriptor) {
        requireNonNull(index);
        requireNonNull(editExerciseDescriptor);

        this.index = index;
        this.editExerciseDescriptor = new EditExerciseDescriptor(editExerciseDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exercise> lastShownList = model.getFilteredExerciseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
        }

        Exercise exerciseToEdit = lastShownList.get(index.getZeroBased());
        Exercise editedExercise = createEditedExercise(exerciseToEdit, editExerciseDescriptor);

        //        if (!exerciseToEdit.isSameExercise(editedExercise) && model.hasExercise(editedExercise)) {
        //            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        //        }

        model.setExercise(exerciseToEdit, editedExercise);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        return new CommandResult(String.format(MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise));
    }

    /**
     * Creates and returns a {@code Exercise} with the details of {@code exerciseToEdit}
     * edited with {@code editExerciseDescriptor}.
     */
    private static Exercise createEditedExercise(Exercise exerciseToEdit, EditExerciseDescriptor
            editExerciseDescriptor) {
        assert exerciseToEdit != null;

        Name updatedName = editExerciseDescriptor.getName().orElse(exerciseToEdit.getName());
        Weight updatedWeight = editExerciseDescriptor.getWeight().orElse(exerciseToEdit.getWeight());
        Sets updatedSets = editExerciseDescriptor.getSets().orElse(exerciseToEdit.getSets());
        Reps updatedReps = editExerciseDescriptor.getReps().orElse(exerciseToEdit.getReps());
        Date updatedDate = editExerciseDescriptor.getDate().orElse(exerciseToEdit.getDate());

        return new Exercise(updatedName, updatedWeight, updatedSets, updatedReps, updatedDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editExerciseDescriptor.equals(e.editExerciseDescriptor);
    }

    /**
     * Stores the details to edit the exercise with. Each non-empty field value will replace the
     * corresponding field value of the exercise.
     */
    public static class EditExerciseDescriptor {
        private Name name;
        private Weight weight;
        private Sets sets;
        private Reps reps;
        private Date date;

        public EditExerciseDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code date} is used internally.
         */
        public EditExerciseDescriptor(EditExerciseDescriptor toCopy) {
            setName(toCopy.name);
            setWeight(toCopy.weight);
            setSets(toCopy.sets);
            setReps(toCopy.reps);
            setDate(toCopy.date);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, weight, sets, reps, date);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setWeight(Weight weight) {
            this.weight = weight;
        }

        public Optional<Weight> getWeight() {
            return Optional.ofNullable(weight);
        }

        public void setSets(Sets sets) {
            this.sets = sets;
        }

        public Optional<Sets> getSets() {
            return Optional.ofNullable(sets);
        }

        public void setReps(Reps reps) {
            this.reps = reps;
        }

        public Optional<Reps> getReps() {
            return Optional.ofNullable(reps);
        }

        /**
         * Sets {@code date} to this object's {@code date}.
         * A defensive copy of {@code date} is used internally.
         */
        public void setDate(Date date) {
            this.date = date;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code date} is null.
         */
        public Optional<Date> getDate() {
            return (date != null) ? Optional.of(date) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditExerciseDescriptor)) {
                return false;
            }

            // state check
            EditExerciseDescriptor e = (EditExerciseDescriptor) other;

            return getName().equals(e.getName())
                    && getWeight().equals(e.getWeight())
                    && getSets().equals(e.getSets())
                    && getReps().equals(e.getReps())
                    && getDate().equals(e.getDate());
        }
    }
}
