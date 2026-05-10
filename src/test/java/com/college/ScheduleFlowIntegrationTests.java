package com.college;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
class ScheduleFlowIntegrationTests {

    @Container
    private static final MongoDBContainer MONGO_DB_CONTAINER =
        new MongoDBContainer(DockerImageName.parse("mongo:7.0"));

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void configureMongo(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
    }

    @AfterEach
    void cleanDatabase() {
        scheduleRepository.deleteAll();
    }

    @Test
    void addEndpointPersistsScheduleToMongo() throws Exception {
        mockMvc.perform(post("/add")
                .param("studentFirstName", "Аліса")
                .param("studentLastName", "Мельник")
                .param("teacherFirstName", "Іван")
                .param("teacherLastName", "Петренко")
                .param("courseName", "Вступ до програмування")
                .param("departmentName", "Комп`ютерні науки")
                .param("roomNumber", "210")
                .param("semester", "Осінь")
                .param("year", "2024")
                .param("startTime", "09:00:00")
                .param("endTime", "10:30:00"))
            .andExpect(status().is3xxRedirection());

        assertThat(scheduleRepository.findAll())
            .hasSize(1)
            .extracting(Schedule::getCourseName)
            .containsExactly("Вступ до програмування");
    }

    @Test
    void savedSchedulesAreRenderedOnTheMainPage() throws Exception {
        scheduleRepository.save(new Schedule(
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
        ));

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("schedule"))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("Аліса")))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("Вступ до програмування")));
    }

    @Test
    void addPageRendersFormWithEmptyScheduleModel() throws Exception {
        mockMvc.perform(get("/add"))
            .andExpect(status().isOk())
            .andExpect(view().name("add"))
            .andExpect(model().attributeExists("schedule"));
    }

    @Test
    void editPageRendersFormWithExistingScheduleModel() throws Exception {
        Schedule saved = scheduleRepository.save(new Schedule(
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
        ));

        mockMvc.perform(get("/edit/{id}", saved.getId()))
            .andExpect(status().isOk())
            .andExpect(view().name("edit"))
            .andExpect(model().attributeExists("schedule"))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("Вступ до програмування")));
    }

    @Test
    void editPageRedirectsToMainPageWhenScheduleIsMissing() throws Exception {
        mockMvc.perform(get("/edit/{id}", "missing-id"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }

    @Test
    void editEndpointUpdatesPersistedScheduleInMongo() throws Exception {
        Schedule saved = scheduleRepository.save(new Schedule(
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
        ));

        mockMvc.perform(post("/edit/{id}", saved.getId())
                .param("studentFirstName", "Оновлена Аліса")
                .param("studentLastName", "Мельник")
                .param("teacherFirstName", "Іван")
                .param("teacherLastName", "Петренко")
                .param("courseName", "Тестування ПЗ")
                .param("departmentName", "QA")
                .param("roomNumber", "305")
                .param("semester", "Весна")
                .param("year", "2026")
                .param("startTime", "11:00:00")
                .param("endTime", "12:30:00"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        Schedule updated = scheduleRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getStudentFirstName()).isEqualTo("Оновлена Аліса");
        assertThat(updated.getCourseName()).isEqualTo("Тестування ПЗ");
        assertThat(updated.getDepartmentName()).isEqualTo("QA");
        assertThat(updated.getRoomNumber()).isEqualTo("305");
    }

    @Test
    void editEndpointRedirectsToMainPageWhenScheduleIsMissing() throws Exception {
        mockMvc.perform(post("/edit/{id}", "missing-id")
                .param("studentFirstName", "Оновлена Аліса")
                .param("studentLastName", "Мельник")
                .param("teacherFirstName", "Іван")
                .param("teacherLastName", "Петренко")
                .param("courseName", "Тестування ПЗ")
                .param("departmentName", "QA")
                .param("roomNumber", "305")
                .param("semester", "Весна")
                .param("year", "2026")
                .param("startTime", "11:00:00")
                .param("endTime", "12:30:00"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        assertThat(scheduleRepository.findAll()).isEmpty();
    }

    @Test
    void deleteEndpointRemovesPersistedScheduleFromMongo() throws Exception {
        Schedule saved = scheduleRepository.save(new Schedule(
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
        ));

        mockMvc.perform(post("/delete/{id}", saved.getId()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        assertThat(scheduleRepository.findById(saved.getId())).isEmpty();
    }
}
