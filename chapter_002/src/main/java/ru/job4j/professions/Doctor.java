package ru.job4j.professions;

/**
 * Класс описывает врача.
 */
public class Doctor extends Human {
    /**
     * Ученая степень.
     */
    private String grade;

    /**
     * Определяет, является ли врач грубым.
     */
    private boolean rudeness;

    /**
     * Основной конструктор.
     * @param name имя.
     * @param grade степень.
     * @param rudeness грубость.
     */
    public Doctor(String name, String grade, boolean rudeness) {
        super(name);
        this.grade = grade;
        this.rudeness = rudeness;
    }

    /**
     * Осмотр пациента.
     * @param human человек-пациент.
     */
    public void inspect(Human human) {
        System.out.println("Doctor " + this.getName() + "inspects pacient" + human.getName());
    }

    //public void analyze(Specimen specimen) {
    //    System.out.println("Doctor " + this.getName() + "analyzes " + specimen);
    //}

    /**
     * Хирургическая операция.
     * @param human человек-пациент.
     */
    public void makeSurgery(Human human) {
        System.out.println("Doctor " + this.getName() + "makes surgery to " + human.getName());
    }
}
