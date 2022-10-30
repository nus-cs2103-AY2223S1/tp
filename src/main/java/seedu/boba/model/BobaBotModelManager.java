package seedu.boba.model;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.image.Image;
import seedu.boba.commons.core.GuiSettings;
import seedu.boba.commons.core.LogsCenter;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.customer.exceptions.PersonNotFoundException;
import seedu.boba.model.exceptions.NextStateNotFoundException;
import seedu.boba.model.exceptions.PreviousStateNotFoundException;
import seedu.boba.model.promotion.Promotion;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static seedu.boba.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the in-memory bobaBotModel of the address book data.
 */
public class BobaBotModelManager implements BobaBotModel {
    private static final Logger logger = LogsCenter.getLogger(BobaBotModelManager.class);

    private final VersionedBobaBot versionedBobaBot;
    private final BobaBot bobaBot;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;
    private final Promotion promotionManager;

    /**
     * Initializes a BobaBotModelManager with the given bobaBot and userPrefs.
     */
    public BobaBotModelManager(ReadOnlyBobaBot bobaBot, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(bobaBot, userPrefs);

        logger.fine("Initializing with bobaBot: " + bobaBot + " and user prefs " + userPrefs);
        LocalDate currentDate = LocalDate.now();
        String currentMonth = String.valueOf(currentDate.getMonth().getValue());

        for (Customer customer : bobaBot.getPersonList()) {
            if (customer.getBirthdayMonth().value.equals(currentMonth)) {
                customer.addBirthdayTag();
            } else {
                customer.removeBirthdayTag();
            }
        }
        this.bobaBot = new BobaBot(bobaBot);
        this.versionedBobaBot = new VersionedBobaBot(bobaBot);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.bobaBot.getPersonList());
        this.promotionManager = new Promotion();
    }

    public BobaBotModelManager() {
        this(new BobaBot(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getBobaBotFilePath() {
        return userPrefs.getBobaBotFilePath();
    }

    @Override
    public void setBobaBotFilePath(Path bobaBotFilePath) {
        requireNonNull(bobaBotFilePath);
        userPrefs.setBobaBotFilePath(bobaBotFilePath);
    }

    //=========== BobaBot ================================================================================

    @Override
    public void setBobaBot(ReadOnlyBobaBot bobaBot) {
        this.bobaBot.resetData(bobaBot);
    }

    @Override
    public ReadOnlyBobaBot getBobaBot() {
        return bobaBot;
    }

    @Override
    public boolean hasPerson(Customer customer) {
        requireNonNull(customer);
        return bobaBot.hasPerson(customer);
    }

    @Override
    public void deletePerson(Customer target) {
        bobaBot.removePerson(target);
    }

    @Override
    public void addPerson(Customer customer) {
        bobaBot.addPerson(customer);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        bobaBot.setPerson(target, editedCustomer);
    }

    @Override
    public void commitBobaBot() {
        versionedBobaBot.commit(this.bobaBot);
    }

    @Override
    public void undoBobaBot() throws PreviousStateNotFoundException {
        versionedBobaBot.undo(this.bobaBot);
    }

    @Override
    public void redoBobaBot() throws NextStateNotFoundException {
        versionedBobaBot.redo(this.bobaBot);
    }

    /**
     * Returns the index of the customer with the same phone number.
     *
     * @param phone Phone number to search
     * @return index of the customer with the same phone number
     * @throws PersonNotFoundException if no customer with corresponding phone number found
     */
    @Override
    public int findNum(Phone phone) throws PersonNotFoundException {
        requireNonNull(phone);
        return bobaBot.findNum(phone);
    }

    /**
     * Returns the index of the customer with the same email.
     *
     * @param email Email to search
     * @return index of the customer with the same email
     * @throws PersonNotFoundException if no customer with corresponding email found
     */
    @Override
    public int findEmail(Email email) throws PersonNotFoundException {
        requireNonNull(email);
        return bobaBot.findEmail(email);
    }

    /**
     * Returns the current Reward points of a Customer
     *
     * @param phone Phone number of the Customer of interest
     * @return the current Reward points of a Customer
     */
    @Override
    public Reward getCurrentReward(Phone phone) {
        requireAllNonNull(phone);
        return bobaBot.getCurrentReward(phone);
    }

    /**
     * Returns the current Reward points of a Customer
     *
     * @param email Email of the Customer of interest
     * @return the current Reward points of a Customer
     */
    @Override
    public Reward getCurrentReward(Email email) {
        requireAllNonNull(email);
        return bobaBot.getCurrentReward(email);
    }

    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedBobaBot}
     */
    @Override
    public ObservableList<Customer> getFilteredPersonList() {
        return filteredCustomers;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    @Override
    public ObservableList<Image> getPromotionList() {
        return promotionManager.getPromotionList();
    }

    @Override
    public void parseAllPromotion(String filePath) {
        promotionManager.parseAllPromotions(filePath);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof BobaBotModelManager)) {
            return false;
        }

        // state check
        BobaBotModelManager other = (BobaBotModelManager) obj;
        return bobaBot.equals(other.bobaBot)
                && userPrefs.equals(other.userPrefs)
                && filteredCustomers.equals(other.filteredCustomers);
    }
}
