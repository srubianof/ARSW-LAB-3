/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filter.CinemaFilterI;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author cristian
 */
@Service
public class CinemaServices {
    @Autowired
    @Qualifier("cinemaPersistence")
    CinemaPersitence cps;
    @Autowired
    @Qualifier("filterbySeats")
    CinemaFilterI cf;

    public void addNewCinema(Cinema c) throws CinemaPersistenceException {
        cps.saveCinema(c);
    }

    public Set<Cinema> getAllCinemas() throws CinemaException {
        return cps.getAllCinemas();
    }

    /**
     * @param name cinema's name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     */
    public Cinema getCinemaByName(String name) throws CinemaPersistenceException {
        return cps.getCinemaByName(name);
    }


    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        cps.buyTicket(row, col, cinema, date, movieName);
    }

    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {

        return cps.getFunctionsbyCinemaAndDate(cinema, date);
    }

    public Map<String, Movie> filterByGenre(String genre) throws CinemaException {
        return cf.filter(cps.getAllCinemas(), genre);
    }

    public Map<String, Movie> filterBySeats(String seats) throws CinemaException {
        return cf.filter(cps.getAllCinemas(), seats);
    }


}
