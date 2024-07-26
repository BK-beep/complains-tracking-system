package ma.attijari.kafkacomplaintsconsumer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String id;
    private String name;
    private String email;
    private String phone;
}
