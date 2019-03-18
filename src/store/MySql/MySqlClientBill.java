package store.MySql;

import store.DAOException;
import store.dao.BillDao;
import store.model.Bill;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * This class realize CRUD operations over the bill
 * table in the database
 * @author Mariana Ramírez Duque
 * @version 1.0
 */
public class MySqlClientBill implements BillDao {

    final String INSERT = "INSERT INTO bill( date, client_id) VALUES(?,?)";
    final String UPDATE = "UPDATE bill SET date = ?, client_id = ? WHERE id_bill = ?";
    final String DELETE = "DELETE FROM bill WHERE id_bill = ?";
    final String GETALL = "SELECT  * FROM bill";
    final String GETONE = "SELECT * FROM bill WHERE id_bill = ?";

    final String GETBYBILL = "SELECT * FROM bill_item WHERE id_bill= ?";
    final String INSERTBYBILL = "INSERT INTO bill_item(id_bill, id_item) VALUES(?,?)";
    final String LASTBILLID = "SELECT * FROM Bill ORDER BY id_bill DESC LIMIT 1";

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
        PreparedStatement stat2 = null;

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
        try{
            stat2 = conn.prepareStatement(LASTBILLID);
            ResultSet rs = stat2.executeQuery();
            if(rs.next()){
                int idBill = rs.getInt("id_bill");
                for (int itemType: bill.getItems()){
                    insertBillItem(idBill, itemType);
                }
            }
        } catch (SQLException e){
            throw new DAOException("Error getting bill ID", e);
        } finally {
            if (stat!= null) {
                try{
                    stat.close();
                }catch (SQLException e){
                    throw new DAOException("Error in SQL", e );
                }
            }

        }
    }

    public void insertBillItem(int billId, int itemId) throws DAOException {
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(INSERTBYBILL);
            stat.setInt(1, billId);
            stat.setInt(2, itemId);
            System.out.println("billId" + billId);
            System.out.println("itemId" + itemId);
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error in sql");
            }
        }catch (SQLException e) {
            throw new DAOException("ERROR",e);
        }finally{
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

    private List<Integer> items(int idBill) throws DAOException{
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Integer> items = new ArrayList<>();

        try {
            statement = conn.prepareStatement(GETBYBILL);
            statement.setInt(1, idBill);
            rs = statement.executeQuery();
            while (rs.next()) {
                items.add(rs.getInt("id_item"));
            }

        }catch(SQLException e){
            throw new DAOException("Error in SQL", e);

        }finally{
            if (rs!= null) {
                try{
                    rs.close();
                }catch (SQLException e){
                    throw new DAOException("Error in SQL", e );
                }
            }
        }

        return items;


    }

    /**
     * This method gets the bill´s attributes from the database and use them
     * to create a bill object
     * @param rs A table of data from the bill table in the database
     * @return A bill object
     * @throws SQLException if bill´s attributes could not be taken from the ResultSet
     */
    private Bill convert(ResultSet rs) throws SQLException, DAOException {
        int id = rs.getInt("id_bill");
        System.out.println("ID BILL"+id);
        Date date = rs.getDate("date");
        int client_id = rs.getInt("client_id");
        List<Integer> items = items(id);
        Bill bill = new Bill(date,client_id, items);
        bill.setBillId(id);
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
            List<Integer> items = new ArrayList<>();
            items.add(1);
            Bill bill = new Bill(new Date(0,1,11),12,items);
            //dao.create(bill);

            List<Bill> bills = dao.getAll();
            for (Bill b: bills){
                System.out.println(b.toString());
            }
    }*/
}
