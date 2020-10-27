package nl.rnsd.organisation.importer;

import nl.rnsd.organisation.api.Organisation;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class OrganisationDataMock {

    @NotNull
    public static Stream<Organisation> getOrganisations(int nr) {
        List<Organisation> newOrganisations = createBaseSetOfSize(nr);
        Stream<Organisation> updatedOrganisations = newOrganisations.stream()
            .map(org -> createOrganisation(org.getId(), org.getName() + "-update"));
        return Stream.concat(newOrganisations.stream(), updatedOrganisations);
    }


    private static List<Organisation> createBaseSetOfSize(int nr) {
        return IntStream.range(0, nr)
            .mapToObj(i -> createOrganisation(UUID.randomUUID().toString(), "name-" + i))
            .collect(toList());
    }

    public static Organisation createOrganisation(String id, String name) {
        return new Organisation(id, name,
            null, null, null, null, null, null, null, null, null, null);
    }
}
