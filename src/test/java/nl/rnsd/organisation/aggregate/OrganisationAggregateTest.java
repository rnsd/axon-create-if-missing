package nl.rnsd.organisation.aggregate;

import nl.rnsd.organisation.api.ImportOrganisationCommand;
import nl.rnsd.organisation.api.Organisation;
import nl.rnsd.organisation.api.OrganisationCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrganisationAggregateTest {

    private AggregateTestFixture<OrganisationAggregate> testFixture;

    private final Organisation organisation = new Organisation("1", "name",
        null, null, null, null, null, null, null, null, null, null);

    @BeforeEach
    void name() {
        testFixture = new AggregateTestFixture<>(OrganisationAggregate.class);
    }

    @Test
    void importOfNewOrganisationShouldLeadToOrganisationCreatedEvent() {
        ImportOrganisationCommand importOrganisationCommand = new ImportOrganisationCommand(organisation.getId(), organisation);
        OrganisationCreatedEvent organisationCreatedEvent = new OrganisationCreatedEvent(organisation);

        testFixture.givenNoPriorActivity()
            .when(importOrganisationCommand)
            .expectEvents(organisationCreatedEvent);
    }

}