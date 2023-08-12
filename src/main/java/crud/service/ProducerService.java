package crud.service;

import crud.domain.Producer;
import crud.repository.ProducerRepository;

public class ProducerService {

    public static void save(Producer producer){
        ProducerRepository.save(producer);
    }
    public static void delete(int id){
        if (id <= 0){
            throw new IllegalArgumentException("Invalid value for id");
        }
        ProducerRepository.delete(id);
    }
}
