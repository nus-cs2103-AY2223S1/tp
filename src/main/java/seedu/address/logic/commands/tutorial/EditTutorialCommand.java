package seedu.address.logic.commands.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UNCHANGED_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIALS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelType;
import seedu.address.model.commons.ModuleCode;
import seedu.address.model.commons.Venue;
import seedu.address.model.datetime.WeeklyTimeslot;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

/**
 * Edits the details of an existing tutorial in the address book.
 */
public class EditTutorialCommand extends Command {

    public static final String COMMAND_WORD = "edit tutorial";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the tutorial identified "
        + "by the index number used in the displayed tutorial list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_MODULE + "MODULE] "
        + "[" + PREFIX_VENUE + "VENUE] "
        + "[" + PREFIX_TIME + "TIMESLOT] "
        + "[" + PREFIX_DATE_DAY + "DAY]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_VENUE + "COM1 B1-01 "
        + PREFIX_TIME + "14:00-16:00 "
        + PREFIX_DATE_DAY + "2";

    public static final String MESSAGE_EDIT_TUTORIAL_SUCCESS = "Edited Tutorial: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This tutorial already exists in the address book.";
    public static final String MESSAGE_CLASH_TUTORIAL =
            "There exists a tutorial with overlapping timeslot in the ModQuik";

    public static final String MESSAGE_DATETIME_TUTORIAL = "Both new day and new timeslot must be given.";

    private final Index index;
    private final EditTutorialDescriptor editTutorialDescriptor;

    /**
     * @param index of the tutorial in the filtered tutorial list to edit
     * @param editTutorialDescriptor details to edit the tutorial with
     */
    public EditTutorialCommand(Index index, EditTutorialDescriptor editTutorialDescriptor) {
        requireNonNull(index);
        requireNonNull(editTutorialDescriptor);

        this.index = index;
        this.editTutorialDescriptor = new EditTutorialDescriptor(editTutorialDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutorial> lastShownList = model.getFilteredTutorialList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
        }

        Tutorial tutorialToEdit = lastShownList.get(index.getZeroBased());
        Tutorial editedTutorial = createEditedTutorial(tutorialToEdit, editTutorialDescriptor);

        if (!tutorialToEdit.isSameTutorial(editedTutorial) && model.hasTutorial(editedTutorial)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        if (model.hasClashingTutorialExcept(editedTutorial, tutorialToEdit)) {
            throw new CommandException(MESSAGE_CLASH_TUTORIAL);
        }

        model.setTutorial(tutorialToEdit, editedTutorial);
        model.updateFilteredTutorialList(PREDICATE_SHOW_ALL_TUTORIALS);
        return new CommandResult(String.format(MESSAGE_EDIT_TUTORIAL_SUCCESS, editedTutorial), ModelType.TUTORIAL);
    }

    /**
     * Creates and returns a {@code Tutorial} with the details of {@code tutorialToEdit}
     * edited with {@code editTutorialDescriptor}.
     */
    private static Tutorial createEditedTutorial(Tutorial tutorialToEdit,
                                                 EditTutorialDescriptor editTutorialDescriptor)
            throws CommandException {
        assert tutorialToEdit != null;

        TutorialName updatedName = editTutorialDescriptor.getName().orElse(tutorialToEdit.getName());
        ModuleCode updatedModule = editTutorialDescriptor.getModule().orElse(tutorialToEdit.getModule());
        Venue updatedVenue = editTutorialDescriptor.getVenue().orElse(tutorialToEdit.getVenue());
        WeeklyTimeslot updatedTimeslot = editTutorialDescriptor.getTimeslot()
                .orElse(tutorialToEdit.getTimeslot());

        Tutorial editedTutorial = new Tutorial(updatedName, updatedModule, updatedVenue, updatedTimeslot);

        if (editedTutorial.equals(tutorialToEdit)) {
            throw new CommandException(MESSAGE_UNCHANGED_FIELD);
        }
        return editedTutorial;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTutorialCommand)) {
            return false;
        }

        // state check
        EditTutorialCommand e = (EditTutorialCommand) other;
        return index.equals(e.index)
                && editTutorialDescriptor.equals(e.editTutorialDescriptor);
    }

    /**
     * Stores the details to edit the tutorial with. Each non-empty field value will replace the
     * corresponding field value of the tutorial.
     */
    public static class EditTutorialDescriptor {
        private TutorialName name;
        private ModuleCode module;
        private Venue venue;
        private WeeklyTimeslot timeslot;

        public EditTutorialDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTutorialDescriptor(EditTutorialDescriptor toCopy) {
            setName(toCopy.name);
            setModule(toCopy.module);
            setVenue(toCopy.venue);
            setTimeslot(toCopy.timeslot);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, module, venue, timeslot);
        }

        public void setName(TutorialName name) {
            this.name = name;
        }

        public Optional<TutorialName> getName() {
            return Optional.ofNullable(name);
        }

        public void setModule(ModuleCode module) {
            this.module = module;
        }

        public Optional<ModuleCode> getModule() {
            return Optional.ofNullable(module);
        }

        public void setVenue(Venue venue) {
            this.venue = venue;
        }

        public Optional<Venue> getVenue() {
            return Optional.ofNullable(venue);
        }

        public void setTimeslot(WeeklyTimeslot timeslot) {
            this.timeslot = timeslot;
        }

        public Optional<WeeklyTimeslot> getTimeslot() {
            return Optional.ofNullable(timeslot);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTutorialDescriptor)) {
                return false;
            }

            // state check
            EditTutorialDescriptor e = (EditTutorialDescriptor) other;

            return getName().equals(e.getName())
                    && getModule().equals(e.getModule())
                    && getVenue().equals(e.getVenue())
                    && getTimeslot().equals(e.getTimeslot());
        }

    }
}
