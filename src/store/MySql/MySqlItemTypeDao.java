package store.MySql;

import store.DAOException;
import store.dao.ItemTypeDao;
import store.model.ItemType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class realize CRUD operations over the item_type
 * table in the database
 * @author Mariana Ramírez Duque
 * @version 1.0
 */

public class MySqlItemTypeDao implements ItemTypeDao {

    final String INSERT = "INSERT INTO item_type (description)" +
            "VALUES(?)";
    final String UPDATE = "UPDATE item_type SET description = ?" +
            "WHERE id_item_type = ?";
    final String DELETE = "DELETE FROM item_type WHERE id_item_type = ?";
    final String GETALL = "SELECT  * FROM item_type";
    final String GETONE = "SELECT * FROM item_type WHERE id_item_type = ?";

    private Connection conn;

    /**
     * Creates a MySqlItemTypeDao object with it's corresponding parameters
     * @param conn The connection to the database
     */
    public MySqlItemTypeDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Executes an insert query in the item_type table in the database
     * @param a An itemType object to insert into the database
     * @throws DAOException if insert query could not be execute successfully
     */
    @Override
    public void create(ItemType a) throws DAOException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getDescription());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Changes may not be saved");
            }

        } catch(SQLException e) {
            throw new DAOException("Error in SQL ", e);
        }
        finally {
            if (stat!= null) {
                try{
                    stat.close();
                }catch (SQLException e){
                    throw new DAOException("Error in SQL", e );
                }
            }
        }
    }

    /**
     * Executes an update query in the item_type table in the database
     * @param a An itemType object to update it's attributes
     * @throws DAOException if the update query could not be execute successfully
     */
    @Override
    public void update(ItemType a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getDescription());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Changes may not be saved");
            }

        } catch(SQLException e) {
            throw new DAOException("Error in SQL ", e);
        }
        finally {
            if (stat!= null) {
                try{
                    stat.close();
                }catch (SQLException e){
                    throw new DAOException("Error in SQL", e );
                }
            }
        }
    }

    /**
     * Executes a delete query in the item_type table in the  database
     * @param a  The itemType object to delete
     * @throws DAOException if the delete query could not be execute successfully
     */
    @Override
    public void delete(ItemType a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, a.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Changes may not be saved");
            }

        } catch(SQLException e) {
            throw new DAOException("Error in SQL ", e);

        }
        finally {
            if (stat!= null) {
                try{
                    stat.close();
                }catch (SQLException e){
                    throw new DAOException("Error in SQL", e );
                }
            }
        }
    }

    /**
     * This method gets the itemType's attributes from the database and use them
     * to create an itemType object
     * @param rs A table of data from the item_type table in the database
     * @return An itemType object
     * @throws SQLException if itemType's attributes could not be taken from the ResultSet
     */
    private ItemType convert(ResultSet rs) throws SQLException{
        String description = rs.getString("description");

        ItemType itemType = new ItemType(description);
        itemType.setId(rs.getInt(1));

        return itemType;
    }

    /**
     * Gets all the data from the item_type table in the database
     * @return A list of itemType objects representing all of itemTypes in the database
     * @throws DAOException if the GETALL query could not be execute successfully
     */
    @Override
    public List<ItemType> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ItemType> itemsTypes = new ArrayList<>();

        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                itemsTypes.add(convert(rs));
            }
        }catch(SQLException e){
            throw new DAOException("Error in SQL", e);
        } finally {
            if(rs != null) {
                try{
                    rs.close();
                }catch (SQLException e){
                    new DAOException("Error in SQL", e);
                }
            }

            if (stat != null){
                try{
                    stat.close();
                } catch(SQLException e) {
                    new DAOException("Error in SQL", e);

                }
            }
        }
        return itemsTypes;
    }

    /**
     * Get the data from the itemType with the given id
     * @param id An Int representing the itemType's id from which to obtain the data
     * @return An itemType object
     * @throws DAOException if the GETONE query could not be execute successfully
     */
    @Override
    public ItemType get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        ItemType a = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1,  id);
            rs = stat.executeQuery();
            if (rs.next()){
                a = convert(rs);
            } else {
                throw  new DAOException("Register not found");
            }
        }catch(SQLException e){
            throw new DAOException("Error in SQL", e);
        } finally {
            if(rs != null) {
                try{
                    rs.close();
                }catch (SQLException e){
                    new DAOException("Error in SQL", e);
                }
            }

            if (stat != null){
                try{
                    stat.close();
                } catch(SQLException e) {
                    new DAOException("Error in SQL", e);

                }
            }
        }
        return a;
    }


}
