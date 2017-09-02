package ru.job4j.professions;

/**
 * Класс описывает инженера.
 */
public class Engineer extends Human {
    /**
     * Любимый софт инженера.
     */
    private String favoriteSoft;
    /**
     * Лицензия на проектную деятельность.
     */
    private String license;

    /**
     * Основной конструктор.
     * @param name имя.
     * @param favoriteSoft любимый софт.
     * @param license лицензия.
     */
    public Engineer(String name, String favoriteSoft, String license) {
        super(name);
        this.favoriteSoft = favoriteSoft;
        this.license = license;
    }

    //public void assemble (Part part1, Part part2, Part part3) {
    //    System.out.println("Engineer " + this.getName() + " assembles " + part1 + part2 + part3);
    //}

    /**
     * Рисование чертежа для задания.
     * @param task задание.
     */
    public void draw(Task task) {
        System.out.println("Engineer " + this.getName() + " draws blueprint for " + task.getType());
    }
}
