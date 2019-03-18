package store;

import store.MySql.MySqlManager;
import store.model.Bill;
import store.model.Client;
import store.model.Item;
import store.model.ItemType;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, DAOException {

    commandLine();

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

    public static void commandLine() throws SQLException, DAOException {
        MySqlManager msq = new MySqlManager("localhost","root","1234", "storedb");

        System.out.println("Presiona: \nC para ver las opciones de clientes \nI para ver las opciones de Items, " +
                "\nB para ver las opciones de Bill, \nT para ver las opciones de itemType");

        Scanner sc = new Scanner(System.in);

        String tipo = null;
        String operacion = null;
        tipo = sc.next();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");


        switch (tipo){
            case "C":
                firstInstruction("cliente");
                operacion = sc.next();
                switch (operacion) {
                    case "I":
                        Client clientInsert = getClient();
                        msq.getClientDao().create(clientInsert);

                        break;
                    case "A":
                        List<Client> clients = msq.getClientDao().getAll();
                        for (Client a : clients) {
                            System.out.println(a.toString());
                        }
                        break;
                    case "U":
                        Client clientUpdate = getClient();
                        System.out.println("Ingrese el id del cliente");
                        int id = sc.nextInt();
                        clientUpdate.setClientId(id);
                        msq.getClientDao().update(clientUpdate);
                        break;
                    case "D":
                        System.out.println("Ingrese el id del cliente que desea eliminar");
                        Client cdefault = new Client("d", "d", "d", new Date(0, 0, 0), "d");
                        int idC = sc.nextInt();
                        cdefault.setClientId(idC);
                        msq.getClientDao().delete(cdefault);
                        break;
                    case "G":
                        System.out.println("Ingrese el id del cliente que desea listar");
                        int idG = sc.nextInt();
                        Client oneClient = msq.getClientDao().get(idG);
                        System.out.println(oneClient);
                        break;
                }
            case "T":
                firstInstruction("item");
                operacion = sc.next();
                switch (operacion){
                    case "I":
                        ItemType itInsert = getItemType();
                        msq.getItemTypeDao().create(itInsert);
                        break;
                    case "A":
                        List<ItemType> itemTypes = msq.getItemTypeDao().getAll();
                        for (ItemType a : itemTypes) {
                            System.out.println(a.toString());
                        }
                        break;
                    case "U":
                        ItemType itemTypeUpdate = getItemType();
                        System.out.println("Ingrese el id del tipo de item");
                        int id = sc.nextInt();
                        itemTypeUpdate.setId(id);
                        msq.getItemTypeDao().update(itemTypeUpdate);
                        break;
                    case "D":
                        System.out.println("Ingrese el id del tipo de item que desea eliminar");
                        ItemType itemtypeD = new ItemType("description");
                        int idIT = sc.nextInt();
                        itemtypeD.setId(idIT);
                        msq.getItemTypeDao().delete(itemtypeD);
                        break;
                    case "G":
                        System.out.println("Ingrese el id del tipo de item que desea listar");
                        int idi = sc.nextInt();
                        ItemType oneItemType = msq.getItemTypeDao().get(idi);
                        System.out.println(oneItemType);
                        break;
                }
        }
    }

    public static void firstInstruction(String tipo){
        System.out.println("Presione: \nI para añadir un "+tipo +"\nD para eliminar un " +tipo + " \nU para actualizar" +
                "la informacion de un " +tipo + "  \nA para listar todos los " +tipo + "s  \nG para listar un " +tipo );
    }

    public static Client getClient(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre");
        String name = sc.next();
        System.out.println("Ingrese el apellido");
        String lastName = sc.next();
        System.out.println("Ingrese el género");
        String gender = sc.next();
        System.out.println("Ingrese el año de nacimiento");
        int year = sc.nextInt();
        year = year - 1900;
        System.out.println("Ingrese el mes de nacimiento");
        int month = sc.nextInt();
        System.out.println("Ingrese el día de nacimiento");
        int day = sc.nextInt();
        System.out.println("Ingrese el estado civil");
        String civilStatus = sc.next();
        Date date = new Date(year,month,day);
        return new Client(name,lastName,gender,date,civilStatus);

    }

    public static ItemType getItemType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insertar la descripción");
        String description = sc.next();
        return new ItemType(description);
    }

   /* public static void switchOperation(String op){
        switch (op){
            case
        }
    }*/
}
