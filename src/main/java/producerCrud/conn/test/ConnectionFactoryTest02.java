package producerCrud.conn.test;

import lombok.extern.log4j.Log4j2;
import producerCrud.domain.Producer;
import producerCrud.service.ProducerServiceRowSet;

import java.util.List;

@Log4j2
public class ConnectionFactoryTest02 {

    public static void main(String[] args) {
        Producer producerToUpdate = Producer.builder().id(6).name("BONES").build();
        ProducerServiceRowSet.UpdateJdbcRowSet(producerToUpdate);
        log.info("-------------------------------------");
        List<Producer> producers = ProducerServiceRowSet.findByNameJdbcRowSet("");
        log.info(producers);
    }
}
