package com.college;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.charset.StandardCharsets;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Веб-додаток для перегляду та редагування розкладу коледжу.
 *
 * @SpringBootApplication - анотація для позначення головного класу Spring Boot додатку.
 *
 * Методи:
 * - main(String[] args): запускає додаток Spring Boot з вбудованим веб-сервером.
 * - addScheduleFromCsv(): додає розклад з CSV-файлу до бази даних.
 * - viewAllSchedules(): виводить всі розклади з бази даних.
 * - dropAllSchedules(): видаляє всі розклади з бази даних.
 *
 * Поля:
 * - scheduleRepository: репозиторій для роботи з розкладами.
 *
 * Використовує:
 * - CSVReader для зчитування даних з CSV-файлу.
 * - Schedule для представлення документу розкладу.
 * - ScheduleRepository для взаємодії з базою даних.
 */
@SpringBootApplication
public class CollegeApplication {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public static void main(String[] args) {
        SpringApplication.run(CollegeApplication.class, args);
    }

    private void addScheduleFromCsv() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
            getClass().getClassLoader().getResourceAsStream("schedule.csv"),
            StandardCharsets.UTF_8)
        )) {
            List<String[]> records = reader.readAll();
            
            records.remove(0); // Видалити перший рядок з назвами стовпців

            List<Schedule> scheduleList = new ArrayList<>();
            for (String[] record : records) {
                Schedule schedule = new Schedule(
                    record[0], // studentFirstName
                    record[1], // studentLastName
                    record[2], // teacherFirstName
                    record[3], // teacherLastName
                    record[4], // courseName
                    record[5], // departmentName
                    record[6], // roomNumber
                    record[7], // semester
                    record[8], // year
                    record[9], // startTime
                    record[10] // endTime
                );

                scheduleList.add(schedule);
            }
            
            // Очистити існуючий розклад, щоб уникнути дублювання при повторному імпорті
            scheduleRepository.deleteAll();
            scheduleRepository.saveAll(scheduleList);
            System.out.println(scheduleList.size() + " документів з рядками з розкладу завантажено з CSV.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не вдалось завантажити рядок розкладу з CSV.");
        }
    }

    private void viewAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        if (schedules.isEmpty()) {
            System.out.println("Документи з рядками розкладу не знайдено.");
        } else {
            System.out.println("Знайдено " + schedules.size() + " документів розкладу:");
            schedules.forEach(System.out::println);
        }
    }

    private void dropAllSchedules() {
        scheduleRepository.deleteAll();
        System.out.println("Розклад видалено.");
    }
}