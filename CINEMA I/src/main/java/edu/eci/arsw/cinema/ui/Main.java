/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.ui;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * @author hcadavid
 */
public class Main {

    public static void main(String[] a) throws CinemaPersistenceException {

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CinemaServices cinemaServices = ac.getBean(CinemaServices.class);
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), "2018-12-18 15:30");
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), "2019-12-12 15:30");
        functions.add(funct1);
        functions.add(funct2);
        Cinema c1 = new Cinema("cinemaX", functions);
        Cinema c2 = new Cinema("cinemaY", functions);
        Cinema c3 = new Cinema("cinemaZ", functions);

        cinemaServices.addNewCinema(c1);
        cinemaServices.addNewCinema(c2);
        cinemaServices.addNewCinema(c3);
        try {
//            Set<Cinema> cinemas = cinemaServices.getAllCinemas();
//            for (Cinema cinema : cinemas) {
//                System.out.println(cinema.getName());
//            }
            System.out.println(cinemaServices.getCinemaByName("cinemaX").getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            cinemaServices.buyTicket(0,0,"cinemaX","2018-12-18 15:30","SuperHeroes Movie");
//            List<CinemaFunction> list = cinemaServices.getFunctionsbyCinemaAndDate("cinemaX","2018-12-18 15:30");
//            for (CinemaFunction cinemaFunction:list
//                 ) {
//                System.out.println(cinemaFunction.getDate());
//                System.out.println(cinemaFunction.getMovie().getName());
//                System.out.println(cinemaFunction.getMovie().getGenre());
//                System.out.println(cinemaFunction.getSeats());
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
