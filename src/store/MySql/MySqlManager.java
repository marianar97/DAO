package store.MySql;

import store.DAOException;
import store.dao.*;
import store.model.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * This class implements a Singleton pattern to make sure that
 * there is only one connection to the database per table
 * @author Mariana Ramirez Duque
 * @version 1.0
 */
public class MySqlManager implements DAOManager {
    private Connection conn;

    private ClientDao clients = null;
    private BillDao bills = null;
    private ItemDao item = null;
    private ItemTypeDao itemType = null;

    /**
     * Creates a connection with the database
     * @param host     The host of the database
     * @param username The username of the database
     * @param password The password of the database
     * @param database The name of the database
     * @throws SQLException
     */
    public MySqlManager(String host, String username, String password, String database) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database, username, password);
    }

    /**
     * This method ensures that there is only one ClientDao instance
     * @return a ClientDao object
     */
    @Override
    public ClientDao getClientDao() {
        if (clients == null ){
            clients = new MySqlClientDao(conn);
        }
        return clients;
    }

    /**
     * This method ensures that there is only one BillDao instance
     * @return A BillDao object
     */
    @Override
    public BillDao getBillDao() {
        if(bills == null){
            bills = new MySqlClientBill(conn);
        }
        return bills;
    }

    /**
     * This method ensures that there is only one ItemDao instance
     * @return An ItemDao object
     */
    @Override
    public ItemDao getItemDao() {
        if (item == null){
            item = new MySqlItemDao(conn);
        }
        return item;
    }

    /**
     * This method ensures that there is only one ItemTypeDao instance
     * @return An ItemType object
     */
    @Override
    public ItemTypeDao getItemTypeDao() {
        if (itemType == null) {
            itemType = new MySqlItemTypeDao(conn);
        }
        return itemType;
    }


}
