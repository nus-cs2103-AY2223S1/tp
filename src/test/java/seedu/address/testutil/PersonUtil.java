package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTHLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.NormalTag;




/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_RISKTAG + person.getRiskTag().tagName + " ");
        sb.append(PREFIX_PLANTAG + person.getPlanTag().tagName + " ");
        sb.append(PREFIX_CLIENTTAG + person.getClientTag().tagName + " ");
        sb.append(PREFIX_INCOME + person.getIncome().value.substring(1) + " ");
        sb.append(PREFIX_MONTHLY + person.getMonthly().value.substring(1) + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getRiskTag().ifPresent(riskTag -> sb.append(PREFIX_RISKTAG).append(riskTag.tagName).append(" "));
        descriptor.getClientTag().ifPresent(
                clientTag -> sb.append(PREFIX_CLIENTTAG).append(clientTag.tagName).append(" "));
        descriptor.getMonthly().ifPresent(monthly -> sb.append(PREFIX_MONTHLY)
                .append(monthly.value.substring(1)).append(" "));
        descriptor.getPlanTag().ifPresent(planTag -> sb.append(PREFIX_PLANTAG).append(planTag.tagName).append(" "));
        descriptor.getIncome().ifPresent(incomeLevel -> sb.append(PREFIX_INCOME)
                .append(incomeLevel.value.substring(1)).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<NormalTag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
