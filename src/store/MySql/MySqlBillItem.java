package store.MySql;

import store.DAOException;
import store.dao.BillItemDao;
import store.model.Bill;
import store.model.BillItem;
import store.model.BillItemId;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlBillItem implements BillItemDao {

    final String INSERT = "INSERT INTO bill_item(id_bill, id_item) VALUES(?,?)";
    final String UPDATE = "UPDATE bill_item SET id_bill = ?, id_item = ?  WHERE id_bill = ? AND id_item = ?";
    final String DELETE = "DELETE FROM bill_item WHERE id_bill = ? AND id_item = ? ";
    final String GETALL = "SELECT * FROM bill_item";
    final String GETONE = "SELECT * FROM bill_item WHERE id_bill = ? AND id_item = ?";
    final String GETBYBILLID = "SELECT id_item FROM bill_item WHERE id_bill = ?";

    private Connection conn;

    public MySqlBillItem(Connection conn){
        this.conn = conn;
    }

    @Override
    public void create(BillItem a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getId().getId_bill());
            stat.setInt(2, a.getId().getId_item());

            System.out.println("id_bill " +  a.getId().getId_bill());
            System.out.println("id_item " +  a.getId().getId_item());

            if(stat.executeUpdate()==0){
                throw new DAOException("Could not add the bill_item register");
            }
            System.out.println("pasa");

        } catch (SQLException e){
            throw new DAOException("Could not add the bill_item register");
        }
        finally{
            if (stat != null){
                try{
                    stat.close();
                }catch (SQLException e){
                    throw new DAOException("Error in SQL");
                }

            }
        }

    }

    @Override
    public void update(BillItem a) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setInt(1,a.getId().getId_bill());
            stat.setInt(2, a.getId().getId_item());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Changes may not be saved");
            }
        }catch(SQLException e) {
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

    @Override
    public void delete(BillItem a) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1,a.getId().getId_bill());
            stat.setInt(2, a.getId().getId_item());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Changes may not be saved");
            }
        }catch(SQLException e) {
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

    private BillItem convert(ResultSet rs) throws SQLException{
        Date date = rs.getDate("date");
        int id_bill = rs.getInt("id_bill");
        int id_item = rs.getInt("id_item");

        BillItemId id = new BillItemId(id_bill,id_item);
        return new BillItem(id);

    }


    @Override
    public List<BillItem> getAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<BillItem> billItem = new ArrayList<>();
        try{
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while(rs.next()) {
                billItem.add(convert(rs));
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
        return billItem;
    }

    @Override
    public BillItem get(BillItemId id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        BillItem bi = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id.getId_bill());
            stat.setInt(2, id.getId_item());
            rs = stat.executeQuery();
            if (rs.next()){
                bi = convert(rs);
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
        return bi;
    }

    public List<Integer> getItems(int billId) throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Integer> items = new ArrayList<>();

        try{
            stat = conn.prepareStatement(GETBYBILLID);
            rs = stat.executeQuery();
            while(rs.next()){
                items.add(rs.getInt("id_item"));

            }
        }catch (SQLException e){
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
}
