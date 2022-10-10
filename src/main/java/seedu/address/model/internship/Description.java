package seedu.address.model.internship;

/**
 * Represents an Internship's description in the address book.
 */
public class Description {

    private final String value;

    public Description(String description) {
        this.value = description;
    }

    @Override
    public String toString() {
        return value;
    }
}
