package crud.conn.test;

import crud.domain.Producer;
import crud.service.ProducerService;

public class ConnectionFactoryTest01 {
    public static void main(String[] args){
        Producer producer = Producer.builder().name("Studio Deen").build();
        ProducerService.delete(4);
    }
}
