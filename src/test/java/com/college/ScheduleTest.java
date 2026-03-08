package com.college;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

class ScheduleTest {

    @Test
    void toStringContainsAllImportantFields() throws Exception {
        Schedule schedule = new Schedule(
            "Аліса",
            "Мельник",
            "Іван",
            "Петренко",
            "Вступ до програмування",
            "Комп`ютерні науки",
            "210",
            "Осінь",
            "2024",
            "09:00:00",
            "10:30:00"
        );

        Field idField = Schedule.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(schedule, "id-123");

        String value = schedule.toString();

        assertTrue(value.contains("id=\"id-123\""));
        assertTrue(value.contains("studentFirstName=\"Аліса\""));
        assertTrue(value.contains("teacherLastName=\"Петренко\""));
        assertTrue(value.contains("courseName=\"Вступ до програмування\""));
        assertTrue(value.contains("roomNumber=\"210\""));
        assertTrue(value.contains("endTime=\"10:30:00\""));
    }

    @Test
    void constructorStoresGivenValuesForSecondCsvRow() {
        Schedule schedule = new Schedule(
            "Богдан",
            "Іванов",
            "Оксана",
            "Коваль",
            "Математичний аналіз I",
            "Математика",
            "212",
            "Осінь",
            "2024",
            "11:00:00",
            "12:30:00"
        );

        String value = schedule.toString();
        assertTrue(value.contains("studentFirstName=\"Богдан\""));
        assertTrue(value.contains("courseName=\"Математичний аналіз I\""));
    }
}
