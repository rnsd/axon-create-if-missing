package nl.rnsd.organisation.aggregate;

import nl.rnsd.organisation.api.ImportOrganisationCommand;
import nl.rnsd.organisation.api.Organisation;
import nl.rnsd.organisation.api.OrganisationCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OrganisationAggregate2Test {

    private AggregateTestFixture<OrganisationAggregate> testFixture;

    private final Organisation org1 = new Organisation("id1", "org1", null, null, null,
        "straat", "12", null, "3033SV", "Rotterdam", "Nederland", "6A21NN");

    @BeforeEach
    void name() {
        testFixture = new AggregateTestFixture<>(OrganisationAggregate.class);
    }

    @Test
    void test1() {
        ImportOrganisationCommand cmd = new ImportOrganisationCommand(org1.getId(), org1);
        OrganisationCreatedEvent organisationCreatedEvent = new OrganisationCreatedEvent(org1);

        testFixture.givenNoPriorActivity()
            .when(cmd)
            .expectEvents(organisationCreatedEvent);
    }

    @Test
    void equalsOrg() {

        Organisation org1 = new Organisation("id1", "org1", null, null, null,
            "straat", "12", null, "3033SV", "Rotterdam", "Nederland", "6A21NN");
        Organisation org2 = new Organisation("id2", "org2", null, null, null,
            "straat", "12", null, "3033SV", "Rotterdam", "Nederland", "6A21MM");
        Organisation org1NoUpdate = new Organisation("id1", "org1", null, null, null,
            "straat", "12", null, "3033SV", "Rotterdam", "Nederland", "6A21NN");
        Organisation org1Updated = new Organisation("id1", "org1NameUpdate", null, null, null,
            "straat", "12", null, "3033SV", "Rotterdam", "Nederland", "6A21NN");

        assertEquals(org1NoUpdate, org1);
        assertNotEquals(org1Updated, org1);
    }

}