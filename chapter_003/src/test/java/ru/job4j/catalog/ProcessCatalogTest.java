package ru.job4j.catalog;

import java.util.*;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProcessCatalogTest {
    /**
     * addMissedDeps test.
     */
    @Test
    public void whenArrayMissDepartmentsThenAddIt() {
        ProcessCatalog pc = new ProcessCatalog();
        List<String> departments = new ArrayList<>();
        departments.addAll(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                                        "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        List<String> expected = new ArrayList<>();
        expected.addAll(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                                        "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2", "K1", "K2\\SK1"));
        pc.addMissedDeps(departments);
        assertThat(departments, is(expected));
    }
    /**
     * risingSort test.
     */
    @Test
    public void whenArrayOfDepsThenRisingSortIt() {
        ProcessCatalog pc = new ProcessCatalog();
        List<String> departments = new ArrayList<>();
        departments.addAll(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                                         "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        List<String> expected = new ArrayList<>();
        expected.addAll(Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2",
                                        "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        pc.addMissedDeps(departments);
        pc.risingSort(departments);
        assertThat(departments, is(expected));
    }
    /**
     * downwardSort test.
     */
    @Test
    public void whenArrayOfDepsThenDownwardSortIt() {
        ProcessCatalog pc = new ProcessCatalog();
        List<String> departments = new ArrayList<>();
        departments.addAll(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                                        "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        List<String> expected = new ArrayList<>();
        expected.addAll(Arrays.asList("K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1",
                                        "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"));
        pc.addMissedDeps(departments);
        pc.downwardSort(departments);
        assertThat(departments, is(expected));
    }
}
