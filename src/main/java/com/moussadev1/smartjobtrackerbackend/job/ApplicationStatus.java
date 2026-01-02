package com.moussadev1.smartjobtrackerbackend.job;

public enum ApplicationStatus {
    TO_APPLY, APPLIED, INTERVIEW, OFFER, REJECTED;

    public boolean canTransitionTo(ApplicationStatus target) {
        // Logique métier centralised ici
        return switch (this) {
            case TO_APPLY -> target == APPLIED || target == REJECTED;
            case APPLIED -> target == INTERVIEW || target == REJECTED;
            case INTERVIEW -> target == OFFER || target == REJECTED;
            default -> false; // Par sécurité
        };
    }
}
