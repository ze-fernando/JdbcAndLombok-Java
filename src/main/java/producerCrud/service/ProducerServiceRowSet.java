package producerCrud.service;

import producerCrud.domain.Producer;
import producerCrud.repository.ProducerRepositoryRowSet;

import java.util.List;

public class ProducerServiceRowSet {

    public static List<Producer> findByNameJdbcRowSet(String name) {
        return ProducerRepositoryRowSet.findByNameJdbcRowSet(name);
    }

    public static void UpdateJdbcRowSet(Producer producer) {
        ProducerRepositoryRowSet.UpdateJdbcRowSet(producer);
    }

}
