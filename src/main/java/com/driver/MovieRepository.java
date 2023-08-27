package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieHashMap;
    HashMap<String, Director> directorHashMap;
    HashMap<String, List<String>> directorMovieHashMap;

    public MovieRepository(HashMap<String, Movie> movieHashMap, HashMap<String, Director> directorHashMap, HashMap<String, List<String>> directorMovieHashMap) {
        this.movieHashMap = movieHashMap;
        this.directorHashMap = directorHashMap;
        this.directorMovieHashMap = directorMovieHashMap;
    }

    public void saveMovie(Movie movie) {
        movieHashMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director) {
        directorHashMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director) {
        if(movieHashMap.containsKey(movie) && directorHashMap.containsKey(director)){
            movieHashMap.put(movie, movieHashMap.get(movie));
            directorHashMap.put(director, directorHashMap.get(director));
            List<String> currentMovies = new ArrayList<>();
            if (directorMovieHashMap.containsKey(director))
                currentMovies = directorMovieHashMap.get(director);
            currentMovies.add(movie);
            directorMovieHashMap.put(director, currentMovies);
        }
    }

    public Movie findMovie(String name) {
        return movieHashMap.get(name);
    }

    public Director findDirector(String name) {
        return directorHashMap.get(name);
    }

    public List<String> findMovieFromDirector(String director) {
        List<String> movieList = new ArrayList<>();
        if (directorMovieHashMap.containsKey(director))
            directorMovieHashMap.get(director);
        return movieList;
    }

    public List<String> findAllMovie() {
        return new ArrayList<>(movieHashMap.keySet());
    }

    public void deleteDirector(String director) {
        List<String> movies = new ArrayList<>();
        if (directorMovieHashMap.containsKey(director)){
            movies = directorMovieHashMap.get(director);
            for (String movie : movies) {
                if (movieHashMap.containsKey(movies)){
                    movieHashMap.remove(movies);
                }
            }
            directorMovieHashMap.remove(director);
        }
        if (directorHashMap.containsKey(director)){
            directorHashMap.remove(director);
        }
    }

    public void deleteAllDirector() {
        HashSet<String> moviesSet = new HashSet<>();

        for (String director : directorMovieHashMap.keySet()){
            for (String movie : directorMovieHashMap.get(director)){
                moviesSet.add(movie);
            }
        }

        for (String movie : moviesSet){
            if (movieHashMap.containsKey(movie)){
                movieHashMap.remove(movie);
            }
        }
    }
}
