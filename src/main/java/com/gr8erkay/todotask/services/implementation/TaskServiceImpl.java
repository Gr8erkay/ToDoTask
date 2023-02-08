package com.gr8erkay.todotask.services.implementation;


import com.gr8erkay.todotask.dto.RequestDto;
import com.gr8erkay.todotask.enums.Status;
import com.gr8erkay.todotask.model.Task;
import com.gr8erkay.todotask.model.User;
import com.gr8erkay.todotask.repository.TaskRepository;
import com.gr8erkay.todotask.services.TaskService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

     @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTask(User user, RequestDto requestDto) {
        Task task = new Task();
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setTimeCreated(LocalDate.now());
        task.setStatus(Status.PENDING);
        task.setUser(user);
        taskRepository.save(task);
        return task;
    }

    @Override
    public void editTask(Long taskId, RequestDto requestDto) {
        Task task = new Task();
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        taskRepository.save(task);

    }

    @Override
    public void updateTask(String title, String description, Long taskId) {

        taskRepository.updateTask(title, description, taskId);
        taskRepository.updateTime(LocalDate.now(), taskId);
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId) {

        taskRepository.deleteById(taskId);
    }

    @Override
    public void pendTask(Long taskId) {

        Optional<Task> found = taskRepository.findById(taskId);
        if (found.isPresent()) {
            taskRepository.pendTask("PENDING", taskId);
            taskRepository.updateTime(LocalDate.now(), taskId);
        }
    }

    @Override
    public void startTask(Long taskId) {

        Optional<Task> found = taskRepository.findById(taskId);
        if (found.isPresent()) {
            taskRepository.startTask("ONGOING", taskId);
            taskRepository.updateTime(LocalDate.now(), taskId);
        }

    }

    @Override
    public void finishTask(Long taskId) {

        Optional<Task> found = taskRepository.findById(taskId);
        if (found.isPresent()) {
            taskRepository.finishTask("COMPLETED", taskId);
            taskRepository.completedTime(LocalDate.now(),taskId);
        }
    }

    @Override
    public List<Task> findTaskByStatus(Status status, Long userId) {
//        httpSession to get user id

        List<Task> allTask = taskRepository.findAllTaskByStatus(status, userId);
        return allTask;
    }

    @Override
    public List<Task> findAllTask(Long userId) {
//        List<Task> allTasks = new List<Task>
        List<Task> allTask = taskRepository.findAllTask(userId);

        return allTask;
    }
}

//
//    @Override
//    public void addTask(User user, Task task) {
//
//    }
//
//    @Override
//    public void editTask(User user, Task task) {
//
//    }
//
//    @Override
//    public void updateTask(Task task) {
//
//    }
//
//    @Override
//    public void deleteTask(Task task) {
//
//    }
//
//
//    @Override
//    public Task findTaskByStatus(Task task, Status status) {
//        return null;
//    }
//
//    @Override
//    public Task getTaskById(Long id) {
//        return null;
//    }
//}
//

