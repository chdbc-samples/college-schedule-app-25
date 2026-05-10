package com.college;

import static com.college.support.ScheduleTestDataBuilder.aSchedule;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

class ScheduleControllerTest {

    @Test
    void viewScheduleReturnsScheduleViewName() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.viewSchedule(model);

        assertEquals("schedule", viewName);
    }

    @Test
    void viewScheduleAddsAllSchedulesToModel() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        Schedule schedule = aSchedule().build();
        when(repository.findAll()).thenReturn(List.of(schedule));
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        controller.viewSchedule(model);

        verify(repository, times(1)).findAll();
        assertNotNull(model.getAttribute("schedules"));
        assertEquals(1, ((List<?>) model.getAttribute("schedules")).size());
    }

    @Test
    void showAddFormReturnsAddViewName() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.showAddForm(model);

        assertEquals("add", viewName);
    }

    @Test
    void showAddFormAddsEmptyScheduleToModel() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        controller.showAddForm(model);

        assertNotNull(model.getAttribute("schedule"));
    }

    @Test
    void showEditFormReturnsEditViewNameForExistingSchedule() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        Schedule schedule = aSchedule().build();
        ReflectionTestUtils.setField(schedule, "id", "id-123");
        when(repository.findById("id-123")).thenReturn(Optional.of(schedule));
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.showEditForm("id-123", model);

        assertEquals("edit", viewName);
        assertEquals(schedule, model.getAttribute("schedule"));
    }

    @Test
    void showEditFormRedirectsWhenScheduleIsMissing() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        when(repository.findById("missing-id")).thenReturn(Optional.empty());
        ScheduleController controller = new ScheduleController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.showEditForm("missing-id", model);

        assertEquals("redirect:/", viewName);
    }

    @Test
    void addScheduleSavesToRepositoryAndRedirects() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        ScheduleController controller = new ScheduleController(repository);
        Schedule schedule = aSchedule().build();

        String viewName = controller.addSchedule(schedule);

        verify(repository, times(1)).save(schedule);
        assertEquals("redirect:/", viewName);
    }

    @Test
    void editScheduleUpdatesExistingRecordAndRedirects() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        Schedule persistedSchedule = aSchedule().build();
        ReflectionTestUtils.setField(persistedSchedule, "id", "id-123");
        when(repository.findById("id-123")).thenReturn(Optional.of(persistedSchedule));
        ScheduleController controller = new ScheduleController(repository);
        Schedule editedSchedule = aSchedule()
            .withStudentFirstName("Оновлена Аліса")
            .withCourseName("Тестування ПЗ")
            .build();

        String viewName = controller.editSchedule("id-123", editedSchedule);

        verify(repository, times(1)).save(persistedSchedule);
        assertEquals("Оновлена Аліса", persistedSchedule.getStudentFirstName());
        assertEquals("Тестування ПЗ", persistedSchedule.getCourseName());
        assertEquals("redirect:/", viewName);
    }

    @Test
    void editScheduleRedirectsWhenScheduleIsMissing() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        when(repository.findById("missing-id")).thenReturn(Optional.empty());
        ScheduleController controller = new ScheduleController(repository);

        String viewName = controller.editSchedule("missing-id", aSchedule().build());

        assertEquals("redirect:/", viewName);
    }

    @Test
    void deleteScheduleRemovesFromRepositoryAndRedirects() {
        ScheduleRepository repository = mock(ScheduleRepository.class);
        ScheduleController controller = new ScheduleController(repository);

        String viewName = controller.deleteSchedule("id-123");

        verify(repository, times(1)).deleteById("id-123");
        assertEquals("redirect:/", viewName);
    }
}
