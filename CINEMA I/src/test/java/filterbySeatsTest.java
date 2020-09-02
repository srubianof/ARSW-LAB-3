import static org.junit.Assert.*;

import edu.eci.arsw.cinema.model.*;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class filterbySeatsTest {
    CinemaServices cinemaServices;

    @Before
    public void prepare() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.cinemaServices = ac.getBean(CinemaServices.class);
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), "2018-12-18 15:30");
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), "2019-12-12 15:30");
        functions.add(funct1);
        functions.add(funct2);
        Cinema c1 = new Cinema("cinemaX", functions);
        try {
            cinemaServices.addNewCinema(c1);
            cinemaServices.buyTicket(0, 0, "cinemaX", "2018-12-18 15:30", "SuperHeroes Movie");
            cinemaServices.buyTicket(0, 1, "cinemaX", "2018-12-18 15:30", "SuperHeroes Movie");
            cinemaServices.buyTicket(0, 2, "cinemaX", "2018-12-18 15:30", "SuperHeroes Movie");
            cinemaServices.buyTicket(0, 3, "cinemaX", "2018-12-18 15:30", "SuperHeroes Movie");
            cinemaServices.buyTicket(0, 4, "cinemaX", "2018-12-18 15:30", "SuperHeroes Movie");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldFilterAvailableSeats() {
        try {
            Map<String, Movie> moviesFiltered = cinemaServices.filterBySeats("0");
            assertTrue(moviesFiltered.size() > 0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldFilterFirstAvailableSeats() {
        try {
            Map<String, Movie> moviesFiltered = cinemaServices.filterBySeats("1");
            assertTrue(moviesFiltered.size() > 0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldFilterSecondAvailableSeats() {
        try {
            Map<String, Movie> moviesFiltered = cinemaServices.filterBySeats("2");
            assertTrue(moviesFiltered.size() > 0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldNotFilterIfString() {
        try {
            Map<String, Movie> moviesFiltered = cinemaServices.filterBySeats("HOLA");
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

}
