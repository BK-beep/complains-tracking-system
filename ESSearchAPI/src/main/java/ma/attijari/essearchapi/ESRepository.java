package ma.attijari.essearchapi;

import ma.attijari.essearchapi.entities.Complaint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESRepository extends ElasticsearchRepository<Complaint,String> {
}
