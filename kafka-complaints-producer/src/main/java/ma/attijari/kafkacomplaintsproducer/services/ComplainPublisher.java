package ma.attijari.kafkacomplaintsproducer.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplaintsproducer.models.Complaint;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplainPublisher {
    private final KafkaProducer producer;
    public void send(Complaint complaint) {
        producer.sendMessage(complaint);
    }
}
