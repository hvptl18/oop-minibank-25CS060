package util;

import model.annotation.Id;
import model.annotation.Positive;
import model.annotation.MaxLength;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AnnotationValidator {

    private AnnotationValidator() {
    }

    public static String[] validate(Object obj) {

        ArrayList<String> errors = new ArrayList<>();

        if (obj == null) {
            errors.add("Object cannot be null.");
            return errors.toArray(new String[0]);
        }

        Class<?> clazz = obj.getClass();

        while (clazz != null && clazz != Object.class) {

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {

                try {

                    field.setAccessible(true);

                    Object value = field.get(obj);

                    // Validate @Positive
                    if (field.isAnnotationPresent(Positive.class)) {

                        Positive annotation =
                                field.getAnnotation(Positive.class);

                        if (value instanceof Number number
                                && number.longValue() <= 0) {

                            errors.add(
                                    field.getName() + " "
                                            + annotation.message()
                            );
                        }
                    }

                    // Validate @MaxLength
                    if (field.isAnnotationPresent(MaxLength.class)) {

                        MaxLength annotation =
                                field.getAnnotation(MaxLength.class);

                        if (value instanceof String text
                                && text.length() > annotation.value()) {

                            errors.add(
                                    field.getName()
                                            + " length must not exceed "
                                            + annotation.value()
                            );
                        }
                    }

                    // @Id is a marker annotation.
                    if (field.isAnnotationPresent(Id.class)) {

                        if (value == null
                                || value.toString().isBlank()) {

                            errors.add(
                                    field.getName()
                                            + " must not be empty"
                            );
                        }
                    }

                } catch (IllegalAccessException
                         | SecurityException e) {

                    errors.add(
                            "Cannot validate field: "
                                    + field.getName()
                    );
                }
            }

            clazz = clazz.getSuperclass();
        }

        return errors.toArray(new String[0]);
    }
}
