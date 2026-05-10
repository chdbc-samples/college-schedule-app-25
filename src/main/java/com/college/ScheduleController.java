package com.college;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Веб-контролер для перегляду розкладу коледжу, додавання нових занять та видалення записів.
 */
@Controller
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping("/")
    public String viewSchedule(Model model) {
        List<Schedule> schedules = scheduleRepository.findAll();
        model.addAttribute("schedules", schedules);
        return "schedule";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("schedule", schedule.get());
        return "edit";
    }

    @PostMapping("/add")
    public String addSchedule(@ModelAttribute Schedule schedule) {
        scheduleRepository.save(schedule);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editSchedule(@PathVariable String id, @ModelAttribute Schedule schedule) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(id);
        if (existingSchedule.isEmpty()) {
            return "redirect:/";
        }

        Schedule scheduleToUpdate = existingSchedule.get();
        scheduleToUpdate.setStudentFirstName(schedule.getStudentFirstName());
        scheduleToUpdate.setStudentLastName(schedule.getStudentLastName());
        scheduleToUpdate.setTeacherFirstName(schedule.getTeacherFirstName());
        scheduleToUpdate.setTeacherLastName(schedule.getTeacherLastName());
        scheduleToUpdate.setCourseName(schedule.getCourseName());
        scheduleToUpdate.setDepartmentName(schedule.getDepartmentName());
        scheduleToUpdate.setRoomNumber(schedule.getRoomNumber());
        scheduleToUpdate.setSemester(schedule.getSemester());
        scheduleToUpdate.setYear(schedule.getYear());
        scheduleToUpdate.setStartTime(schedule.getStartTime());
        scheduleToUpdate.setEndTime(schedule.getEndTime());
        scheduleRepository.save(scheduleToUpdate);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable String id) {
        scheduleRepository.deleteById(id);
        return "redirect:/";
    }
}
