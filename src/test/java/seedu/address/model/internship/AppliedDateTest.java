package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppliedDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppliedDate(null));
    }

    @Test
    public void constructor_invalidAppliedDate_throwsIllegalArgumentException() {
        String invalidAppliedDate = "";
        assertThrows(IllegalArgumentException.class, () -> new AppliedDate(invalidAppliedDate));
    }

    @Test
    public void isValidAppliedDate() {
        // null appliedDate
        assertThrows(NullPointerException.class, () -> AppliedDate.isValidAppliedDate(null));

        // invalid appliedDates
        assertFalse(AppliedDate.isValidAppliedDate("")); // empty string
        assertFalse(AppliedDate.isValidAppliedDate(" ")); // spaces only

        // valid appliedDates
        assertTrue(AppliedDate.isValidAppliedDate("2022-10-30"));
        assertTrue(AppliedDate.isValidAppliedDate("2022-11-30"));
        assertTrue(AppliedDate.isValidAppliedDate("2022-12-30"));
    }
}
