package javacore.base.hw5;

public class Main {
    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Иванов Иван", "Программист", "i.ivanov@mailbox.com", "8923089340", 30000, 21);
        persArray[1] = new Person("Петров Петр", "Строитель", "p.petrov@mailbox.com", "8923742308", 50000, 22);
        persArray[2] = new Person("Сидоров Сергей", "Сантихник", "s.sidorov@mailbox.com", "8923908230", 60000, 30);
        persArray[3] = new Person("Васильев Вася", "Водитель", "v.vasiliev@mailbox.com", "8923708445", 70000, 50);
        persArray[4] = new Person("Минеев Михаил", "Электрик", "m.mineev@mailbox.com", "8923970892", 80000, 55);

        for (Person p : persArray) {
            if (p.getAge() > 40) {
                System.out.println(p);
            }
        }
    }
}