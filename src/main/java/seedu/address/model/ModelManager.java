package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.Property;

/**
 * Represents the in-memory model of the buyer book and property book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final BuyerBook buyerBook;
    private final PropertyBook propertyBook;
    private final FilteredList<Buyer> filteredBuyers;
    private final FilteredList<Property> filteredProperties;


    /**
     * Initializes a ModelManager with the given buyerBook and userPrefs.
     */
    public ModelManager(ReadOnlyBuyerBook buyerBook, ReadOnlyPropertyBook propertyBook,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(buyerBook, propertyBook, userPrefs);

        logger.fine("Initializing with buyer book: " + buyerBook + " and property book: " + propertyBook
                + " and user prefs " + userPrefs);

        this.buyerBook = new BuyerBook(buyerBook);
        this.propertyBook = new PropertyBook(propertyBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBuyers = new FilteredList<>(this.buyerBook.getBuyerList());
        filteredProperties = new FilteredList<>(this.propertyBook.getPropertyList());
    }

    public ModelManager() {
        this(new BuyerBook(), new PropertyBook(), new UserPrefs());
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
    public Path getBuyerBookFilePath() {
        return userPrefs.getBuyerBookFilePath();
    }

    @Override
    public void setBuyerBookFilePath(Path buyerBookFilePath) {
        requireNonNull(buyerBookFilePath);
        userPrefs.setBuyerBookFilePath(buyerBookFilePath);
    }

    @Override
    public Path getPropertyBookFilePath() {
        return userPrefs.getPropertyBookFilePath();
    }

    @Override
    public void setPropertyBookFilePath(Path propertyBookFilePath) {
        requireNonNull(propertyBookFilePath);
        userPrefs.setPropertyBookFilePath(propertyBookFilePath);
    }

    //=========== BuyerBook ================================================================================

    @Override
    public void setBuyerBook(ReadOnlyBuyerBook buyerBook) {
        this.buyerBook.resetData(buyerBook);
    }

    @Override
    public ReadOnlyBuyerBook getBuyerBook() {
        return buyerBook;
    }

    @Override
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return buyerBook.hasBuyer(buyer);
    }

    @Override
    public void deleteBuyer(Buyer target) {
        buyerBook.removeBuyer(target);
    }

    @Override
    public void addBuyer(Buyer buyer) {
        buyerBook.addBuyer(buyer);
        updateFilteredBuyerList(PREDICATE_SHOW_ALL_BUYERS);
    }

    @Override
    public void setBuyer(Buyer target, Buyer editedBuyer) {
        requireAllNonNull(target, editedBuyer);
        buyerBook.setBuyer(target, editedBuyer);
    }

    //=========== Filtered Buyer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Buyer} backed by the internal list of
     * {@code BuyerBook}
     */
    @Override
    public ObservableList<Buyer> getFilteredBuyerList() {
        return filteredBuyers;
    }

    @Override
    public void updateFilteredBuyerList(Predicate<Buyer> predicate) {
        requireNonNull(predicate);
        filteredBuyers.setPredicate(predicate);
    }

    @Override
    public void sortBuyerList(Comparator<Buyer> comparator) {
        requireNonNull(comparator);
        List<Buyer> sortedList = new ArrayList<>(buyerBook.getBuyerList());
        sortedList.sort(comparator);
        buyerBook.setBuyers(sortedList);
    }


    //=========== PropertyBook ================================================================================

    @Override
    public void setPropertyBook(ReadOnlyPropertyBook propertyBook) {
        this.propertyBook.resetData(propertyBook);
    }

    @Override
    public ReadOnlyPropertyBook getPropertyBook() {
        return propertyBook;
    }

    @Override
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return propertyBook.hasProperty(property);
    }

    @Override
    public void deleteProperty(Property target) {
        propertyBook.removeProperty(target);
    }

    @Override
    public void addProperty(Property property) {
        propertyBook.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);
        propertyBook.setProperty(target, editedProperty);
    }

    //=========== Filtered Property List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Property} backed by the internal list of
     * {@code PropertyBook}
     */
    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return filteredProperties;
    }

    @Override
    public void updateFilteredPropertyList(Predicate<Property> predicate) {
        requireNonNull(predicate);
        filteredProperties.setPredicate(predicate);
    }

    @Override
    public void sortPropertyList(Comparator<Property> comparator) {
        requireNonNull(comparator);
        List<Property> sortedList = new ArrayList<>(propertyBook.getPropertyList());
        sortedList.sort(comparator);
        propertyBook.setProperties(sortedList);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        return userPrefs.equals(other.userPrefs)
                && buyerBook.equals(other.buyerBook)
                && propertyBook.equals(other.propertyBook)
                && filteredBuyers.equals(other.filteredBuyers)
                && filteredProperties.equals(other.filteredProperties);
    }
}
