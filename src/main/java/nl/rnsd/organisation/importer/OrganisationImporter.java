package nl.rnsd.organisation.importer;

import lombok.extern.slf4j.Slf4j;
import nl.rnsd.organisation.api.ImportOrganisationCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static nl.rnsd.organisation.importer.OrganisationDataMock.getOrganisations;


@Slf4j
@RestController
public class OrganisationImporter {

    private final CommandGateway commandGateway;

    public OrganisationImporter(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping("/import/{nr}")
    public void importOrganisations(@PathVariable Integer nr) {
        long startTime = System.currentTimeMillis();

        getOrganisations(nr).forEach(organisation ->
            commandGateway.sendAndWait(new ImportOrganisationCommand(organisation.getId(), organisation)));

        log.info("Import of organisation with CREATE_IF_MISSING feature completed in {} seconds", (System.currentTimeMillis() - startTime) / 1000);
    }

}


