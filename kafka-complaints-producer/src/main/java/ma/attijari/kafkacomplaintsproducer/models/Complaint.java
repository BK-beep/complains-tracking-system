package ma.attijari.kafkacomplaintsproducer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {
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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class Complaint {

    @JsonProperty("complaintId")
    private String complaintId;

    @JsonProperty("from")
    private Client from;

    @JsonProperty("madeAt")
    private Date madeAt;

    @JsonProperty("status")
    private ComplaintStatus status;

    @JsonProperty("source")
    private ComplaintSource source;

    @JsonProperty("description")
    private String description;

}

 */