package store.dao;

/**
 * This interface declares a DAO manager for each class in the model
 * @author Mariana Ramirez
 * @version 1.0
 */

public interface DAOManager {
    ClientDao getClientDao();
    BillDao getBillDao();
    ItemDao getItemDao();
    ItemTypeDao getItemTypeDao();


}
