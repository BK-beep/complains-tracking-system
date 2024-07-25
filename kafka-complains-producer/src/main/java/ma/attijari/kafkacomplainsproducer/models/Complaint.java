package ma.attijari.kafkacomplainsproducer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Complaint {
    @JsonProperty("complainId")
    private String complainId;

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