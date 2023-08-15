package producerCrud.repository;

import producerCrud.conn.ConnectionFactory;
import producerCrud.domain.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
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
    public static void showProducerMetaData(){
        log.info("Showing Producer Metadata");
        String sql = "SELECT * FROM animeStore.producer;";
        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            ResultSetMetaData rsMd = rs.getMetaData();
            int columns = rsMd.getColumnCount();
            log.info("Columns count '{}'", columns);
            for (int i = 1; i<=columns; i++){
                log.info("Table name '{}' ", rsMd.getTableName(i));
                log.info("Colum name '{}' ", rsMd.getColumnName(i));
                log.info("Colum size '{}' ", rsMd.getColumnDisplaySize(i));
                log.info("Colum type '{}' ", rsMd.getColumnTypeName(i));
            }
        } catch (SQLException e){
            log.error("Error while trying to find producer", e);
        }
    }
    public static void showDriverMetaData(){
        log.info("Showing Driver Metadata");
        try (Connection con = ConnectionFactory.getConnection()){
            DatabaseMetaData DBmetaData = con.getMetaData();
            if (DBmetaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)){
                log.info("Supports TYPE_FORWARD_ONLY");
                if (DBmetaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)){
                    log.info("And supports CONCUR_UPDATABLE");
                }
            }
            if (DBmetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)){
                log.info("Supports TYPE_SCROLL_INSENSITIVE");
                if (DBmetaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)){
                    log.info("And supports CONCUR_UPDATABLE");
                }
            }
            if (DBmetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)){
                log.info("Supports TYPE_SCROLL_SENSITIVE");
                if (DBmetaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)){
                    log.info("And supports CONCUR_UPDATABLE");
                }
            }

        } catch (SQLException e){
            log.error("Error while trying to find producer", e);
        }
    }
    public static void showTypeScrollWorking() {
        String sql = "SELECT * FROM animeStore.producer;";
        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {
            log.info("Last row '{}' Row '{}'", rs.last(), rs.getRow());
            log.info(Producer.builder().name(rs.getString("name")).id(rs.getInt("id")).build());

            log.info("First row '{}' Row '{}'", rs.first(), rs.getRow());
            log.info(Producer.builder().name(rs.getString("name")).id(rs.getInt("id")).build());

            log.info("Row Absolute '{}' Row '{}'", rs.absolute(2), rs.getRow());
            log.info(Producer.builder().name(rs.getString("name")).id(rs.getInt("id")).build());

            log.info("Row Relative '{}' Row '{}'", rs.relative(-1), rs.getRow());
            log.info(Producer.builder().name(rs.getString("name")).id(rs.getInt("id")).build());

            log.info("Last row '{}' Row '{}'", rs.last(), rs.getRow());
            log.info("--------------------------------------------");
            rs.next();
            log.info("After last row '{}'", rs.isAfterLast());
            while(rs.previous()){
                log.info(Producer.builder().name(rs.getString("name")).id(rs.getInt("id"))
                        .build());
            }
        } catch (SQLException e) {
            log.error("Error while trying to find producer", e);
        }

    }
    public static List<Producer> findByNameAndToUpperCase(String name){
        String sql = "SELECT * FROM animeStore.producer WHERE name like '%%%s%%';"
                .formatted(name);
        List<Producer> producers = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                rs.updateString("name", rs.getString("name").toUpperCase());
                rs.updateRow();
                Producer producer = Producer
                        .builder()
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
    public static List<Producer> findByNameAndInsertWhenNotFound(String name){
        String sql = "SELECT * FROM animeStore.producer WHERE name like '%%%s%%';"
                .formatted(name);
        List<Producer> producers = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)){
            if (rs.next()) return producers;

            InsertNewProducer(name, rs);
            Producer producer = getProducer(rs);
            producers.add(producer);
        } catch (SQLException e){
            log.error("Error while trying to find producer", e);
        }
        return producers;
    }

    public static void findByNameAndDelete(String name){
        String sql = "SELECT * FROM animeStore.producer WHERE name like '%%%s%%';"
                .formatted(name);
        List<Producer> producers = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                log.info("Deleting '{}'", rs.getString("name"));
                rs.deleteRow();
            }
        } catch (SQLException e){
            log.error("Error while trying to find producer", e);
        }
    }

    private static void InsertNewProducer(String name, ResultSet rs) throws SQLException {
        rs.moveToInsertRow();
        rs.updateString("name", name);
        rs.insertRow();
    }

    private static Producer getProducer(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        rs.next();
        return Producer.builder().name(rs.getString("name")).id(rs.getInt("id"))
                .build();
    }
}