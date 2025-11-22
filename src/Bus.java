public class Bus {

    private int name;
    private double gas;
    private int routNum;

    public Bus(int name, double gas, int routNum){
        setName(name);
        setGas(gas);
        setRoutNum(routNum);
    }

    public Bus(Bus other){
        setName(other.getName());
        setGas(other.getGas());
        setRoutNum(other.getRoutNum());
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    public int getRoutNum() {
        return routNum;
    }

    public void setRoutNum(int routNum) {
        this.routNum = routNum;
    }

    public void Info(){
        System.out.println("Автобус номер: " + this.name);
    }
}
