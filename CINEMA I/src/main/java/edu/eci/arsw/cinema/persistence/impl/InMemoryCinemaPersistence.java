/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.*;
import edu.eci.arsw.cinema.persistence.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author cristian
 */
@Component
@Qualifier("cinemaPersistence")
public class InMemoryCinemaPersistence implements CinemaPersitence {

    private Map<String, Cinema> cinemas = new HashMap<>();

    public InMemoryCinemaPersistence() {
        //load stub data
//        String functionDate = "2018-12-18 15:30";
//        List<CinemaFunction> functions = new ArrayList<>();
//        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
//        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
//        functions.add(funct1);
//        functions.add(funct2);
//        Cinema c = new Cinema("cinemaX", functions);
//        cinemas.put("cinemaX", c);
    }

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        Cinema cinema1 = this.cinemas.get(cinema);
        boolean functionExists = false;
        List<CinemaFunction> functions = cinema1.getFunctions();
        for (CinemaFunction function : functions) {
            if (function.getMovie().getName().equals(movieName) && function.getDate().equals(date)) {
                function.buyTicket(row, col);
                functionExists = true;
            }
        }
        if (!functionExists) {
            throw new CinemaException("There are no functions that match either the title or the date");
        }
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
//        System.out.println(cinema+" "+date);
        List<CinemaFunction> cinemaFunctionsMatched = new ArrayList<>();
        Cinema cinema1 = this.cinemas.get(cinema);
        List<CinemaFunction> cinemaFunctions = cinema1.getFunctions();
        for (CinemaFunction cinemaFunction : cinemaFunctions) {
            if (cinemaFunction.getDate().equals(date)) {
                cinemaFunctionsMatched.add(cinemaFunction);
            }
        }
        return cinemaFunctions;
    }


    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())) {
            throw new CinemaPersistenceException("The given cinema already exists: " + c.getName());
        } else {
            cinemas.put(c.getName(), c);
        }
    }

    @Override
    public Cinema getCinema(String name) {
        return cinemas.get(name);
    }

    @Override
    public Cinema getCinemaByName(String name){
        return cinemas.get(name);
    }

    @Override
    public Set<Cinema> getAllCinemas() throws CinemaException {
        if (cinemas.isEmpty()) {
            throw new CinemaException("There are no cinemas to show");
        }
        return new HashSet<>(cinemas.values());
    }
}
