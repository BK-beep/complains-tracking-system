package ma.attijari.kafkacomplaintsconsumer.kafka;

import ma.attijari.kafkacomplaintsconsumer.models.Complaint;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component

// Class
public class KafkaConsumer {

    @KafkaListener(topics = "complaints",
            groupId = "complaints_listeners",
            containerFactory = "complaintListener")

    // Method
    public void
    consume(Complaint complaint)
    {
        // Print statement
        System.out.println("message = " + complaint.toString());
    }
}