import static org.junit.Assert.*;

import edu.eci.arsw.cinema.model.*;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class filterbyGenreTest {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldFilterByGenre() {
        try {
            Map<String, Movie> moviesFiltered = cinemaServices.filterByGenre("Horror");
            assertTrue(moviesFiltered.size() > 0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldNotFilterbyUknownGenre() {
        try {
            Map<String, Movie> moviesFiltered = cinemaServices.filterByGenre("Sci-Fi");
            assertEquals(0, moviesFiltered.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldFilterAndReturnEspectedMovie() {
        try {
            Map<String, Movie> moviesFiltered = cinemaServices.filterByGenre("Horror");
            Collection<Movie> movies = moviesFiltered.values();
            for (Movie m : movies) {
                assertEquals("Horror", m.getGenre());
            }
        } catch (Exception e) {
            fail();
        }
    }

}
