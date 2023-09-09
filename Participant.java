import java.util.HashMap;
import java.util.Map;

public class Participant {
    private String name;
    private Map<String, Double> itemShares;

    public Participant(String name) {
        this.name = name;
        this.itemShares = new HashMap<>();
    }

    public void setItemShare(String item, double share) {
        itemShares.put(item, share);
    }

    public double getItemShare(String item) {
        return itemShares.getOrDefault(item, 0.0);
    }

    public String getName() {
        return name;
    }
}
