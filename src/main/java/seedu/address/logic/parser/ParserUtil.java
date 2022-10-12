package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_PERSON_CATEGORY = PersonCategory.MESSAGE_CONSTRAINTS;
    public static final String MESSAGE_ORDER_USAGE = AddOrderCommand.MESSAGE_USAGE;

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
                ArgumentTokenizer.tokenize(orderString,
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
            throw new ParseException(MESSAGE_ORDER_USAGE);
        }

        OrderStatus orderStatus =

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElse(""));
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElse(""));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElse(""));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElse(""));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Buyer buyer = new Buyer(PersonCategory.BUYER, name, phone, email, address, tagList, null);

        return new Order(trimmedOrderString);
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
     * Parses {@code Collection<String> orders} into a {@code Set<Order>}.
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

    PREFIX_ORDER_REQUESTS,
    PREFIX_ORDER_PRICE,
    PREFIX_ORDER_PRICE_RANGE,
    PREFIX_ORDER_ADDITIONAL_REQUESTS,
    PREFIX_ORDER_DATE
}
