package producerCrud.service;

import producerCrud.domain.Producer;
import producerCrud.repository.ProducerRepository;

import java.util.List;

public class ProducerService {

    public static void save(Producer producer){
        ProducerRepository.save(producer);
    }
    public static void delete(Integer id){
        requireValidId(id);
        ProducerRepository.delete(id);
    }
    public static void update(Producer producer){
        requireValidId(producer.getId());
        ProducerRepository.update(producer);
    }
    public static List<Producer> findAll(){
        return ProducerRepository.findAll();
    }
    public static List<Producer> findByName(String name){
        return ProducerRepository.findByName(name);
    }
    public static void showProducerMetaData(){
        ProducerRepository.showProducerMetaData();
    }
    public static void showDriverMetaData(){
        ProducerRepository.showDriverMetaData();
    }

    public static void showTypeScrollWorking(){
        ProducerRepository.showTypeScrollWorking();
    }
    public static List<Producer> findByNameAndToUpperCase(String name){
        return ProducerRepository.findByNameAndToUpperCase(name);
    }
    public static List<Producer> findByNameAndInsertWhenNotFound(String name){
        return ProducerRepository.findByNameAndInsertWhenNotFound(name);
    }
    public static void findByNameAndDelete(String name){
        ProducerRepository.findByNameAndDelete(name);
    }

   private static void requireValidId(Integer id){
        if (id == null || id <= 0){
            throw new IllegalArgumentException("Invalid value for id");
        }
    }
}