package ma.attijari.kafkacomplaintsconsumer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Complaint {
    private String complaintId;

    private Client from;

    private Date madeAt;

    private ComplaintStatus status;

    private ComplaintSource source;

    private String description;
}
