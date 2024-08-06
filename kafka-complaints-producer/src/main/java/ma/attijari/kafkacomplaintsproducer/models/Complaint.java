package ma.attijari.kafkacomplaintsproducer.models;

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