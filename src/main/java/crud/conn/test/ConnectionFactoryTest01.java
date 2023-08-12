package crud.conn.test;

import crud.domain.Producer;
import crud.service.ProducerService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ConnectionFactoryTest01 {
    public static void main(String[] args){
        Producer producer = Producer.builder().name("Mad House").build();
        // ProducerService.save(producer);
        // ProducerService.delete(4);
        Producer producerToUpdate = Producer.builder().id(1).name("MadHouse").build();
        //ProducerService.update(producerToUpdate);
        List<Producer> producers = ProducerService.findAll();
        //List<Producer> producers = ProducerService.findByName("Mad");
        log.info("Producers found '{}'", producers);
    }
}
