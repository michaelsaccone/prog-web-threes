package it.michaelsaccone.sudoku.listeners;

import it.michaelsaccone.sudoku.Beans.User;
import it.michaelsaccone.sudoku.Database;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.ArrayList;

@WebListener("Ascoltatore per inizializzare il database del Context")
public class ContextInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context INIT");
        //  Istanzio il database una volta nel contesto condiviso
        Database hallOfFameDB = new Database("hall-of-fame.dat");
        ArrayList<User> localUsers = new ArrayList<>();
        hallOfFameDB.loadSaves();
        System.out.println("Salvataggi caricati con successo");
        sce.getServletContext().setAttribute("database", hallOfFameDB);
        sce.getServletContext().setAttribute("users", localUsers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context DESTROY");
        //  riprendo dal contesto il database e salvo i dati prima della chiusura dell'applicazione
        var hofDB = (Database) sce.getServletContext().getAttribute("database");
        hofDB.serialize();
        System.out.println("Salvataggi serializzati con successo");
        ServletContextListener.super.contextDestroyed(sce);
    }
}
