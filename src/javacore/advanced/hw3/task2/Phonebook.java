package javacore.advanced.hw3.task2;

import java.util.*;

public class Phonebook {

    private final Map<String, Set<String>> data;

    public Phonebook() {
        data = new HashMap<>();
    }

    /**
     * Add a phone number.
     *
     * @param name  Person last name
     * @param phone Phone number
     */
    public void add(String name, String phone) {
        data.computeIfAbsent(normalize(name), key -> new HashSet<>())
                .add(phone);
    }

    /**
     * Get phone numbers by a person last name.
     *
     * @param name Person last name
     * @return Collection of phone numbers that belong to the person
     */
    public Collection<String> get(String name) {
        Set<String> phones = data.getOrDefault(normalize(name), Collections.emptySet());
        return Collections.unmodifiableSet(phones);
    }

    private String normalize(String name) {
        return name.toLowerCase().trim();
    }
}
