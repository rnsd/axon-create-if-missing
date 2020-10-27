package nl.rnsd.organisation.aggregate;

import lombok.extern.slf4j.Slf4j;
import nl.rnsd.organisation.api.DeleteOrganisationCommand;
import nl.rnsd.organisation.api.ImportOrganisationCommand;
import nl.rnsd.organisation.api.Organisation;
import nl.rnsd.organisation.api.OrganisationCreatedEvent;
import nl.rnsd.organisation.api.OrganisationDeletedEvent;
import nl.rnsd.organisation.api.OrganisationUpdatedEvent;
import nl.rnsd.organisation.api.UpdateOrganisationCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
public class OrganisationAggregate {
    @AggregateIdentifier
    private String id;

    private Organisation organisation;

    public OrganisationAggregate() {
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(ImportOrganisationCommand cmd) {
        if (id == null) {
            apply(new OrganisationCreatedEvent(cmd.getOrganisation()));
        } else if (!cmd.getOrganisation().equals(organisation)) {
            apply(new OrganisationUpdatedEvent(cmd.getOrganisation()));
        }
    }

    @CommandHandler
    public void handle(UpdateOrganisationCommand cmd) {
        if (organisation == null) {
            log.info("Can not update organisations that have been deleted");
        } else {
            apply(new OrganisationUpdatedEvent(cmd.getOrganisation()));
        }
    }

    @CommandHandler
    public void handle(DeleteOrganisationCommand cmd) {
        apply(new OrganisationDeletedEvent(cmd.getId()));
    }


    @EventSourcingHandler
    public void on(OrganisationCreatedEvent evt) {
        if (id == null) id = evt.getOrganisation().getId();
        organisation = evt.getOrganisation();
    }

    @EventSourcingHandler
    public void on(OrganisationUpdatedEvent evt) {
        organisation = evt.getOrganisation();
    }
}
