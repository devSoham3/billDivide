import java.util.HashMap;
import java.util.Map;

public class Item {
    private String name;
    private double cost;

    public Item(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
