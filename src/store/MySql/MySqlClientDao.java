package store.MySql;

import store.DAOException;
import store.dao.ClientDao;
import store.dao.DAO;
import store.model.Client;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * This class realize CRUD operations over the client
 * table in the database
 * @author Mariana Ramírez Duque
 * @version 1.0
 */
public class MySqlClientDao implements ClientDao {

    final String INSERT = "INSERT INTO client(name, last_name, gender, birth_date, civil_status) " +
            "VALUES(?,?,?,?,?)";
    final String UPDATE = "UPDATE client SET name = ?, last_name = ?, gender = ?, birth_date =?, civil_status=?" +
            "WHERE id_client = ?";
    final String DELETE = "DELETE FROM client WHERE id_client = ?";
    final String GETALL = "SELECT  * FROM client";
    final String GETONE = "SELECT * FROM client WHERE id_client = ?";


    private Connection conn;

    /**
     *Creates a MysqlClientDao object with it's corresponding parameters
     * @param conn The connection to the database
     */
    public MySqlClientDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Executes an insert query in the client table in the database
     * @param a A Client object to insert into the database
     * @throws DAOException if the insert f insert query could not be execute successfully
     */
    @Override
    public void create(Client a) throws DAOException {
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(INSERT);
            statement.setString(1,a.getName());
            statement.setString(2, a.getLastName());
            statement.setString(3, a.getGender());
            statement.setDate(4, new Date(a.getBirthDate().getTime()));
            statement.setString(5, a.getCivilStatus());
            if(statement.executeUpdate() == 0 ){
                throw new DAOException("Could not add the client");
            }
        } catch (SQLException e) {
            throw new DAOException("Could not add the client", e);
        } finally {
            if (statement!= null) {
                try{
                    statement.close();
                }catch (SQLException e){
                    throw new DAOException("Error in SQL", e );
                }
            }
        }

    }

    /**
     * Executes an update query in the client table in the database
     * @param a A client object to update it's attributes
     * @throws DAOException if the update query could not be execute successfully
     */
    @Override
    public void update(Client a) throws DAOException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1,a.getName());
            stat.setString(2, a.getLastName());
            stat.setString(3, a.getGender());
            stat.setDate(4, new Date(a.getBirthDate().getTime()));
            stat.setString(5 , a.getCivilStatus());
            stat.setInt(6,a.getClientId());
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
     * Executes a delete query in the client table in the  database
     * @param a The client object to delete
     * @throws DAOException if the delete query could not be execute successfully
     */
    @Override
    public void delete(Client a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, a.getClientId());
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
     * This method gets the client´s  attributes from the database and use them
     *  to create a client object
     * @param rs A table of data from the client table in the database
     * @return A client object
     * @throws SQLException if client´s attributes could not be taken from the ResultSet
     */
    private Client convert(ResultSet rs) throws SQLException{
        String name = rs.getString("name");
        String last_name = rs.getString("last_name");
        String gender = rs.getString("gender");
        Date birth_date = rs.getDate("birth_date");
        String civil_status = rs.getString("civil_status");
        Client client = new Client(name,last_name,gender,birth_date,civil_status);
        client.setClientId(rs.getInt("id_client"));
        return client;
    }

    /**
     * Gets all the data from the client table in the database
     * @return A list of client objects representing all of clients in the database
     * @throws DAOException if the GETALL query could not be execute successfully
     */
    @Override
    public List<Client> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Client> clients = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                clients.add(convert(rs));
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
        return clients;
    }

    /**
     * Get the data from the client with the given id
     * @param id an Int representing the client's id from which to obtain the data
     * @return A client object
     * @throws DAOException if the GETONE query could not be execute successfully
     */
    @Override
    public Client get(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Client a = null;
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


