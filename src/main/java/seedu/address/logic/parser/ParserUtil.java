package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_CERTIFICATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR_PATTERN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_OWNER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_VACCINATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_WEIGHT;
import static seedu.address.model.ModelManager.ACCEPTABLE_DATE_FORMATS;
import static seedu.address.model.ModelManager.PREFERRED_DATE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.commands.AddPetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.person.Address;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Phone;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.Height;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetCertificate;
import seedu.address.model.pet.Species;
import seedu.address.model.pet.VaccinationStatus;
import seedu.address.model.pet.Weight;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_PERSON_CATEGORY = PersonCategory.MESSAGE_CONSTRAINTS;

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String orderString} into an {@code Order}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderString} is invalid.
     */
    public static Order parseOrder(String orderString, Buyer buyer) throws ParseException {
        requireNonNull(orderString);
        String trimmedOrderString = orderString.trim();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(trimmedOrderString,
                        PREFIX_ORDER_PET,
                        PREFIX_ORDER_STATUS,
                        PREFIX_ORDER_REQUESTS,
                        PREFIX_ORDER_PRICE,
                        PREFIX_ORDER_PRICE_RANGE,
                        PREFIX_ORDER_ADDITIONAL_REQUESTS,
                        PREFIX_ORDER_DATE);
        if (!arePrefixesPresent(argMultimap,
                PREFIX_ORDER_STATUS,
                PREFIX_ORDER_REQUESTS,
                PREFIX_ORDER_PRICE,
                PREFIX_ORDER_PRICE_RANGE,
                PREFIX_ORDER_DATE)) {
            throw new ParseException(AddOrderCommand.MESSAGE_USAGE);
        }

        PriceRange priceRange = parsePriceRange(argMultimap.getValue(PREFIX_ORDER_PRICE_RANGE).orElse(""));
        Request request = parseRequest(argMultimap.getValue(PREFIX_ORDER_REQUESTS).orElse(""));
        AdditionalRequests additionalRequests =
                parseAdditionalRequests(argMultimap.getAllValues(PREFIX_ORDER_ADDITIONAL_REQUESTS));
        LocalDate byDate = parseDate(argMultimap.getValue(PREFIX_ORDER_DATE).orElse(""));
        Price price = parsePrice(argMultimap.getValue(PREFIX_ORDER_PRICE).orElse(""));
        OrderStatus orderStatus = parseOrderStatus(argMultimap.getValue(PREFIX_ORDER_STATUS).orElse(""));

        Pet pet = null;
        if (argMultimap.getValue(PREFIX_ORDER_PET).isPresent()) {
            pet = parsePet(argMultimap.getValue(PREFIX_ORDER_PET).orElse(""));
        }

        return new Order(pet, buyer, priceRange, request, additionalRequests, byDate, price, orderStatus);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> orders} into a {@code List<Order>}.
     */
    public static List<Order> parseOrders(Collection<String> orders, Buyer buyer) throws ParseException {
        requireNonNull(orders);
        final List<Order> orderList = new ArrayList<>();
        for (String order : orders) {
            orderList.add(parseOrder(order, buyer));
        }
        return orderList;
    }

    /**
     * Parses {@code personCategory} into a {@code PersonCategory} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if the specified person category is invalid (not a buyer, deliverer, or supplier).
     */
    public static PersonCategory parsePersonCategory(String personCategory) throws ParseException {
        String trimmed = personCategory.trim();
        checkArgument(PersonCategory.isValidPersonCategory(trimmed), MESSAGE_INVALID_PERSON_CATEGORY);
        return Arrays.stream(PersonCategory.class.getEnumConstants())
                .filter(x -> x.toString().equals(trimmed))
                .findFirst().orElse(PersonCategory.BUYER);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses a {@code String orderStatus} into an {@code OrderStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderStatus} is invalid.
     */
    public static OrderStatus parseOrderStatus(String orderStatus) throws ParseException {
        requireNonNull(orderStatus);
        String trimmedOrderStatus = orderStatus.trim();
        if (!OrderStatus.isValidOrderStatus(trimmedOrderStatus)) {
            throw new ParseException(OrderStatus.MESSAGE_CONSTRAINTS);
        }
        return Arrays
                .stream(OrderStatus.class.getEnumConstants())
                .filter(x -> x.toString().equals(trimmedOrderStatus))
                .findFirst()
                .orElse(OrderStatus.PENDING);
    }

    /**
     * Parses a {@code String request} into an {@code Request}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code request} is invalid.
     */
    public static Request parseRequest(String request) throws ParseException {
        requireNonNull(request);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(request,
                        PREFIX_PET_SPECIES,
                        PREFIX_ORDER_AGE,
                        PREFIX_PET_COLOR,
                        PREFIX_PET_COLOR_PATTERN);
        if (!arePrefixesPresent(argMultimap,
                PREFIX_PET_SPECIES,
                PREFIX_ORDER_AGE,
                PREFIX_PET_COLOR,
                PREFIX_PET_COLOR_PATTERN)) {
            throw new ParseException(Request.MESSAGE_USAGE);
        }

        Age age = parseAge(argMultimap.getValue(PREFIX_ORDER_AGE).orElse(""));
        Color color = parseColor(argMultimap.getValue(PREFIX_PET_COLOR).orElse(""));
        ColorPattern colorPattern = parseColorPattern(argMultimap.getValue(PREFIX_PET_COLOR_PATTERN).orElse(""));
        Species species = parseSpecies(argMultimap.getValue(PREFIX_PET_SPECIES).orElse(""));
        return new Request(age, color, colorPattern, species);
    }

    /**
     * Parses a {@code String petString} into an {@code Pet}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code petString} is invalid.
     */
    public static Pet parsePet(String petString) throws ParseException {

        requireNonNull(petString);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(petString,
                        PREFIX_PET_OWNER,
                        PREFIX_PET_NAME,
                        PREFIX_PET_DATE_OF_BIRTH,
                        PREFIX_PET_COLOR,
                        PREFIX_PET_COLOR_PATTERN,
                        PREFIX_PET_HEIGHT,
                        PREFIX_PET_CERTIFICATE,
                        PREFIX_PET_SPECIES,
                        PREFIX_PET_VACCINATION_STATUS,
                        PREFIX_PET_WEIGHT,
                        PREFIX_PET_TAG);
        if (!arePrefixesPresent(argMultimap,
                PREFIX_PET_NAME,
                PREFIX_PET_DATE_OF_BIRTH,
                PREFIX_PET_COLOR,
                PREFIX_PET_COLOR_PATTERN,
                PREFIX_PET_HEIGHT,
                PREFIX_PET_SPECIES,
                PREFIX_PET_WEIGHT)) {
            throw new ParseException(AddPetCommand.MESSAGE_USAGE);
        }


        Person owner = null;
        //        TODO Parse the owner
        //        if (argMultimap.getValue(PREFIX_PET_OWNER).isPresent()) {
        //            owner = parseOwner(argMultimap.getValue(PREFIX_PET_OWNER));
        //        }

        Name name = parseName(argMultimap.getValue(PREFIX_PET_NAME).orElse(""));
        DateOfBirth dateOfBirth = parseDateOfBirth(argMultimap.getValue(PREFIX_PET_DATE_OF_BIRTH).orElse(""));
        Color color = parseColor(argMultimap.getValue(PREFIX_PET_COLOR).orElse(""));
        ColorPattern colorPattern = parseColorPattern(argMultimap.getValue(PREFIX_PET_COLOR_PATTERN).orElse(""));
        Height height = parseHeight(argMultimap.getValue(PREFIX_PET_HEIGHT).orElse(""));
        Set<PetCertificate> certificates = parseCertificates(argMultimap.getAllValues(PREFIX_PET_CERTIFICATE));
        Species species = parseSpecies(argMultimap.getValue(PREFIX_PET_SPECIES).orElse(""));
        Weight weight = parseWeight(argMultimap.getValue(PREFIX_PET_WEIGHT).orElse(""));
        VaccinationStatus vaccinationStatus =
                parseVaccinationStatus(argMultimap.getValue(PREFIX_PET_VACCINATION_STATUS).orElse("false"));
        Set<Tag> tags = parseTags(argMultimap.getAllValues(PREFIX_PET_TAG));

        Pet pet = new Pet(name,
                owner,
                color,
                colorPattern,
                dateOfBirth,
                species,
                weight,
                height,
                vaccinationStatus,
                tags,
                certificates);

        return pet;
    }

    /**
     * Parses a {@code String price} into an {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code price} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();

        double doublePrice;
        try {
            doublePrice = Double.parseDouble(trimmedPrice);
        } catch (NumberFormatException ex) {
            throw new ParseException(Price.MESSAGE_USAGE);
        }

        Price toReturn = new Price(doublePrice);
        if ((!toReturn.isNotApplicablePrice()) && toReturn.compareTo(new Price(0)) < 0) {
            throw new ParseException(Price.MESSAGE_USAGE);
        }
        return toReturn;
    }

    /**
     * Parses a {@code String priceRange} into an {@code PriceRange}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priceRange} is invalid.
     */
    public static PriceRange parsePriceRange(String priceRange) throws ParseException {
        requireNonNull(priceRange);
        String[] splitPrices = priceRange.split(PriceRange.DELIMITER);
        if (splitPrices.length != 2) {
            throw new ParseException(PriceRange.MESSAGE_USAGE);
        }

        Price lower = parsePrice(splitPrices[0]);
        Price upper = parsePrice(splitPrices[1]);

        if ((!upper.isNotApplicablePrice()) && upper.compareTo(lower) < 0) {
            throw new ParseException(PriceRange.MESSAGE_USAGE);
        }

        return new PriceRange(lower, upper);
    }

    /**
     * Parses {@code Collection<String> requests} into a {@code AdditionalRequests}.
     */
    public static AdditionalRequests parseAdditionalRequests(Collection<String> requests) {
        requireNonNull(requests);
        return new AdditionalRequests(requests);
    }

    /**
     * Parses a {@code String date} into an {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code date} cannot be parsed in all acceptable formats.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        LocalDate output;
        for (String format: ACCEPTABLE_DATE_FORMATS) {
            try {
                output = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
                return output;
            } catch (DateTimeParseException exception) {
                //Do nothing because it will eventually throw an exception if no formats match
            }
        }
        throw new ParseException("The date should be in this format: " + PREFERRED_DATE_FORMAT);
    }

    /**
     * Parses a {@code String age} into an {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        int intAge;
        try {
            intAge = Integer.parseInt(age);
        } catch (NumberFormatException ex) {
            throw new ParseException(Age.MESSAGE_USAGE);
        }
        return new Age(intAge);
    }

    /**
     * Parses a {@code String color} into an {@code Color}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Color parseColor(String color) {
        requireNonNull(color);
        String trimmedColor = color.trim();
        return new Color(trimmedColor);
    }

    /**
     * Parses a {@code String colorPattern} into an {@code ColorcolorPattern}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static ColorPattern parseColorPattern(String colorPattern) {
        requireNonNull(colorPattern);
        String trimmedColorPattern = colorPattern.trim();
        return new ColorPattern(trimmedColorPattern);
    }

    /**
     * Parses a {@code String species} into an {@code species}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Species parseSpecies(String species) {
        requireNonNull(species);
        String trimmedSpecies = species.trim();
        return new Species(trimmedSpecies);
    }

    /**
     * Parses a {@code String birthday} into an {@code DateOfBirth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code birthday} cannot be parsed in all acceptable formats.
     */
    public static DateOfBirth parseDateOfBirth(String date) throws ParseException {
        LocalDate output;
        for (String format: ACCEPTABLE_DATE_FORMATS) {
            try {
                output = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
                return new DateOfBirth(output);
            } catch (DateTimeParseException exception) {
                //Do nothing because it will eventually throw an exception if no formats match
            }
        }
        throw new ParseException(DateOfBirth.MESSAGE_USAGE);
    }

    /**
     * Parses a {@code String height} into an {@code Height}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code height} is invalid.
     */
    public static Height parseHeight(String height) throws ParseException {
        requireNonNull(height);

        double doubleHeight;
        try {
            doubleHeight = Double.parseDouble(height);
        } catch (NumberFormatException ex) {
            throw new ParseException(Height.MESSAGE_USAGE);
        }
        if (doubleHeight < 0) {
            throw new ParseException(Height.MESSAGE_USAGE);
        }

        return new Height(doubleHeight);
    }

    /**
     * Parses {@code Collection<String> certificates} into a {@code Set<PetCertificate>}.
     */
    public static Set<PetCertificate> parseCertificates(Collection<String> certificates) throws ParseException {
        requireNonNull(certificates);
        final Set<PetCertificate> certificateSet = new HashSet<>();
        for (String cert : certificates) {
            certificateSet.add(parsePetCertificate(cert));
        }
        return certificateSet;
    }

    /**
     * Parses a {@code String petCertificate} into an {@code PetCertificate}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static PetCertificate parsePetCertificate(String certificate) {
        requireNonNull(certificate);
        String trimmedCertificate = certificate.trim();
        return new PetCertificate(trimmedCertificate);
    }

    /**
     * Parses a {@code String vaccinationStatus} into an {@code VaccinationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weight} is invalid.
     */
    public static VaccinationStatus parseVaccinationStatus(String vaccinationStatus) throws ParseException {
        requireNonNull(vaccinationStatus);
        if ("true".equals(vaccinationStatus)) {
            return new VaccinationStatus(true);
        } else if ("false".equals(vaccinationStatus)) {
            return new VaccinationStatus(false);
        } else {
            throw new ParseException(VaccinationStatus.MESSAGE_USAGE);
        }
    }

    /**
     * Parses a {@code String weight} into an {@code Weight}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weight} is invalid.
     */
    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);

        double doubleWeight;
        try {
            doubleWeight = Double.parseDouble(weight);
        } catch (NumberFormatException ex) {
            throw new ParseException(Weight.MESSAGE_USAGE);
        }
        if (doubleWeight < 0) {
            throw new ParseException(Weight.MESSAGE_USAGE);
        }

        return new Weight(doubleWeight);
    }
}
