package ma.attijari.kafkacomplaintsconsumer.kafka;

import lombok.RequiredArgsConstructor;
import ma.attijari.kafkacomplaintsconsumer.elasticsearch.ESController;
import ma.attijari.kafkacomplaintsconsumer.models.Complaint;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component

// Class
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ESController controller;
    @KafkaListener(topics = "complaints",
            groupId = "complaints_listeners",
            containerFactory = "complaintListener")

    // Method
    public void
    consume(Complaint complaint)
    {
        // Print statement
        System.out.println("message = " + complaint.toString());
        controller.createComplaint(complaint);
    }
}