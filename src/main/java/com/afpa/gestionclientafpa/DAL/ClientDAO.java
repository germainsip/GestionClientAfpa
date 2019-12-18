/*
 *  ___ _                    _                   
 * |_ _| |_  __ __ _____ _ _| |__ ___  ___ _ _   
 *  | ||  _| \ V  V / _ \ '_| / /(_-< / _ \ ' \  
 * |___|\__|  \_/\_/\___/_| |_\_\/__/ \___/_||_| 
 *  _ __ _  _   _ __  __ _ __| |_ (_)_ _  ___    
 * | '  \ || | | '  \/ _` / _| ' \| | ' \/ -_)   
 * |_|_|_\_, | |_|_|_\__,_\__|_||_|_|_||_\___|   
 *       |__/                                    
 */
package com.afpa.gestionclientafpa.DAL;

import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javafx.scene.control.Alert;

/**
 * créé le 19 nov. 2018 , 09:30:32
 *
 * @author germain
 */
public class ClientDAO {

    private String dest = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC";
    private String user = "root";
    private String pass = "";

    /**
     *
     */
    public static int idMax = 0;

    /**
     *
     */
    public ClientDAO() {

    }

    /**
     * Insert new client in Hotel database
     * @param cli 
     * object with clid_id,cli_nom, cli_prenom and cli_ville as attribut
     * @throws SQLException
     */
    public void Insert(Client cli) throws SQLException {
        //
        Connection con = DriverManager.getConnection(dest, user, pass);
        PreparedStatement stm1 = con.prepareStatement("insert into client (cli_nom,cli_prenom,cli_ville) values (?,?,?)");
        stm1.setString(1, cli.getNom());
        stm1.setString(2, cli.getPrenom());
        stm1.setString(3, cli.getVille());
        stm1.execute();

        stm1.close();
        con.close();

    }

    /**
     * Update client values in Hotel database 
     * @param cli
     * object with clid_id,cli_nom, cli_prenom and cli_ville as attribut
     * @throws SQLException
     */
    public void Update(Client cli) throws SQLException {
        Connection con = DriverManager.getConnection(dest, user, pass);
        PreparedStatement stm = con.prepareStatement("UPDATE client SET cli_nom=? ,cli_prenom=? ,cli_ville=? WHERE cli_id=?");
        stm.setString(1, cli.getNom());
        stm.setString(2, cli.getPrenom());
        stm.setString(3, cli.getVille());
        stm.setInt(4, cli.getId());
        stm.execute();
        stm.close();
        con.close();

    }

    /**
     * Delete client selected in Hotel database
     * @param cli
     * @throws SQLException
     */
    public void Delete(Client cli) throws SQLException {

        Connection con = DriverManager.getConnection(dest, user, pass);
        PreparedStatement stm = con.prepareStatement("DELETE FROM client WHERE cli_id=?");
        stm.setInt(1, cli.getId());
        stm.execute();
        stm.close();
        con.close();

    }
//    public Client Find (int id){
//        
//        return ;
//    }

    /**
     * create a list with the content of Hotel.client database
     * @return
     */
    public List<Client> List() throws SQLException {
        List<Client> listeClient = new ArrayList();
        
            Connection con = DriverManager.getConnection(dest, user, pass);
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * from client");

            while (result.next()) {
                int id = result.getInt("cli_id");
                if (id > idMax) {
                    idMax = id;
                }
                String nom = result.getString("cli_nom");
                String prenom = result.getString("cli_prenom");
                String ville = result.getString("cli_ville");
                Client c = new Client(id, nom, prenom, ville);
                listeClient.add(c);

            }
            stm.close();
            con.close();

        
        System.out.println(listeClient.size());
        return listeClient;
    }

}
