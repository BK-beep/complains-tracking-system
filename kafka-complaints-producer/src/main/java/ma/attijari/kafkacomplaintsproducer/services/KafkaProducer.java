package ma.attijari.kafkacomplaintsproducer.services;

import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplaintsproducer.models.Complaint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Complaint> kafkaTemplate;
    private final String TOPIC_NAME= "complaints";

    public void sendMessage(Complaint message) {
        kafkaTemplate.send(TOPIC_NAME, message);
        System.out.println("Complaint " + message.toString() +
                " has been sucessfully sent to the topic: " + TOPIC_NAME);
    }
}
