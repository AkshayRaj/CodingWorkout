package ark.coding.interviews.socialchorus;

import java.text.SimpleDateFormat;
import java.util.*;

public class MovieNight {
    public static Boolean canViewAll(Collection<Movie> movies) {
        Boolean canViewAll = false;
        ArrayList<Movie> movieList = new ArrayList();
        movieList.addAll(movies);

        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                Date d1 = m1.getStart();
                Date d2 = m2.getStart();
                return d1.compareTo(d2);
            }
        });
        for(int index = 0; index < movieList.size()-1; index++){
            if (movieList.get(index).getEnd().getTime() > movieList.get(index+1).getStart().getTime()){
                return canViewAll;
            }
        }
        canViewAll = true;

        return canViewAll;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 21:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));

        System.out.println(MovieNight.canViewAll(movies));
    }
}

class Movie {
    private Date start, end;
    
    public Movie(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
    
    public Date getStart() {
        return this.start;
    }
    
    public Date getEnd() {
        return this.end;
    } 
}