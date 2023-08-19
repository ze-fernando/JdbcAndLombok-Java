package producerCrud.conn.test;

import producerCrud.domain.Producer;
import producerCrud.service.ProducerService;

import java.util.List;

public class ConnectionFactoryTest03 {
    public static void main(String[] args) {
        Producer p1 = Producer.builder().name("TOEI ANIMATION").build();
        Producer p2 = Producer.builder().name("WHITE FOX").build();
        Producer p3 = Producer.builder().name("STUDIO GHIBLI").build();
        ProducerService.saveTransaction(List.of(p1, p2, p3));
    }
}
