package javacore.advanced.hw3.task2;

public class Main {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook() {{
            add("Smith", "+1885568020");
            add("Smith", "+1245406214");
            add("Jones", "+1708516864");
            add("Taylor", "+1522550243");
            add("Brown", "+1427383085");
            add("Brown", "+1709545336");
            add("Brown", "+1534740600");
            add("Williams", "+1498470051");
            add("Williams", "+1426723361");
        }};

        System.out.println(phonebook.get("Brown"));
    }
}
