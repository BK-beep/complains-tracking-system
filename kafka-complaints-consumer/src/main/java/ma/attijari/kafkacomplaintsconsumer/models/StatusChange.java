package ma.attijari.kafkacomplaintsconsumer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
class StatusChange {
    private ComplaintStatus oldStatus;
    private ComplaintStatus newStatus;
    private Date changeDate;
}