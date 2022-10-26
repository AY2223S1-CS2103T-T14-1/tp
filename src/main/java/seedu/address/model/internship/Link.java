package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's link in the findMyIntern.
 * Guarantees: immutable; is valid as declared in {@link #isValidLink(String)}
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS = "Link is of an incorrect format.";
    private static final String OPTIONAL_PROTOCOL = "\\b(?:(https?|http)://|www\\.)?";
    private static final String HOSTNAME = "[-a-zA-Z0-9+&#/%?=~_|$!:,.;]*[a-zA-Z0-9+&@#/%=~_|$]\\.";
    private static final String PATH = "[-a-zA-Z0-9+&@#/%?=~_|$!:,.;]*[a-zA-Z0-9+&@#/%=~_|$]";
    public static final String VALIDATION_REGEX = OPTIONAL_PROTOCOL + HOSTNAME + PATH;
    public final String value;

    /**
     * Constructs a {@code Link}.
     *
     * @param link A valid link.
     */
    public Link(String link) {
        requireNonNull(link);
        checkArgument(isValidLink(link), MESSAGE_CONSTRAINTS);
        value = link;
    }

    /**
     * Returns true if a given string is a valid link.
     */
    public static boolean isValidLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && value.equals(((Link) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
