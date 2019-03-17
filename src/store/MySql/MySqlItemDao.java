package store.MySql;

import store.DAOException;
import store.dao.ItemDao;
import store.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class realize CRUD operations over the item
 * table in the database
 * @author Mariana Ramírez Duque
 * @version 1.0
 */

public class MySqlItemDao implements ItemDao {

    final String INSERT = "INSERT INTO item ( item_type, description, unit_value)" +
            "VALUES(?,?,?)";
    final String UPDATE = "UPDATE item SET item_type = ?, description = ?, unit_value = ? WHERE id_item = ? ";
    final String DELETE = "DELETE FROM item WHERE id_item = ?";
    final String GETALL = "SELECT  * FROM item";
    final String GETONE = "SELECT * FROM item WHERE id_item = ? ";


    private Connection conn;

    /**
     * Creates a MysqlItemDao object with it's corresponding parameters
     * @param conn The connection to the database
     */
    public MySqlItemDao(Connection conn){
        this.conn = conn;
    }

    /**
     * Executes an insert query in the item table in the database
     * @param a An item object to insert into the database
     * @throws DAOException if insert query could not be execute successfully
     */
    @Override
    public void create(Item a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1,a.getItemT());
            stat.setString(2, a.getDescription());
            stat.setFloat(3, a.getUnitValue());
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
     * Executes an update query in the item table in the database
     * @param a An item object to update it's attributes
     * @throws DAOException if the update query could not be execute successfully
     */
    @Override
    public void update(Item a) throws DAOException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setInt(1,a.getItemT());
            stat.setString(2,a.getDescription());
            stat.setFloat(3,a.getUnitValue());
            stat.setInt(4, a.getId());
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
     * Executes a delete query in the item table in the  database
     * @param a The item object to delete
     * @throws DAOException if the delete query could not be execute successfully
     */
    @Override
    public void delete(Item a) throws DAOException {
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
     * This method gets the item´s attributes from the database and use them
     * to create an item object
     * @param rs A table of data from the item table in the database
     * @return An item object
     * @throws SQLException if item´s attributes could not be taken from the ResultSet
     */
    private Item convert(ResultSet rs) throws SQLException{
        int item_type = rs.getInt("item_type");
        String description = rs.getString("description");
        Float unit_value = rs.getFloat("unit_value");

        Item item = new Item(item_type, description, unit_value);
        item.setId(rs.getInt(1));

        return item;
    }

    /**
     * Gets all the data from the item table in the database
     * @return A list of Item objects representing all of items in the dataset
     * @throws DAOException if the GETALL query could not be execute successfully
     */
    @Override
    public List<Item> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();

        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                items.add(convert(rs));
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
        return items;
    }


    /**
     * Get the data from the item with the given id
     * @param id An Int representing the items's id from which to obtain the data
     * @return An item object
     * @throws DAOException if the GETONE query could not be execute successfully
     */
    @Override
    public Item get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Item a = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
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
