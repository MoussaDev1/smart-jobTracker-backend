package com.moussadev1.smartjobtrackerbackend.job;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApplicationStatusTest {
    @ParameterizedTest(name = "{0} vers {1} devrait être {2}")
    @CsvSource({
            // Cas PASSANTS (True)
            "TO_APPLY, APPLIED, true",
            "APPLIED, INTERVIEW, true",
            "APPLIED, REJECTED, true",
            "INTERVIEW, OFFER, true",

            // Cas REFUSÉS (False) - Quelques exemples critiques
            "TO_APPLY, OFFER, false",       // Saut d'étape interdit
            "TO_APPLY, INTERVIEW, false",   // Saut d'étape interdit
            "REJECTED, APPLIED, false",     // Impossible de revenir en arrière (selon ta logique)
            "OFFER, INTERVIEW, false"       // Impossible de revenir en arrière
    })
    void shouldValidateTransition(ApplicationStatus current, ApplicationStatus next, boolean expectedResult) {
        // WHEN
        boolean result = current.canTransitionTo(next);
        // THEN
        assertThat(result).isEqualTo(expectedResult);
    }
}
