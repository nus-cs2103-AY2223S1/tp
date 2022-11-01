package seedu.boba.model;

import seedu.boba.model.exceptions.NextStateNotFoundException;
import seedu.boba.model.exceptions.PreviousStateNotFoundException;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

/**
 * Keeps track of the current state of the BobaBot by storing multiple
 * versions of the BobaBot after each successful command.
 */
public class VersionedBobaBot extends BobaBot {
    // Keeps track of the current version of BobaBot
    private int currentStatePointer;

    // Maximum number of snapshots we take
    private int sizeLimit;

    // Stores the different versions of BobaBot
    private ArrayList<ReadOnlyBobaBot> bobaBotStateList = new ArrayList<>();

    /**
     * Creates an instance of VersionedBobaBot.
     *
     * @param bobaBot BobaBot to be initialised as the first state
     */
    public VersionedBobaBot(ReadOnlyBobaBot bobaBot) {
        assert bobaBot != null;
        this.currentStatePointer = 0;
        this.sizeLimit = 20; // Default value
        bobaBotStateList.add(this.currentStatePointer, bobaBot);
    }

    /**
     * Creates an instance of VersionedBobaBot.
     *
     * @param bobaBot   BobaBot to be initialised as the first state
     * @param stepLimit maximum number of previous states tracked
     */
    public VersionedBobaBot(ReadOnlyBobaBot bobaBot, int stepLimit) {
        assert bobaBot != null;
        this.currentStatePointer = 0;
        this.sizeLimit = stepLimit; // Default value
        bobaBotStateList.add(this.currentStatePointer, bobaBot);
    }

    private void trimStateList() {
        if (this.bobaBotStateList.size() <= this.sizeLimit) {
            return;
        }
        while (this.bobaBotStateList.size() > this.sizeLimit) {
            this.bobaBotStateList.remove(0);
            this.currentStatePointer--;
        }
    }

    /**
     * Saves the current BobaBot state in its history.
     *
     * @param bobaBot BobaBot to save
     */
    public void commit(BobaBot bobaBot) {
        requireNonNull(bobaBot);
        BobaBot copiedBobaBot = new BobaBot(bobaBot);
        if (!copiedBobaBot.strictlyEquals(this.bobaBotStateList.get(this.currentStatePointer))) {
            // prune away all previously stored redoable steps
            for (int i = currentStatePointer + 1; i < this.bobaBotStateList.size(); i++) {
                this.bobaBotStateList.remove(currentStatePointer + 1);
            }
            this.bobaBotStateList.add(copiedBobaBot);
            this.currentStatePointer++;
        }
        this.trimStateList();
    }

    /**
     * Restores the previous BobaBot state from its history.
     *
     * @param currentBobaBot BobaBot to set to the previous state
     * @throws PreviousStateNotFoundException if currentStatePointer is at the initialised state
     */
    public void undo(BobaBot currentBobaBot) throws PreviousStateNotFoundException {
        requireNonNull(currentBobaBot);

        if (this.currentStatePointer <= 0) {
            throw new PreviousStateNotFoundException();
        }
        this.currentStatePointer--;
        currentBobaBot.resetData(this.bobaBotStateList.get(this.currentStatePointer));
    }

    /**
     * Restores the previously undone BobaBot state from its history.
     *
     * @param currentBobaBot BobaBot to set to the before undone state
     * @throws NextStateNotFoundException if currentStatePointer is at the most updated state
     */
    public void redo(BobaBot currentBobaBot) throws NextStateNotFoundException {
        requireNonNull(currentBobaBot);

        int length = this.bobaBotStateList.size();
        if (this.currentStatePointer + 1 >= length) {
            throw new NextStateNotFoundException();
        }
        this.currentStatePointer++;
        currentBobaBot.resetData(this.bobaBotStateList.get(this.currentStatePointer));
    }

    /**
     * Compares if two VersionedBobaBot objects are equal
     *
     * @param other
     * @return boolean that represents whether the other object is considered equal to self
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof VersionedBobaBot) {
            VersionedBobaBot ot = (VersionedBobaBot) other;
            return this.bobaBotStateList.equals(ot.bobaBotStateList)
                    && this.currentStatePointer == ot.currentStatePointer
                    && this.sizeLimit == ot.sizeLimit;
        }
        return false;
    }

    /**
     * Returns a String representing this object
     */
    @Override
    public String toString() {
        return "VersionedBobaBot: \n"
                + "    size limit: " + this.sizeLimit + "\n"
                + "    current pointer: " + this.currentStatePointer + "\n"
                + this.bobaBotStateList.toString();
    }
}
