package ma.attijari.kafkacomplainsproducer.services;

import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplainsproducer.models.Complaint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplainPublisher {
    private final KafkaProducer producer;
    public void send(Complaint complaint) {
        producer.sendMessage(complaint);
    }
}
