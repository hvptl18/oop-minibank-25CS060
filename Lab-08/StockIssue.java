import java.util.HashMap;
import java.util.Map;

class Warehouse {

    private Map<String, Integer> stock = new HashMap<>();

    public Warehouse() {
        stock.put("Laptop", 5);
        stock.put("Mouse", 10);
        stock.put("Keyboard", 3);
    }

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        // Check invalid quantity
        if (qty <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than 0."
            );
        }

        // Check whether item exists
        if (!stock.containsKey(item)) {
            throw new OutOfStockException(
                    "Item '" + item + "' is not available.",
                    qty
            );
        }

        int available = stock.get(item);

        // Check stock availability
        if (qty > available) {

            int shortfall = qty - available;

            throw new OutOfStockException(
                    "Not enough stock for " + item +
                    ". Available: " + available +
                    ", Requested: " + qty,
                    shortfall
            );
        }

        // Issue the item
        stock.put(item, available - qty);

        System.out.println(
                "Issued " + qty + " " + item +
                "(s) successfully."
        );
    }
}

public class StockIssue {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        // List of stock requests
        String[][] requests = {
                {"Laptop", "2"},
                {"Mouse", "20"},
                {"Keyboard", "1"},
                {"Laptop", "0"},
                {"Monitor", "2"},
                {"Mouse", "3"}
        };

        for (String[] request : requests) {

            String item = request[0];
            int qty = Integer.parseInt(request[1]);

            System.out.println(
                    "Request: " + item + " - Quantity: " + qty
            );

            try {

                warehouse.issue(item, qty);

            } catch (OutOfStockException e) {

                System.out.println(
                        "Stock Error: " + e.getMessage()
                );

                System.out.println(
                        "Shortfall: " + e.getShortfall()
                );

            } catch (InvalidQuantityException e) {

                System.out.println(
                        "Quantity Error: " + e.getMessage()
                );
            }

            System.out.println();
        }

        System.out.println("All requests processed.");
    }
}
