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
            log.info("DB rows affected {}",rowsAffect);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
