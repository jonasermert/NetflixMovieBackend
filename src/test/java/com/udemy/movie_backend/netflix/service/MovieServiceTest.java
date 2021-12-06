package com.udemy.movie_backend.netflix.service;

import com.udemy.movie_backend.netflix.entity.MovieEntity;
import com.udemy.movie_backend.netflix.exception.BadRequestMovieException;
import com.udemy.movie_backend.netflix.exception.NotFoundMovieException;
import com.udemy.movie_backend.netflix.model.Movie;
import com.udemy.movie_backend.netflix.repository.MovieRepository;
import com.udemy.movie_backend.netflix.utils.MovieMapper;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;



@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {

    @InjectMocks
    MovieService service;

    @Mock
    MovieRepository movieRepository;



    @Test
    public void testAllMovies(){
        //2 MoviesEntity in db (MovieRepository) einfuegen
        //die Methode aufrufen: seeAllMovie
        //testen , dass die anzahl von den ausgegebenen Movies 2 ist
        List<MovieEntity> liste = new ArrayList<>();
        MovieEntity movie = createMovieEntity();
        liste.add(movie);
        liste.add(movie);
        Mockito.when(movieRepository.findAll()).thenReturn(liste);
        List<Movie> result = service.seeAllMovie();
        MatcherAssert.assertThat(result.size(), is(2));

    }

    @Test
    public void testGetMovie(){
        //ein Movie mit movieId in db (movieRepository) speichern
        // getMovie Methode mit diesem Id aufrufen
        //  testen dass movie rauskommt
        MovieEntity movieEntity = createMovieEntity();
        movieEntity.setMovieId("movieId");
        Mockito.when(movieRepository.findByMovieId("movieId")).thenReturn(movieEntity);
        Movie movie = service.getMovie("movieId");
        MatcherAssert.assertThat(movie.getActorName(), is(movieEntity.getActorName()));

    }
    @Test(expected = NotFoundMovieException.class)
    public void testGetMovieWithNotFoundMovie(){
        //ein fake Id einsetzen
        //getMovie Methode aufrufen
        service.getMovie("WrongMovieId");

    }

    @Test(expected = BadRequestMovieException.class)
    public void testUpdateMovieWhenWrongMovieIdWithMovie(){
       //die methode updateMovie mit unterschiedlichen MovieIds
        MovieEntity movieEntity = createMovieEntity();
        Movie movie = MovieMapper.INSTANCE.mapEntityToModel(movieEntity);
        movie.setMovieId("wrongMovieId");
        service.updateMovie("movieId", movie);

    }
    @Test(expected = NotFoundMovieException.class)
    public void testUpdateMovieWhennMovieIdNotFound(){
       //movie definieren
        // gleiche Id definieren
        // aber movie nicht in db gespeichert.
        MovieEntity movieEntity = createMovieEntity();
        Movie movie = MovieMapper.INSTANCE.mapEntityToModel(movieEntity);
        movie.setMovieId("movieId");
        service.updateMovie("movieId", movie);

    }


    @Test
    public void testUpdateMovie(){

        MovieEntity movieEntity = createMovieEntity();
        movieEntity.setMovieId("movieId");
       Mockito.when(movieRepository.findByMovieId("movieId")).thenReturn(movieEntity);

       Movie movie = new Movie();
       movie.setMovieId("movieId");
       movie.setDescription("irgendwas");
       service.updateMovie("movieId", movie);
       verify(movieRepository, times(1)).save(movieEntity);
    }

    @Test
    public void testCreateMovie(){
        // Movie zuerst erzeugen
        // Methode aufrufen
        // testen, dass die Methode .save aufgerufen wurde oder nicht
        Movie movie = MovieMapper.INSTANCE.mapEntityToModel(createMovieEntity());
        service.createMovie(movie);
        verify(movieRepository, times(1)).save(any());
    }


    @Test(expected = NotFoundMovieException.class)
    public void testDeleteMovieWithNotFoundMovie(){
        //methode mit falschen MovieId aufrufen
        service.deleteMovie("wrongMovieId");

    }

    @Test
    public void testDeleteMovie(){
        // Movieentity mit movieID in db (movieRepository) speichern
        // diese movieId löschen
        // testen, ob die Methode delete von movieRepository aufgerufen wurde

        MovieEntity movieEntity = createMovieEntity();
        when(movieRepository.findByMovieId("movieID")).thenReturn(movieEntity);
        service.deleteMovie("movieID");
        verify(movieRepository,  times(1)).delete(any());

    }

    private MovieEntity createMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setActorName("Actorname");
        movieEntity.setDate(123);
        movieEntity.setDescription("Description");
        movieEntity.setMovieId("movieID");
        return  movieEntity;
    }
}
