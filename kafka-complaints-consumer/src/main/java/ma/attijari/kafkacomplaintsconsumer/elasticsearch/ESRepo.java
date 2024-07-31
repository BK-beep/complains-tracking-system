package ma.attijari.kafkacomplaintsconsumer.elasticsearch;

import ma.attijari.kafkacomplaintsconsumer.models.Complaint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ESRepo extends ElasticsearchRepository<Complaint,String> {

}
