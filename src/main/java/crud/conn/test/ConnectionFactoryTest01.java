package crud.conn.test;

import crud.domain.Producer;
import crud.repository.ProducerRepository;

public class ConnectionFactoryTest01 {
    public static void main(String[] args){
        Producer producer = Producer.builder().name("Studio Deen").build();
        ProducerRepository.save(producer);
    }
}
