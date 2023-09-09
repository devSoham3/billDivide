import java.text.DecimalFormat;
import java.util.*;

public class BillSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bill Splitter!");
        System.out.print("Enter the number of participants: ");
        int numParticipants = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Participant> participants = new ArrayList<>();
        for (int i = 1; i <= numParticipants; i++) {
            System.out.print("Enter the name of participant " + i + ": ");
            String participantName = scanner.nextLine();
            participants.add(new Participant(participantName));
        }

        System.out.print("Enter the number of items in the bill: ");
        int numItems = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= numItems; i++) {
            System.out.print("Enter the name of item " + i + ": ");
            String itemName = scanner.nextLine();
            System.out.print("Enter the cost of item " + i + ": ");
            double itemCost = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            Item item = new Item(itemName, itemCost);
            items.add(item);

            double remainingShare = 1.0; // Initialize remaining share to 1.0

            for (Participant participant : participants) {
                if (remainingShare > 0) {
                    System.out.print("Is " + participant.getName() + " included in " + itemName + "? (yes/no, remaining share: " + remainingShare + "): ");
                    String inclusion = scanner.nextLine().toLowerCase();
                    if (inclusion.equals("yes")) {
                        System.out.print("Enter " + participant.getName() + "'s share for " + itemName + ": ");
                        double share = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        if (share > remainingShare) {
                            share = remainingShare; // Cap the share to the remaining share
                        }
                        participant.setItemShare(itemName, share);
                        remainingShare -= share;
                        DecimalFormat df1 = new DecimalFormat("#.##");
                        remainingShare = Double.parseDouble(df1.format(remainingShare));
                    }
                } else {
                    System.out.println("The remaining share for " + itemName + " is already 0. Skipping further shares.");
                    break; // Skip further shares if remaining share is 0
                }
            }
        }

        Map<String, Double> personShares = calculateBillShare(participants, items);

        System.out.println("\nBill Summary:");
        DecimalFormat df = new DecimalFormat("#.##");
        for (Map.Entry<String, Double> entry : personShares.entrySet()) {
            String formattedShare = df.format(entry.getValue());
            System.out.println(entry.getKey() + " should pay: $" + formattedShare);
        }

        scanner.close();
    }

    public static Map<String, Double> calculateBillShare(List<Participant> participants, List<Item> items) {
        Map<String, Double> personShares = new HashMap<>();

        for (Participant participant : participants) {
            String participantName = participant.getName();
            double totalShare = 0.0;

            for (Item item : items) {
                double share = participant.getItemShare(item.getName()) * item.getCost();
                totalShare += share;
            }

            personShares.put(participantName, totalShare);
        }

        return personShares;
    }
}
