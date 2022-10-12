<<<<<<<< HEAD:src/main/java/longtimenosee/model/person/exceptions/DuplicateClientException.java
package seedu.address.model.person.exceptions;
========
package longtimenosee.model.client.exceptions;
>>>>>>>> master:src/main/java/longtimenosee/model/client/exceptions/DuplicateClientException.java

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException() {
        super("Operation would result in duplicate clients");
    }
}
