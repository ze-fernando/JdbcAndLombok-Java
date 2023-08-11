package crud.conn.test;

import crud.domain.Producer;
import crud.repository.ProducerRepository;

public class ConnectionFactoryTest01 {
    public static void main(String[] args){
        Producer producer = Producer.ProducerBuilder.builder().name("NHK").build();
        ProducerRepository.save(producer);
    }
}
