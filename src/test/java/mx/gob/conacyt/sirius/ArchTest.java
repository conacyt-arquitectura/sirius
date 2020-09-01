package mx.gob.conacyt.sirius;

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
            .importPackages("mx.gob.conacyt.sirius");

        noClasses()
            .that()
                .resideInAnyPackage("mx.gob.conacyt.sirius.service..")
            .or()
                .resideInAnyPackage("mx.gob.conacyt.sirius.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..mx.gob.conacyt.sirius.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
