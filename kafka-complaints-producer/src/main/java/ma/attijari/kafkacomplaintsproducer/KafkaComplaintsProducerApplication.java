package ma.attijari.kafkacomplaintsproducer;
import lombok.AllArgsConstructor;
import ma.attijari.kafkacomplaintsproducer.models.Client;
import ma.attijari.kafkacomplaintsproducer.models.Complaint;
import ma.attijari.kafkacomplaintsproducer.models.ComplaintSource;
import ma.attijari.kafkacomplaintsproducer.models.ComplaintStatus;
import ma.attijari.kafkacomplaintsproducer.services.ComplainPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.*;
import java.util.stream.Stream;
@SpringBootApplication
@AllArgsConstructor
public class KafkaComplaintsProducerApplication {
	private static ComplainPublisher complainPublisher;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(KafkaComplaintsProducerApplication.class, args);
		ComplainPublisher complainPublisher = context.getBean(ComplainPublisher.class);
		int status = new Random().nextInt(ComplaintStatus.values().length);
		int source = new Random().nextInt(ComplaintSource.values().length);
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				Stream.of(
						new Client(UUID.randomUUID().toString(), "Salma", "salma@gmail.com", "098765"),
						new Client(UUID.randomUUID().toString(), "Manal", "manal@gmail.com", "024567"),
						new Client(UUID.randomUUID().toString(), "Lamiaa", "lamiaa@gmail.com", "099965"),
						new Client(UUID.randomUUID().toString(), "Khaoula", "khaoula@gmail.com", "076421"),
						new Client(UUID.randomUUID().toString(), "Meryem", "meryem@gmail.com", "090000"),
						new Client(UUID.randomUUID().toString(), "Hiba", "hiba@gmail.com", "090005"),
						new Client(UUID.randomUUID().toString(), "Arij", "arij@gmail.com", "093335"),
						new Client(UUID.randomUUID().toString(), "Nisrine", "nisrine@gmail.com", "0936475"),
						new Client(UUID.randomUUID().toString(), "Yasmine", "yasmine@gmail.com", "0986325"),
						new Client(UUID.randomUUID().toString(), "Mohammed", "mohammed@gmail.com", "0087465")
				).forEach(user -> {
					complainPublisher.send(new Complaint(
							UUID.randomUUID().toString(),
							user,
							new Date(),
							ComplaintStatus.values()[status],
							ComplaintSource.values()[source],
							"test"
					));
				});
			}
		};
		Timer timer=new Timer();
		timer.schedule(task,0,3600000);
	}
}
