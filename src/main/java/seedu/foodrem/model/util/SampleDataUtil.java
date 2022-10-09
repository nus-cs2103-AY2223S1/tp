package seedu.foodrem.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemUnit;
import seedu.foodrem.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Item[] getSampleItems() {
        return new Item[]{
            new Item(new ItemName("Potatoes"),
                new ItemQuantity("10"),
                new ItemUnit("kg"),
                new ItemBoughtDate("2022-11-11"),
                new ItemExpiryDate("2022-11-11")),
            new Item(new ItemName("Cucumbers"),
                new ItemQuantity("2000"),
                new ItemUnit("grams"),
                new ItemBoughtDate("2022-11-11"),
                new ItemExpiryDate("2022-11-11"))
        };
        //return new Person[] {
        //    new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
        //        new Address("Blk 30 Geylang Street 29, #06-40"),
        //        getTagSet("friends")),
        //    new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
        //        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
        //        getTagSet("colleagues", "friends")),
        //    new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
        //        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
        //        getTagSet("neighbours")),
        //    new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
        //        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
        //        getTagSet("family")),
        //    new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
        //        new Address("Blk 47 Tampines Street 20, #17-35"),
        //        getTagSet("classmates")),
        //    new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
        //        new Address("Blk 45 Aljunied Street 85, #11-31"),
        //        getTagSet("colleagues"))
        //};
    }

    public static ReadOnlyFoodRem getSampleAddressBook() {
        FoodRem sampleAddressBook = new FoodRem();
        for (Item sampleItem : getSampleItems()) {
            sampleAddressBook.addItem(sampleItem);
        }
        return sampleAddressBook;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
