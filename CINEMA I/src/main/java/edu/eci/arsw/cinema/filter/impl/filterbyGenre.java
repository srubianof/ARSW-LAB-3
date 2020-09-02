package edu.eci.arsw.cinema.filter.impl;

import edu.eci.arsw.cinema.filter.CinemaFilterI;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@Qualifier("filterbyGenre")
public class filterbyGenre implements CinemaFilterI {
    @Override
    public Map<String,Movie> filter(Set<Cinema> cinemas, String input) {
        Map<String,Movie> movies = new HashMap<>();
        for (Cinema cinema : cinemas) {
            List<CinemaFunction> functions = cinema.getFunctions();
            for (CinemaFunction cinemaFunction : functions) {
                if (cinemaFunction.getMovie().getGenre().equals(input)) {
                    movies.put(cinema.getName(),cinemaFunction.getMovie());
                }
            }
        }
        return movies;
    }
}
