package it.michaelsaccone.sudoku;

import it.michaelsaccone.sudoku.Beans.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> users;

    private final File saveFile;

    public Database(String filePath) {
        this.saveFile = new File(filePath);
        try {
            if (!saveFile.exists()) {
                this.saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Errore nella creazione del file di salvataggio");
        }
    }

    public void registerUser(User user) throws Exception {
        var userAlreadyExists = this.users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));
        if (userAlreadyExists)
            throw new Exception("Utente con username " + user.getUsername() + " esiste gia'");

        this.users.add(user);
    }

    public User loginUser(String username) throws Exception {
        var user = this.users.stream().filter(u -> u.getUsername().equals(username)).findFirst()
                .orElseThrow(() -> new Exception("Login errato: Utente non esistente"));

        return user;
    }

    public void serialize() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(this.saveFile));
            output.writeObject(this.users);
        } catch (IOException e) {
            System.out.println("Errore nella serializzazione degli utenti");
        }
    }

    public void loadSaves() {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(this.saveFile));
            this.users = (ArrayList<User>) input.readObject();
        } catch (IOException e) {
            System.out.println("Errore nel caricamento del salvataggio");
        } catch (ClassNotFoundException e) {
            //  caso in cui (la prima volta) il file e' vuoto e il casting a List<User> non funziona
            this.users = new ArrayList<>();
        } finally {
            if (this.users == null) this.users = new ArrayList<>(); // primo avvio
        }
    }
}
