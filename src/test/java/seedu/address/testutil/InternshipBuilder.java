package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Email;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final String DEFAULT_COMPANY = "Google";
    public static final String DEFAULT_LINK = "8https://careers.google.com/students";
    public static final String DEFAULT_EMAIL = "careers@google.com";
    public static final ApplicationStatus DEFAULT_APPLICATION_STATUS = ApplicationStatus.Applied;
    public static final String DEFAULT_APPLIED_DATE = "2022-10-30";

    private Company company;
    private Link link;
    private Email email;
    private ApplicationStatus applicationStatus;
    private AppliedDate appliedDate;
    private Set<Tag> tags;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        company = new Company(DEFAULT_COMPANY);
        link = new Link(DEFAULT_LINK);
        email = new Email(DEFAULT_EMAIL);
        applicationStatus = DEFAULT_APPLICATION_STATUS;
        appliedDate = new AppliedDate(DEFAULT_APPLIED_DATE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        company = internshipToCopy.getCompany();
        link = internshipToCopy.getLink();
        email = internshipToCopy.getEmail();
        applicationStatus = internshipToCopy.getApplicationStatus();
        appliedDate = internshipToCopy.getAppliedDate();
        tags = new HashSet<>(internshipToCopy.getTags());
    }

    /**
     * Sets the {@code Company} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Internship} that we are building.
     */
    public InternshipBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code AppliedDate} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withAppliedDate(String appliedDate) {
        this.appliedDate = new AppliedDate(appliedDate);
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withLink(String link) {
        this.link = new Link(link);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
        return this;
    }

    public Internship build() {
        return new Internship(company, link, email, applicationStatus, appliedDate, tags);
    }

}
