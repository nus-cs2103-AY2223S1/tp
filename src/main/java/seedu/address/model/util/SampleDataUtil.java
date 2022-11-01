package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.Month;

import seedu.address.model.BuyerBook;
import seedu.address.model.PropertyBook;
import seedu.address.model.ReadOnlyBuyerBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Email;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.property.Description;
import seedu.address.model.property.Owner;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;

/**
 * Contains utility methods for populating {@code BuyerBook} with sample data.
 */
public class SampleDataUtil {


    public static LocalDateTime[] getSampleTime() {
        int thisYear = 2022;
        int day = 6;
        int hour = 17;
        int minutes = 30;
        return new LocalDateTime[] {
                LocalDateTime.of(thisYear, Month.JANUARY, day, hour, minutes),
                LocalDateTime.of(thisYear, Month.FEBRUARY, day, hour, minutes),
                LocalDateTime.of(thisYear, Month.MARCH, day, hour, minutes),
                LocalDateTime.of(thisYear, Month.APRIL, day, hour, minutes),
                LocalDateTime.of(thisYear, Month.MAY, day, hour, minutes),
                LocalDateTime.of(thisYear, Month.JUNE, day, hour, minutes),
        };
    }

    public static Buyer[] getSampleBuyers() {
        return new Buyer[]{
            new Buyer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new PriceRange("200000 - 250000"), new Characteristics("Bright; 5-Room"),
                    new Priority("high"), getSampleTime()[0]),
            new Buyer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new PriceRange("250000 - 280000"), new Characteristics("Clean; Large"),
                    new Priority("low"), getSampleTime()[1]),
            new Buyer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new PriceRange("300000 - 400000"), new Characteristics("Near MRT"),
                    new Priority("low"), getSampleTime()[2]),
            new Buyer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new PriceRange("500000 - 800000"), new Characteristics("Near School"),
                    new Priority("normal"), getSampleTime()[3]),
            new Buyer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new PriceRange("200000 - 250000"), new Characteristics("Bishan"),
                    new Priority("normal"), getSampleTime()[4]),
            new Buyer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new PriceRange("100000 - 150000"), new Characteristics("Toa Payoh; Kid-Friendly"),
                    new Priority("low"), getSampleTime()[5])
        };
    }

    public static Owner[] getSampleOwners() {
        return new Owner[]{
            new Owner(new Name("Alex Yeoh"), new Phone("87438807")),
            new Owner(new Name("Bernice Yu"), new Phone("99272758")),
            new Owner(new Name("Charlotte Oliveiro"), new Phone("93210283")),
            new Owner(new Name("David Li"), new Phone("91031282")),
            new Owner(new Name("Irfan Ibrahim"), new Phone("92492021")),
            new Owner(new Name("Roy Balakrishnan"), new Phone("92624417"))
        };
    }

    public static Property[] getSampleProperties() {
        return new Property[]{
            new Property(new PropertyName("Residential College 4"), new Price("50000"),
                    new Address("6 College Avenue East"), new Description("A place for NUS students to stay."),
                    new Characteristics("Bright; Sunny"), getSampleOwners()[0], getSampleTime()[0]),
            new Property(new PropertyName("Tembusu College"), new Price("9999"),
                    new Address("26 College Avenue East"), new Description("A place for NUS students to stay."),
                    new Characteristics("Near MRT"), getSampleOwners()[1], getSampleTime()[1]),
            new Property(new PropertyName("Peak Residence"), new Price("10000000"),
                    new Address("333 Thompson Road"),
                    new Description("latest freehold luxurious exclusive condominium."),
                    new Characteristics("Near School"), getSampleOwners()[2], getSampleTime()[2]),
            new Property(new PropertyName("Hut"), new Price("25000"),
                    new Address("25 Regent Road"),
                    new Description("new 3-room HDB flat"),
                    new Characteristics("Kid-Friendly"), getSampleOwners()[3], getSampleTime()[3]),
        };
    }

    public static ReadOnlyBuyerBook getSampleBuyerBook() {
        BuyerBook sampleAb = new BuyerBook();
        for (Buyer sampleBuyer : getSampleBuyers()) {
            sampleAb.addBuyer(sampleBuyer);
        }
        return sampleAb;
    }

    public static ReadOnlyPropertyBook getSamplePropertyBook() {
        PropertyBook propertyBook = new PropertyBook();
        for (Property sampleProperty : getSampleProperties()) {
            propertyBook.addProperty(sampleProperty);
        }
        return propertyBook;
    }


}
