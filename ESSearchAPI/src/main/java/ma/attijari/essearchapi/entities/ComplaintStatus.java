package ma.attijari.essearchapi.entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ComplaintStatus {
    New("New"),
    IN_PROGRESS("In Progress"),
    RESOLVED("Resolved"),
    CLOSED("Closed");
    private final String status;
}
