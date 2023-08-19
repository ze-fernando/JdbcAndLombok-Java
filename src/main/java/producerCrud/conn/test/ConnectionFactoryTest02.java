package producerCrud.conn.test;

import lombok.extern.log4j.Log4j2;
import producerCrud.domain.Producer;
import producerCrud.repository.ProducerRepositoryRowSet;

@Log4j2
public class ConnectionFactoryTest02 {

    public static void main(String[] args) {
        Producer producerToUpdate = Producer.builder().id(2).name("NHK").build();
        ProducerRepositoryRowSet.updateCachedRowSet(producerToUpdate);
     //  log.info("------------------------------------");
     //  List<Producer> producers = ProducerServiceRowSet.findByNameJdbcRowSet("");
     //  log.info(producers);

    }
}
