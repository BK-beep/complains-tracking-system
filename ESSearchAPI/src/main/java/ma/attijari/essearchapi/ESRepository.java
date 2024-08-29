package ma.attijari.essearchapi;

import ma.attijari.essearchapi.entities.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESRepository extends ElasticsearchRepository<Complaint,String> {
    Page<Complaint> findAll(Pageable pageable);
}
