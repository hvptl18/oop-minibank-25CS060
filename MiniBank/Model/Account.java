Part B — Project Work (Hour 2): MiniBank
Now that you are comfortable with classes and interfaces, you add metadata to MiniBank.
You define custom annotations (@Id, @Positive, @MaxLength) and read them at run time
with reflection to validate any object automatically — the same idea real frameworks use.
The @Id marker will later identify each record in the generic repository you build in
Practical 13.
1. Add the @Override annotation above every overridden method written so far (toString,
equals, hashCode and the subclass methods). Add @FunctionalInterface above the
WithdrawRule interface.

CO1,
CO2
PO2,
PO3,
PSO2

Practical
Number CO/PO

2. In a package named model.annotation, define three custom annotations: @Id (a marker,
with @Target(FIELD) and @Retention(RUNTIME)); @Positive with a member String
message() default “must be > 0”; and @MaxLength with a member int value().
3. Put @Id on the accountNumber field and @Positive on the balance field of Account.
4. Create a class named AnnotationValidator with a public static method String[]
validate(Object obj) that uses reflection — getClass().getDeclaredFields(),
field.isAnnotationPresent(...), field.setAccessible(true) and field.get(obj) — to check the
@Positive and @MaxLength rules and return the error messages as a String[].
5. In main, create an Account with a negative balance and run
AnnotationValidator.validate() to print the error.
