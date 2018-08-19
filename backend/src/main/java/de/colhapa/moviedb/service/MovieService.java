package de.colhapa.moviedb.service;

import de.colhapa.moviedb.domain.Movie;
import de.colhapa.moviedb.domain.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private static Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovie(String title) {
        return movieRepository.findOneByTitle(title).orElse(new Movie());
    }

    public void saveMovie(Movie movie) {
        logger.info("Saving Movie " + movie.getTitle());
        Movie movieToSave = movieRepository.findOneByTitle(movie.getTitle()).orElse(new Movie());
        movieToSave.setTitle(movie.getTitle());
        movieRepository.save(movieToSave);
    }

    public void deleteMovie(String title) {
        Optional<Movie> foundMovie = movieRepository.findOneByTitle(title);
        foundMovie.ifPresent(movie -> movieRepository.delete(movie));
    }
}
