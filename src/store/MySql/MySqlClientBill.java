package store.MySql;

import store.DAOException;
import store.dao.BillDao;
import store.model.Bill;
import store.model.Client;

import javax.sql.rowset.serial.SerialArray;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class realize CRUD operations over the bill
 * table in the database
 * @author Mariana Ramírez Duque
 * @version 1.0
 */
public class MySqlClientBill implements BillDao {

    final String INSERT = "INSERT INTO bill( date, client_id)" +
            "VALUES(?,?)";
    final String UPDATE = "UPDATE bill SET date = ?, client_id = ? WHERE id_bill = ?";
    final String DELETE = "DELETE FROM bill WHERE id_bill = ?";
    final String GETALL = "SELECT  * FROM bill";
    final String GETONE = "SELECT * FROM bill WHERE id_bill = ?";


    private Connection conn;

    /**
     * Creates a MySqlClientBill object with it's corresponding parameters
     * @param conn  The connection to the database
     */
    public MySqlClientBill(Connection conn){
        this.conn = conn;
    }

    /**
     * Executes an insert query in the bill table in the database
     * @param bill A bill object to insert into the database
     * @throws DAOException  if insert query could not be execute successfully
     */
    @Override
    public void create(Bill bill) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = conn.prepareStatement(INSERT);
            stat.setDate(1, new Date(bill.getDate().getTime()));
            stat.setInt(2, bill.getClientId());


            if (stat.executeUpdate() == 0) {
                throw new DAOException("Could not add the bill");
            }
        } catch(SQLException e) {
            throw new DAOException("Could not add the bill ", e);
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
     * Executes an update query in the bill table in the database
     * @param a A bill object to update it's attributes
     * @throws DAOException if the update query could not be execute successfully
     */
    @Override
    public void update(Bill a) throws DAOException{
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setDate(1, new Date(a.getDate().getTime()));
            stat.setInt(2, a.getClientId());
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
     * Executes a delete query in the bill table in the  database
     * @param a The bill object to delete
     * @throws DAOException if the delete query could not be execute successfully
     */
    @Override
    public void delete(Bill a)  throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, a.getBillId());
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
     * This method gets the bill´s attributes from the database and use them
     * to create a bill object
     * @param rs A table of data from the bill table in the database
     * @return A bill object
     * @throws SQLException if bill´s attributes could not be taken from the ResultSet
     */
    private Bill convert(ResultSet rs) throws SQLException{
        Date date = rs.getDate("date");
        int client_id = rs.getInt("client_id");

        Bill bill = new Bill(date,client_id);
        bill.setBillId(rs.getInt(1));

        return bill;
    }

    /**
     * Gets all the data from the bill table in the database
     * @return A list of Bill objects representing all of bills in the database
     * @throws DAOException if the GETALL query could not be execute successfully
     */
    @Override
    public List<Bill> getAll()  throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Bill> bills = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                bills.add(convert(rs));
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
        return bills;
    }

    /**
     * Get the data from the bill with the given id
     * @param id an int representing the bill's id from which to obtain the data
     * @return A bill object
     * @throws DAOException if the GETONE query could not be execute successfully
     */
    @Override
    public Bill get(Integer id) throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        Bill a = null;
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

    /*public static void main(String[] args) throws SQLException, DAOException {
        Connection conn = null;

            conn = DriverManager.getConnection("jdbc:mysql://localhost/storedb", "root", "1234");
            BillDao dao = new MySqlClientBill(conn);
            Bill bill = new Bill(new Date(0,1,11),3);
            dao.create(bill);
            List<Bill> bills = dao.getAll();
            for (Bill b: bills){
                System.out.println(b.toString());
            }
    }*/
}
