import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Annotation to check blank values
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

// Annotation to check maximum length
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// Signup form
class SignupForm {

    @NotBlank
    @MaxLength(20)
    private String username;

    @NotBlank
    @MaxLength(50)
    private String email;

    @NotBlank
    @MaxLength(15)
    private String password;

    public SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

// Validator using reflection
class Validator {

    public static List<String> validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Class<?> cls = obj.getClass();

        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            try {
                Object value = field.get(obj);

                // Check @NotBlank
                if (field.isAnnotationPresent(NotBlank.class)) {

                    if (value == null ||
                        value.toString().trim().isEmpty()) {

                        errors.add(
                            field.getName() + " must not be blank."
                        );
                    }
                }

                // Check @MaxLength
                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength annotation =
                            field.getAnnotation(MaxLength.class);

                    int max = annotation.value();

                    if (value != null &&
                        value.toString().length() > max) {

                        errors.add(
                            field.getName() +
                            " must not exceed " + max + " characters."
                        );
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add(
                    "Cannot access field: " + field.getName()
                );
            }
        }

        return errors;
    }
}

public class FormValidator {

    public static void main(String[] args) {

        SignupForm form = new SignupForm(
            "",
            "user@example.com",
            "thispasswordiswaytoolong"
        );

        List<String> errors = Validator.validate(form);

        if (errors.isEmpty()) {

            System.out.println("Form is valid.");

        } else {

            System.out.println("Validation errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}
