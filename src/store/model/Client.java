package store.model;

import java.util.Date;

/**
 * This class represents a client object with it's corresponding
 * clientId, name, lastName, gender, birthDate, civilStatus
 * @author Mariana Ramirez Duque
 * @version 1.0
 *
 */

public class Client {
    private int clientId;
    private String name;
    private String lastName;
    private String gender;
    private Date birthDate;
    private String civilStatus;

    /**
     * Creates a Client object with the specified parameters
     * @param name          The client's name
     * @param lastName      The client's last name
     * @param gender        The client's gender
     * @param birthDate     The client's birth date
     * @param civilStatus   The client's civilStatus
     */
    public Client( String name, String lastName, String gender, Date birthDate, String civilStatus){
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.civilStatus = civilStatus;
    }

    /**
     * Gets the clientId
     * @return an int representing the clientId
     */
    public int getClientId(){
        return clientId;
    }

    /**
     * Gets the client's name
     * @return a String representing the client's name
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the client's last name
     * @return a String representing the client's last name
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Gets the client's gender
     * @return a String representing the client's gender
     */
    public String getGender(){
        return gender;
    }

    /**
     * Gets the client's birth date
     * @return a Date object representing the client's birth date
     */
    public Date getBirthDate(){
        return birthDate;
    }

    /**
     * Gets the client's civilStatus
     * @return a String representing the client's civil status
     */
    public String getCivilStatus(){
        return civilStatus;
    }

    /**
     * sets the clientId
     * @param clientId  An int representing the clientId
     */
    public void setClientId(int clientId){
        this.clientId = clientId;
    }

    /**
     * sets the client's name
     * @param name A String representing the client's name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * sets the client's last name
     * @param lastName A String representing the client's last name
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * Sets the client's gender
     * @param gender A String representing the client's gender
     */
    public void setGender(String gender){
        this.gender = gender;
    }

    /**
     * Sets the client's birth date
     * @param birthDate A Date object representing the client's birth date
     */
    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }

    /**
     * Sets the client's civil status
     * @param civilStatus A string representing the client's civil status
     */
    public void setCivilStatus(String civilStatus){
        this.civilStatus = civilStatus;
    }

    /**
     * This methods return the object's attributes as a String
     * @return A String representing the object attributes
     */
    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", civilStatus='" + civilStatus + '\'' +
                '}';
    }


}