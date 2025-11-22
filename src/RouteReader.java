import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RouteReader{

    public static List<Bus> busReader(String file) throws IOException {

        List<Bus> buses = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        int cntBuses = Integer.parseInt(reader.readLine());

        for (int i = 0; i < cntBuses; i++){
            line = reader.readLine();

            String[] parts = line.split(" ");
            int name = Integer.parseInt(parts[0]);
            double gas = Double.parseDouble(parts[1]);
            int routNum = Integer.parseInt(parts[2]);

            Bus bus = new Bus(name, gas, routNum);

            buses.add(bus);
        }

        reader.close();
        return buses;
    }

    public static List<Route> routeReader(String file) throws IOException {

        List<Route> routes = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        int cntRoutes = Integer.parseInt(reader.readLine());

        for (int i = 0; i < cntRoutes; i++){
            line = reader.readLine();

            String[] parts = line.split(" ");
            int routNum = Integer.parseInt(parts[0]);
            double routLong = Double.parseDouble(parts[1]);
            int busesCnt = Integer.parseInt(parts[2]);

            Route route = new Route(routNum, routLong, busesCnt);

            routes.add(route);
        }

        reader.close();
        return routes;
    }

}
