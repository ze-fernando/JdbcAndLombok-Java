package crud.conn.test;

import crud.conn.ConnectionFactory;

import java.sql.SQLException;

public class ConnectionFactoryTest {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory.getConnection();
    }
}
