package ma.attijari.kafkacomplainsproducer.services;

import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplainsproducer.models.Complaint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Complaint> kafkaTemplate;
    private final String TOPIC_NAME= "complaints";

    public void sendMessage(Complaint message) {
        kafkaTemplate.send(TOPIC_NAME, message);
        System.out.println("Complaint " + message +
                " has been sucessfully sent to the topic: " + TOPIC_NAME);
    }
}
