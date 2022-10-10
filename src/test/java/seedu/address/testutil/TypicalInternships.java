package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship ALIBABA = new InternshipBuilder().withCompany("Alibaba")
            .withLink("https://careers.alibaba.com")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withAppliedDate("2022-10-30").withEmail("careers@alibaba.com").withTags("friends").build();
    public static final Internship BINANCE = new InternshipBuilder().withCompany("Binance")
            .withLink("https://www.binance.com/en/careers")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withAppliedDate("2022-10-20").withEmail("careers@binance.com")
            .withTags("owesMoney", "friends").build();
    public static final Internship CITADEL = new InternshipBuilder().withCompany("Citadel")
            .withLink("https://www.citadel.com/careers")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withAppliedDate("2022-10-25").withEmail("careers@citadel.com").build();
    public static final Internship DELL = new InternshipBuilder().withCompany("Dell")
            .withLink("https://jobs.dell.com/internships")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withAppliedDate("2022-11-05").withEmail("careers@dell.com")
            .withTags("friends").build();
    public static final Internship EBAY = new InternshipBuilder().withCompany("Ebay")
            .withLink("https://careers.ebayinc.com/")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withAppliedDate("2022-11-10").withEmail("careers@ebay.com").build();
    public static final Internship FACEBOOK = new InternshipBuilder().withCompany("Facebook")
            .withLink("https://metacareers.com/careerprograms/students")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withAppliedDate("2022-11-15").withEmail("careers@facebook.com").build();
    public static final Internship GOLDMAN = new InternshipBuilder().withCompany("Goldman Sachs")
            .withLink("https://www.goldmansachs.com/careers/students/programs/")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withAppliedDate("2022-11-20").withEmail("careers@goldmansachs.com").build();

    // Manually added
    public static final Internship HUAWEI = new InternshipBuilder().withCompany("Huawei")
            .withLink("https://career.huawei.com/reccampportal/portal5/index.html")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withAppliedDate("2022-11-25").withEmail("careers@huawei.com").build();
    public static final Internship INDEED = new InternshipBuilder().withCompany("Indeed")
            .withLink("https://sg.indeed.com/cmp/Indeed")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withAppliedDate("2022-11-30").withEmail("careers@indeed.com").build();

    // Manually added - Internship's details found in {@code CommandTestUtil}
    public static final Internship GOOGLE = new InternshipBuilder().withCompany(VALID_COMPANY_GOOGLE)
            .withLink(VALID_LINK_GOOGLE).withEmail(VALID_EMAIL_GOOGLE)
            .withAppliedDate(VALID_APPLIED_DATE_GOOGLE).withTags(VALID_TAG_FRIEND).build();
    public static final Internship TIKTOK = new InternshipBuilder().withCompany(VALID_COMPANY_TIKTOK)
            .withLink(VALID_LINK_TIKTOK).withEmail(VALID_EMAIL_TIKTOK)
            .withAppliedDate(VALID_APPLIED_DATE_TIKTOK).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternships() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Internship internship : getTypicalInternships()) {
            ab.addInternship(internship);
        }
        return ab;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(ALIBABA, BINANCE, CITADEL, DELL, EBAY, FACEBOOK, GOLDMAN));
    }
}
