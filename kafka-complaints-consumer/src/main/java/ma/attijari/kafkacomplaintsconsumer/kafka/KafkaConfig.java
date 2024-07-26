package ma.attijari.kafkacomplaintsconsumer.kafka;

import ma.attijari.kafkacomplaintsconsumer.models.Complaint;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

        @Bean
        public ConsumerFactory<String, Complaint> consumerFactory() {
            Map<String, Object> config = new HashMap<>();
            config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
            config.put(ConsumerConfig.GROUP_ID_CONFIG, "complaint_listener");
            config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
            config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
            config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "ma.attijari.kafkacomplaintsconsumer.models.Complaint");
            config.put(JsonDeserializer.TYPE_MAPPINGS, "Complaint:ma.attijari.kafkacomplaintsconsumer.models.Complaint");
            JsonDeserializer<Complaint> jsonDeserializer = new JsonDeserializer<>(Complaint.class, false);
            jsonDeserializer.trustedPackages("*");
// Returning message in JSON format
            return new DefaultKafkaConsumerFactory<>(
                    config, new StringDeserializer(),
                    jsonDeserializer);
        }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Complaint> complaintListener() {
        ConcurrentKafkaListenerContainerFactory<String, Complaint> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
