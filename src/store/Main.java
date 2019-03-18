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
import java.util.*;

public class Main {

    static Scanner sc;

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


        String type = null;
        type = sc.next();
        switch (type){
            case "C":
                client();
                break;
            case "T":
                itemType();
                break;
            case "I":
                type();
                break;
            case "B":
                bill();
                break;


        }
    }

    public static void client() throws SQLException, DAOException {
        Scanner sc = new Scanner(System.in);
        MySqlManager msq = new MySqlManager("localhost", "root", "1234", "storedb");

        firstInstruction("cliente");
        String op = sc.next();
        switch (op) {
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
    }

    public static void itemType() throws DAOException, SQLException {
        Scanner sc = new Scanner(System.in);
        MySqlManager msq = new MySqlManager("localhost", "root", "1234", "storedb");
        String op = null;
        firstInstruction("item");
        op = sc.next();
            switch (op){
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

    public static void type() throws SQLException, DAOException {
        Scanner sc = new Scanner(System.in);
        MySqlManager msq = new MySqlManager("localhost", "root", "1234", "storedb");
        String op = null;
        firstInstruction("item");
        op = sc.next();

        switch (op) {
            case "I":
                Item item = getItem();
                msq.getItemDao().create(item);
                break;
            case "A":
                List<Item> items = msq.getItemDao().getAll();
                for (Item a : items) {
                    System.out.println(a.toString());
                }
                break;
            case"U":
                Item itemUpdate = getItem();
                System.out.println("Ingrese el id del artículo");
                int id = sc.nextInt();
                itemUpdate.setId(id);
                msq.getItemDao().update(itemUpdate);
                break;
            case "D":
                System.out.println("Ingrese el id del item  que desea eliminar");
                Item itemD = new Item(1,"d", 0);
                int idI = sc.nextInt();
                itemD.setId(idI);
                msq.getItemDao().delete(itemD);
                break;
            case "G":
                System.out.println("Ingrese el id del item que desea listar");
                int idi = sc.nextInt();
                Item oneItem = msq.getItemDao().get(idi);
                System.out.println(oneItem);
                break;
        }
    }

    public static void bill() throws SQLException,DAOException {
        Scanner sc = new Scanner(System.in);
        MySqlManager msq = new MySqlManager("localhost", "root", "1234", "storedb");
        String op = null;
        firstInstruction("item");
        op = sc.next();

        switch (op) {
            case "I":
                Bill bill = getBill();
                msq.getBillDao().create(bill);
                break;
            case "A":
                List<Bill> bills = msq.getBillDao().getAll();
                for (Bill a : bills) {
                    System.out.println(a.toString());
                }
                break;
            case"U":
                Bill billUpdate = getBill();
                System.out.println("Ingrese el id de la factura");
                int id = sc.nextInt();
                billUpdate.setBillId(id);
                msq.getBillDao().update(billUpdate);
                break;
            case "D":
                System.out.println("Ingrese el id de la factura que desea eliminar");
                List<Integer> items = new ArrayList<>();
                items.add(1);
                Bill billD = new Bill(new Date(0,0,0),1,items);
                int idI = sc.nextInt();
                billD.setBillId(idI);
                msq.getBillDao().delete(billD);
                break;
            case "G":
                System.out.println("Ingrese el id de la factura que desea listar");
                int idi = sc.nextInt();
                Bill oneBill = msq.getBillDao().get(idi);
                System.out.println(oneBill);
                break;
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

    public static Bill getBill() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el año de la fecha");
        int date = sc.nextInt();
        System.out.println("Ingrese el mes de la fecha en numero");
        int month = sc.nextInt();
        System.out.println("Ingrese el dia de la fecha");
        int day = sc.nextInt();
        System.out.println("Ingrese id del cliente");
        int id_client = sc.nextInt();
        System.out.println("Ingrese el numero de items");
        int nItems = sc.nextInt();
        sc.nextLine();
        List<Integer> items = new ArrayList<>();
        int d ;
        for(int i = 1 ; i <= nItems ; i++){
            System.out.println("Ingrese id del item numero " + i );
            d  = sc.nextInt();
            items.add(d);
        }

        return new Bill(new Date(date),id_client,items);
    }

    public static ItemType getItemType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insertar la descripción");
        String description = sc.next();
        return new ItemType(description);
    }

    public static Item getItem(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el tipo de item que es");
        int type = sc.nextInt();
        System.out.println("Ingrese la descripcion del item");
        String description = sc.next();
        System.out.println("Ingrese el valor del item");
        int value = sc.nextInt();
        return new Item(type,description,value);
    }


   /* public static void switchOperation(String op){
        switch (op){
            case
        }
    }*/
}
