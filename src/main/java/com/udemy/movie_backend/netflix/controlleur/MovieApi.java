package com.udemy.movie_backend.netflix.controlleur;

import com.udemy.movie_backend.netflix.model.Movie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("movie_backend")
@Api("Movie Netflix Backend")
public interface MovieApi {

    @GetMapping(path = "/status")
    public String testStatus();

    //see all Movies
    @GetMapping(path = "/movies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste von Filme erfolgreich abgegeben"),
            @ApiResponse(code = 500, message = "Server fehlgeschlagen"),
    })
    public ResponseEntity<List<Movie>> seeAllMovie();

    //speziel Movie haben
    @GetMapping(path = "/movies/get/{movieId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Film existiert und  erfolgreich abgegeben"),
            @ApiResponse(code = 404, message = "Film existiert nicht in Datenbank"),
            @ApiResponse(code = 500, message = "Server fehlgeschlagen"),
    })
    public ResponseEntity<Movie> getMovie(@NonNull @PathVariable("movieId") String movieId);

    //Movie Infos modifizieren
    @PutMapping(path = "/movies/put/{movieId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Film existiert und  Aktualisierung erfolgreich"),
            @ApiResponse(code = 400, message = "MovieId und MovieId von dem Film stimmen nicht überein"),
            @ApiResponse(code = 404, message = "Film existiert nicht in Datenbank"),
            @ApiResponse(code = 500, message = "Server fehlgeschlagen"),
    })
    public ResponseEntity<Movie> updateMovie(@NonNull @PathVariable("movieId") String movieId, @RequestBody Movie movie);

    // Movie Objekt erzeugen
    @PostMapping(path = "/movie")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Film erfolgreich erzeugt"),
            @ApiResponse(code = 500, message = "Server fehlgeschlagen"),
    })
    public ResponseEntity<Movie> createMovie (@NonNull @RequestBody Movie movie);

    // Movie Objekt löschen
    @DeleteMapping(path = "/movies/delete/{movieId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Film erfolgreich gelöscht"),
            @ApiResponse(code = 404, message = "Film existiert nicht in Datenbank"),
            @ApiResponse(code = 500, message = "Server fehlgeschlagen"),
    })
    public ResponseEntity<Movie> deleteMovie (@NonNull @PathVariable String movieId);

}
