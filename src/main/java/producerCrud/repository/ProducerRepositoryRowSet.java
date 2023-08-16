package producerCrud.repository;

import producerCrud.conn.ConnectionFactory;
import producerCrud.domain.Producer;
import producerCrud.listener.CustomRowSetListener;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducerRepositoryRowSet {

    public static List<Producer> findByNameJdbcRowSet(String name) {
        String sql = "SELECT * FROM animeStore.producer WHERE name like ?;";
        List<Producer> producers = new ArrayList<>();
        try (JdbcRowSet jrs = ConnectionFactory.getJdbcRowSet()) {
            jrs.addRowSetListener(new CustomRowSetListener());
            jrs.setCommand(sql);
            jrs.setString(1, String.format("%%%s%%", name));
            jrs.execute();
            while (jrs.next()) {
                Producer producer = Producer.builder()
                        .name(jrs.getString("name"))
                        .id(jrs.getInt("id"))
                        .build();
                producers.add(producer);}
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return producers;

    }

    public static void UpdateJdbcRowSet(Producer producer) {
        String sql = "SELECT * FROM animeStore.producer WHERE(`id` = ?);";
        try (JdbcRowSet jrs = ConnectionFactory.getJdbcRowSet()) {
            jrs.addRowSetListener(new CustomRowSetListener());
            jrs.setCommand(sql);
            jrs.setInt(1, producer.getId());
            jrs.execute();
            if (!jrs.next()) return;
            jrs.updateString("name", producer.getName());
            jrs.updateRow();
        } catch(SQLException e) {
            e.printStackTrace();
       }
    }

}
