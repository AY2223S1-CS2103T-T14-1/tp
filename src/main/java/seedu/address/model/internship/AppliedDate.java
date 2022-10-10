package seedu.address.model.internship;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's applied date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppliedDate(String)}
 */
public class AppliedDate {

    public static final String MESSAGE_CONSTRAINTS = "AppliedDate must be a parsable LocalDate";

    public final String value;

    /**
     * Constructs an {@code AppliedDate}.
     *
     * @param value A valid appliedDate.
     */
    public AppliedDate(String value) {
        requireNonNull(value);
        checkArgument(isValidAppliedDate(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAppliedDate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return LocalDate.parse(value).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppliedDate // instanceof handles nulls
                && value.equals(((AppliedDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
