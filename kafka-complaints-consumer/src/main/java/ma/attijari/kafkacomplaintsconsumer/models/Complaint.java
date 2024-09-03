package ma.attijari.kafkacomplaintsconsumer.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "complaints")
public class Complaint {
    @Id
    private String complaintId;
    private Client from;
    private Date madeAt;
    private ComplaintStatus status;
    private ComplaintSource source;
    private String description;
    private Date lastModified;
    private List<StatusChange> statusHistory = new ArrayList<>();



    public void updateStatus(ComplaintStatus newStatus) {
        StatusChange change = new StatusChange(this.status, newStatus, new Date());
        this.statusHistory.add(change);
        this.status = newStatus;
        this.lastModified = new Date();
    }

}

/*
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


 */