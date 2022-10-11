package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.entry.Entry;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Entry> PREDICATE_SHOW_ALL_ENTRIES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' penny wise file path.
     */
    Path getPennyWiseFilePath();

    /**
     * Sets the user prefs' penny wise file path.
     */
    void setPennyWiseFilePath(Path pennyWiseFilePath);

    /**
     * Replaces penny wise data with the data in {@code pennyWise}.
     */
    void setPennyWise(ReadOnlyPennyWise pennyWise);

    /**
     * Returns the PennyWise
     */
    ReadOnlyPennyWise getPennyWise();

    /**
     * Returns true if an expenditure with the same identity as {@code entry} exists in the expenditure list.
     */
    boolean hasExpenditure(Entry entry);

    /**
     * Deletes the given expenditure.
     * The expenditure must exist in the expenditure list.
     */
    void deleteExpenditure(Entry target);

    /**
     * Adds the given expenditure.
     * {@code entry} must not already exist in the expenditure list.
     */
    void addExpenditure(Entry entry);

    /**
     * Replaces the given expenditure {@code target} with {@code editedEntry}.
     * {@code target} must exist in the expenditure list.
     * The expenditure identity of {@code editedEntry} must not be the same as another existing expenditure
     * in the expenditure list.
     */
    void setExpenditure(Entry target, Entry editedEntry);

    /**
     * Returns true if an income with the same identity as {@code entry} exists in the income list.
     */
    boolean hasIncome(Entry entry);

    /**
     * Deletes the given income.
     * The income must exist in the income list.
     */
    void deleteIncome(Entry target);

    /**
     * Adds the given income.
     * {@code entry} must not already exist in the income list.
     */
    void addIncome(Entry entry);

    /**
     * Replaces the given income {@code target} with {@code editedEntry}.
     * {@code target} must exist in the income list.
     * The income identity of {@code editedEntry} must not be the same as another existing income in the income list.
     */
    void setIncome(Entry target, Entry editedEntry);

    /**
     * Returns an unmodifiable view of the filtered expenditure list
     */
    ObservableList<Entry> getFilteredExpenditureList();

    /**
     * Returns an unmodifiable view of the filtered income list
     */
    ObservableList<Entry> getFilteredIncomeList();

    /**
     * Updates the filter of the filtered entry list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEntryList(Predicate<Entry> predicate);
}
