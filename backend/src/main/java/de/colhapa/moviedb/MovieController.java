package de.colhapa.moviedb;

import de.colhapa.moviedb.domain.Movie;
import de.colhapa.moviedb.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "movies")
public class MovieController {

    private static Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping(
            path = "/{title}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Movie getMovieByTitle(@PathVariable("title") String title) {
        return movieService.getMovie(title);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void saveMovie(@RequestBody Movie movie) {
        movieService.saveMovie(movie);
    }

    @DeleteMapping(
            path = "/{title}")
    public void deleteMovie(@PathVariable("title") String title) {
        movieService.deleteMovie(title);
    }
}
