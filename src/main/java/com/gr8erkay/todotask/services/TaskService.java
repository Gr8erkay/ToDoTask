package com.gr8erkay.todotask.services;


import com.gr8erkay.todotask.dto.RequestDto;
import com.gr8erkay.todotask.enums.Status;
import com.gr8erkay.todotask.model.Task;
import com.gr8erkay.todotask.model.User;
import com.gr8erkay.todotask.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task addTask(User user, RequestDto requestDto);

    void editTask(Long taskId, RequestDto requestDto);

    void updateTask(String title, String description, Long taskId);

    void deleteTask(Long taskId);

    void pendTask(Long taskId);

    void startTask(Long taskId);

    void finishTask(Long taskId);

    List<Task> findTaskByStatus(Status status, Long userId);

    List<Task> findAllTask(Long userId);



//    List<Task> getTasksByStatus(Status status) throws Exception;
}

