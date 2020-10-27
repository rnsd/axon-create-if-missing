package nl.rnsd.organisation.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class Organisation(
        val id: String,
        val name: String?,
        val accountName: String?,
        val externalCreateDate: String?,
        val externalLastModificationDate: String?,
        val street: String?,
        val houseNumber: String?,
        val houseNumberSupplement: String?,
        val postalCode: String?,
        val city: String?,
        val country: String?,
        val brin: String?
)

data class ImportOrganisationCommand(
        @TargetAggregateIdentifier val id: String,
        val organisation: Organisation
)

data class UpdateOrganisationCommand(
        @TargetAggregateIdentifier val businessPartnerId: String,
        val organisation: Organisation
)

data class DeleteOrganisationCommand(
        @TargetAggregateIdentifier val id: String,
        val organisation: Organisation
)

data class OrganisationCreatedEvent(
        val organisation: Organisation
)

data class OrganisationUpdatedEvent(
        val organisation: Organisation
)

data class OrganisationDeletedEvent(
        val businessPartnerId: String
)

