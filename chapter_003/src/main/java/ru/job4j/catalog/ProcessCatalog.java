package ru.job4j.catalog;

import java.util.*;

public class ProcessCatalog {

    public void addMissedDeps(List<String> departments) {
        List<String> depsList = new ArrayList<>();
        depsList.addAll(departments);
        for (String element : depsList) {
            while (element.contains("\\")) {
                element = element.substring(0, element.lastIndexOf("\\"));
                if (!departments.contains(element)) {
                    departments.add(element);
                }
            }
        }
    }
    /**
     * Сортировка по возрастанию.
     * @param departments лист департаментов.
     */
    public void risingSort(List<String> departments) {
        departments.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                }
        );
    }
    /**
     * Сортировка по убыванию.
     * @param departments лист департаментов.
     */
    public void downwardSort(List<String> departments) {
        departments.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if (o1.startsWith(o2)) {
                            return 1;
                        } else if (o2.startsWith(o1)) {
                            return -1;
                        } else {
                            return o2.compareTo(o1);
                        }
                    }
                }
        );
    }
}
