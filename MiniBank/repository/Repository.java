package repository;

import model.annotation.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class Repository<T> {

    private final HashMap<String, T> data = new HashMap<>();

    // Save one object using its @Id field
    public T save(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        try {
            Class<?> clazz = entity.getClass();

            while (clazz != null) {

                for (Field field : clazz.getDeclaredFields()) {

                    if (field.isAnnotationPresent(Id.class)) {

                        field.setAccessible(true);

                        Object value = field.get(entity);

                        if (value == null) {
                            throw new IllegalArgumentException(
                                    "ID cannot be null"
                            );
                        }

                        String id = value.toString();

                        if (id.trim().isEmpty()) {
                            throw new IllegalArgumentException(
                                    "ID cannot be empty"
                            );
                        }

                        data.put(id, entity);
                        return entity;
                    }
                }

                // Also search parent classes for an @Id field
                clazz = clazz.getSuperclass();
            }

            throw new IllegalArgumentException(
                    "No field marked with @Id found"
            );

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to read entity ID", e);
        }
    }

    // Find an object by ID
    public T findById(String id) {
        return data.get(id);
    }

    // Return all stored objects
    public ArrayList<T> findAll() {
        return new ArrayList<>(data.values());
    }

    // Delete an object by ID
    public boolean delete(String id) {
        return data.remove(id) != null;
    }

    // Save multiple objects
    @SafeVarargs
    public final void saveAll(T... items) {

        for (T item : items) {
            save(item);
        }
    }

    // Generic method with a bounded type parameter
    public static <N extends Number> double average(N[] values) {

        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                    "Array cannot be null or empty"
            );
        }

        double sum = 0;

        for (N value : values) {
            if (value == null) {
                throw new IllegalArgumentException(
                        "Array cannot contain null values"
                );
            }

            sum += value.doubleValue();
        }

        return sum / values.length;
    }
}
