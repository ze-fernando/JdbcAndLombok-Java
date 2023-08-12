package crud.repository;

import crud.conn.ConnectionFactory;
import crud.domain.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


@Log4j2
public class ProducerRepository {
    public static void save(Producer producer){
        String sql = "INSERT INTO `animeStore`.`producer` (`name`) VALUES ('%s');".formatted(producer.getName());
        try (Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement()){
            int rowsAffect = stmt.executeUpdate(sql);
            log.info("Insert '{}' in db rows affected {}",producer.getName(),rowsAffect);
        } catch (SQLException e){
            log.error("Error while trying to insert producer '{}' ", producer.getName(), e);
        }
    }
    public static void delete(int id){
        String sql = "DELETE FROM `animeStore`.`producer` WHERE (`id` = '%d');".formatted(id);
        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement()){
            int rowsAffect = stmt.executeUpdate(sql);
            log.info("Deleted '{}' in db rows affected {}",id ,rowsAffect);
        } catch (SQLException e){
            log.error("Error while trying to insert producer '{}' ", id, e);
        }
    }

}
