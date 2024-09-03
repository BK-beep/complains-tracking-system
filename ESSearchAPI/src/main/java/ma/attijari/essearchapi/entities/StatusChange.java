package ma.attijari.essearchapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusChange {
    private ComplaintStatus oldStatus;
    private ComplaintStatus newStatus;
    private Date changeDate;
}
