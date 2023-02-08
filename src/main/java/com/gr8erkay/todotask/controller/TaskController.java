package com.gr8erkay.todotask.controller;

import com.gr8erkay.todotask.dto.RequestDto;
import com.gr8erkay.todotask.model.User;
import com.gr8erkay.todotask.services.implementation.TaskServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @GetMapping("/add_Task")
    public String taskForm(Model model) {
        model.addAttribute("task", new RequestDto());
        return "/addTask";
    }


    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("task") RequestDto taskRequestDto, HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");  //What I need in the backend
        taskServiceImpl.addTask(user, taskRequestDto);

        return "redirect:/home";
    }

    @GetMapping("/edit_task/{taskId}")
    public String editForm(Model model, @PathVariable Long taskId) {
        model.addAttribute("task", new RequestDto());
        model.addAttribute("taskId", taskId);
        return "edit";
    }

    @PostMapping("/editTask/{taskId}")
    public String editTask(@ModelAttribute("task") RequestDto requestDto, @PathVariable Long taskId) {
        taskServiceImpl.updateTask(requestDto.getTitle(), requestDto.getDescription(), taskId);
        return "redirect:/home";
    }

    @GetMapping("/delete_task/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskServiceImpl.deleteTask(taskId);
        return "redirect:/home";
    }
    @GetMapping("/forward_Task/{forwardTask}")
    public String forwardTask(@PathVariable Long forwardTask) {
        taskServiceImpl.startTask(forwardTask);
        return "redirect:/home";
    }

    @GetMapping("/reverse_Task/{reverseTask}")
    public String reverseStatus(@PathVariable Long reverseTask) {
        taskServiceImpl.pendTask(reverseTask);
        return "redirect:/home";
    }

    @GetMapping("/complete_Task/{completedTask}")
    public String completedStatus(@PathVariable Long completedTask) {
        taskServiceImpl.finishTask(completedTask);
        return "redirect:/home";
    }




}
