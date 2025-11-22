import java.util.List;

public class Route{

    private int routNum;
    private double routLong;
    private int busesCnt;
    private int efficiency;
    private List<Bus> buses;
    private double routDifference;

    public Route(int routNum, double routLong, int busesCnt, List<Bus> buses) {
        setRoutNum(routNum);
        setRoutLong(routLong);
        setBusesCnt(busesCnt);
        setBuses(buses);
        efficiency = 0;
    }

    public Route(int routNum, double routLong, int busesCnt) {
        setRoutNum(routNum);
        setRoutLong(routLong);
        setBusesCnt(busesCnt);
        efficiency = 0;
    }

    public Route(Route other){
        setRoutNum(other.getRoutNum());
        setRoutLong(other.getRoutLong());
        setBusesCnt(other.getBusesCnt());
        efficiency = 0;
    }

    public Route(Route other, List<Bus> buses) {
        setRoutNum(other.getRoutNum());
        setRoutLong(other.getRoutLong());
        setBusesCnt(other.getBusesCnt());
        setBuses(buses);
        efficiency = 0;
    }

    public int getRoutNum() {
        return routNum;
    }

    public void setRoutNum(int routNum) {
        this.routNum = routNum;
    }

    public double getRoutLong() {
        return routLong;
    }

    public void setRoutLong(double routLong) {
        this.routLong = routLong;
    }

    public int getBusesCnt() {
        return busesCnt;
    }

    public void setBusesCnt(int busesCnt) {
        this.busesCnt = busesCnt;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public void Info(){
        System.out.println("Маршрут номер: " + this.routNum);
    }

    public double getRoutDifference() {
        return routDifference;
    }

    public void setRoutDifference(double routDifference) {
        this.routDifference = routDifference;
    }

    public void moreOrLess(List<Bus> buses){
        if (this.busesCnt > buses.size())
            System.out.println("Автобусов не хватает");
        else if (this.busesCnt < buses.size()) {
            System.out.println("Автобусов больше, чем нужно");
        }
        else {
            System.out.println("Автобусов достаточно");
        }
        efficiency = busesCnt - buses.size();
    }

    public void allGas(List<Bus> buses){
        double sum = 0;
        for (Bus bus: buses){
            sum += bus.getGas();
        }
        System.out.printf("Все автобусы данного маршрута расходуют %.2f литров бензина\n", sum);
    }

    public void difference(double x){
        setRoutDifference(Math.abs(x - routLong));
    }

    public void efficiencyChange(){
        efficiency = busesCnt - buses.size();
    }
}
