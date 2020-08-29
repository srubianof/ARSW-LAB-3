package edu.eci.arsw.cinema.filter;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.Movie;

import java.util.*;

public interface CinemaFilterI {
    public Map<String,Movie> filter(Set<Cinema> cinemas, String input);
}
