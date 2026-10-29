class Box<T> {
private T value;


public void set(T value) {
    this.value = value;
}

public T get() {
    return value;
}

}

class Pair<A, B> {
private A first;
private B second;


public Pair(A first, B second) {
    this.first = first;
    this.second = second;
}

public A getFirst() {
    return first;
}

public B getSecond() {
    return second;
}


}

public class GenericContainersStats {


// Generic method to return the first element of any array
public static <T> T firstElement(T[] array) {
    if (array == null || array.length == 0) {
        throw new IllegalArgumentException("Array cannot be null or empty");
    }
    return array[0];
}

// Generic method to calculate the average of Number types
public static <N extends Number> double average(N[] values) {
    if (values == null || values.length == 0) {
        throw new IllegalArgumentException("Array cannot be null or empty");
    }

    double sum = 0.0;

    for (N value : values) {
        sum += value.doubleValue();
    }

    return sum / values.length;
}

public static void main(String[] args) {

    // 1. Generic Box with different types
    Box<Integer> intBox = new Box<>();
    intBox.set(100);
    System.out.println("Integer Box: " + intBox.get());

    Box<String> strBox = new Box<>();
    strBox.set("Hello Java");
    System.out.println("String Box: " + strBox.get());

    // 2. Generic Pair with different types
    Pair<String, Integer> pair = new Pair<>("Heer", 20);
    System.out.println("Pair First: " + pair.getFirst());
    System.out.println("Pair Second: " + pair.getSecond());

    // 3. Generic method to get the first array element
    String[] names = {"Java", "Python", "C++"};
    System.out.println("First Name: " + firstElement(names));

    Integer[] numbers = {10, 20, 30};
    System.out.println("First Number: " + firstElement(numbers));

    // 4. Bounded generic average method
    Integer[] integers = {10, 20, 30, 40};
    Double[] doubles = {1.5, 2.5, 3.5};
    Long[] longs = {100L, 200L, 300L};

    System.out.println("Integer Average: " + average(integers));
    System.out.println("Double Average: " + average(doubles));
    System.out.println("Long Average: " + average(longs));
}


}
