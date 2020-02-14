package tg.nmgroup.store;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("tg.nmgroup.store");

        noClasses()
            .that()
                .resideInAnyPackage("tg.nmgroup.store.service..")
            .or()
                .resideInAnyPackage("tg.nmgroup.store.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..tg.nmgroup.store.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
