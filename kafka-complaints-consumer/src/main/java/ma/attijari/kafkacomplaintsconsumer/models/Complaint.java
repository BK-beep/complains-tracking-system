package ma.attijari.kafkacomplaintsconsumer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document(indexName = "complaints")
public class Complaint {
    @Id
    private String complaintId;

    private Client from;

    private Date madeAt;

    private ComplaintStatus status;

    private ComplaintSource source;

    private String description;
}
