package edu.eci.arsw.cinema.filter.impl;

import edu.eci.arsw.cinema.filter.CinemaFilterI;
import edu.eci.arsw.cinema.model.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Qualifier("filterbySeats")
public class filterbySeats implements CinemaFilterI {
    @Override
    public Map<String, Movie> filter(Set<Cinema> cinemas, String input) {
        int Seats = Integer.parseInt(input);
        Map<String, Movie> movies = new HashMap<>();
        for (Cinema cinema : cinemas) {
            List<CinemaFunction> functions = cinema.getFunctions();
            for (CinemaFunction cinemaFunction : functions) {
                int numberSeats = 0;
                List<List<Boolean>> seatM = cinemaFunction.getSeats();
                for (List<Boolean> p : seatM) {
                    for (Boolean b : p) {
                        if (!b) {
                            numberSeats++;
                        }
                    }
                }
                if (numberSeats > Seats) {
                    movies.put(cinema.getName(), cinemaFunction.getMovie());
                }
            }
        }
        return movies;
    }
}
