package ma.attijari.kafkacomplaintsproducer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    // Getters and Setters
}
