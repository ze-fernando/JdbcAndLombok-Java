package crud.repository;

import crud.conn.ConnectionFactory;
import crud.domain.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class ProducerRepository {
    public static void save(Producer producer){
        String sql = "INSERT INTO `animeStore`.`producer` (`name`) VALUES ('%s');".formatted(producer.getName());
        try (Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement()){
            int rowsAffect = stmt.executeUpdate(sql);
            log.info("Insert producer '{}' in db rows affected {}",producer.getName(),rowsAffect);
        } catch (SQLException e){
            log.error("Error while trying to insert producer '{}' ", producer.getName(), e);
        }
    }
    public static void delete(int id){
        String sql = "DELETE FROM `animeStore`.`producer` WHERE (`id` = '%d');".formatted(id);
        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement()){
            int rowsAffect = stmt.executeUpdate(sql);
            log.info("Deleted producer '{}' in db rows affected {}",id ,rowsAffect);
        } catch (SQLException e){
            log.error("Error while trying to delete producer '{}' ", id, e);
        }
    }
    public static void update(Producer producer){
        String sql = "UPDATE `animeStore`.`producer` SET `name` = '%s ' WHERE (`id` = '%d');"
                .formatted(producer.getName(), producer.getId());
        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement()){
            int rowsAffect = stmt.executeUpdate(sql);
            log.info("Updated producer '{}' in db rows affected {}",producer.getId() ,rowsAffect);
        } catch (SQLException e){
            log.error("Error while trying to update producer '{}' ", producer.getId(), e);
        }
    }
    public static List<Producer> findAll(){
        log.info("Find all Producers");
        return findByName("");
    }

    public static List<Producer> findByName(String name){
        String sql = "SELECT * FROM animeStore.producer WHERE name like '%%%s%%';"
                .formatted(name);
        List<Producer> producers = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                Producer producer = Producer.builder()
                        .name(rs.getString("name"))
                        .id(rs.getInt("id"))
                        .build();
                producers.add(producer);
            }
        } catch (SQLException e){
            log.error("Error while trying to find producer", e);
        }
        return producers;
    }

}
