import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {

    public int compare(Route r1, Route r2){

        if (r1.getRoutDifference() < r2.getRoutDifference()){
            return -1;
        }
        if (r1.getRoutDifference() > r2.getRoutDifference()) {
            return 1;
        }
        return 0;
    }

}
