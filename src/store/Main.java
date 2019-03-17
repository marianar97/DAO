package store;

import store.MySql.MySqlManager;
import store.model.Client;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, DAOException {

        MySqlManager msq = new MySqlManager("localhost","root","1234", "storedb");

        
        /*
        Client c = new Client("Geoffrey","Hinton", "Male",new Date(45,12,6),"Married");
        //msq.getClientDao().create(c); adds c to the client table

        c.setClientId(10);
        //msq.getClientDao().update(c); updates c

        //msq.getClientDao().delete(c); deletes c

        //Client oneClient = msq.getClientDao().get(9);
        //System.out.println(oneClient); gets c

        List<Client> clients = msq.getClientDao().getAll();
        for (Client a: clients){
         System.out.println(a.toString());
        }*/




    }

}
