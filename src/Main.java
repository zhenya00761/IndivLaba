import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        try {

            List<Bus> buses = RouteReader.busReader("buses.txt");
            List<Route> routes = RouteReader.routeReader("routes.txt");

            HashMap<Route, List<Bus>> mass = new HashMap<>();

            for (Route route : routes) {
                List<Bus> busesOnThisRoute = new ArrayList<>();
                for (Bus bus : buses) {
                    if (bus.getRoutNum() == route.getRoutNum()) {
                        busesOnThisRoute.add(bus);
                    }
                }
                mass.put(route, busesOnThisRoute);
                route.setBuses(busesOnThisRoute); // устанавливаем автобусы для маршрута
                route.efficiencyChange(); // пересчитываем эффективность
            }

            //ЗАДАНИЕ

            List<Route> lessRoutes = new ArrayList<>();
            List<Route> moreRoutes = new ArrayList<>();

            System.out.println("=== ДО ПЕРЕРАСПРЕДЕЛЕНИЯ ===");
            for (Map.Entry<Route, List<Bus>> entry: mass.entrySet()){
                entry.getKey().Info();
                entry.getKey().moreOrLess(entry.getValue());

                if(entry.getKey().getEfficiency() < 0)
                    lessRoutes.add(entry.getKey());
                else if(entry.getKey().getEfficiency() > 0)
                    moreRoutes.add(entry.getKey());

                entry.getKey().allGas(entry.getValue());
                for (Bus bus: entry.getValue()){
                    System.out.println("Автобус номер: " + bus.getName());
                }
                System.out.println("==========================");
            }

            //Дополнительно - ПЕРЕРАСПРЕДЕЛЕНИЕ АВТОБУСОВ

            System.out.println("\n=== ПРОЦЕСС ПЕРЕРАСПРЕДЕЛЕНИЯ ===");

            for (Route lessRoute : lessRoutes) {
                int neededBuses = Math.abs(lessRoute.getEfficiency());
                System.out.println("Маршрут " + lessRoute.getRoutNum() + " нуждается в " + neededBuses + " автобусах");

                // Находим маршруты с избытком автобусов
                List<Route> availableMoreRoutes = new ArrayList<>();
                for (Route moreRoute : moreRoutes) {
                    if (moreRoute.getEfficiency() > 0) {
                        moreRoute.difference(lessRoute.getRoutLong());
                        availableMoreRoutes.add(moreRoute);
                    }
                }

                // Сортируем по близости длины маршрута
                Collections.sort(availableMoreRoutes, new RouteComparator());

                int busesTransferred = 0;
                for (Route donorRoute : availableMoreRoutes) {
                    if (busesTransferred >= neededBuses) break;

                    // Перемещаем автобусы с донорского маршрута на нуждающийся
                    List<Bus> donorBuses = mass.get(donorRoute);
                    List<Bus> busesToTransfer = new ArrayList<>();

                    // Собираем автобусы для перемещения
                    for (Bus bus : donorBuses) {
                        if (busesTransferred < neededBuses && donorRoute.getEfficiency() > 0) {
                            busesToTransfer.add(bus);
                            busesTransferred++;
                            donorRoute.setEfficiency(donorRoute.getEfficiency() - 1);
                        }
                    }

                    // Перемещаем автобусы
                    for (Bus bus : busesToTransfer) {
                        // Обновляем маршрут автобуса
                        bus.setRoutNum(lessRoute.getRoutNum());

                        // Удаляем из донорского маршрута
                        donorBuses.remove(bus);

                        // Добавляем в нуждающийся маршрут
                        List<Bus> lessRouteBuses = mass.get(lessRoute);
                        lessRouteBuses.add(bus);

                        System.out.println("Автобус " + bus.getName() + " перемещен с маршрута " +
                                donorRoute.getRoutNum() + " на маршрут " + lessRoute.getRoutNum());
                    }

                    // Обновляем данные в mass
                    mass.put(donorRoute, donorBuses);
                    donorRoute.setBuses(donorBuses);
                }

                // Обновляем автобусы для получающего маршрута
                List<Bus> updatedLessRouteBuses = mass.get(lessRoute);
                lessRoute.setBuses(updatedLessRouteBuses);
                lessRoute.efficiencyChange();

                System.out.println("Перемещено автобусов на маршрут " + lessRoute.getRoutNum() + ": " + busesTransferred);
            }

            System.out.println("\n===ПЕРЕРАСПРЕДЕЛЕНИЕ===");
            for (Map.Entry<Route, List<Bus>> entry: mass.entrySet()){
                entry.getKey().Info();
                entry.getKey().moreOrLess(entry.getValue());
                entry.getKey().allGas(entry.getValue());
                for (Bus bus: entry.getValue()){
                    System.out.println("Автобус номер: " + bus.getName() + " (маршрут: " + bus.getRoutNum() + ")");
                }
                System.out.println("==========================");
            }

        } catch (IOException e){
            System.out.println("Ошибка чтения файла:" + e.getMessage());
        }
    }
}