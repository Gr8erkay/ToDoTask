package com.gr8erkay.todotask.repository;

import com.gr8erkay.todotask.enums.Status;
import com.gr8erkay.todotask.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Transactional
//    @Query(value = "update task t set t.status = 1 where t.task_id = ?1", nativeQuery = true)
//    void moveToProgress (Long taskId);
    @Query(value = "update task t set t.status = ?1 where t.task_id = ?2", nativeQuery = true)
    void startTask(String status, Long taskId);

    @Transactional
    @Modifying
    @Query(value ="update task t set t.status = ?1 where t.task_id = ?2", nativeQuery = true)
    void finishTask(String status, Long taskId);

    @Transactional
    @Modifying
    @Query(value ="update task t set t.status = ?1  where t.task_id = ?2", nativeQuery = true)
    void pendTask(String status, Long taskId);

    @Modifying
    @Transactional
    @Query(value = "update task t set t.time_updated = ?1 where t.task_id = ?2", nativeQuery = true)
    void updateTime(LocalDate time, Long taskId);

    @Modifying
    @Transactional
    @Query(value = "update task t set t.time_completed = ?1 where t.task_id = ?2", nativeQuery = true)
    void completedTime(LocalDate time, Long taskId);

    @Query(value = "select * from task t where t.user_id = ?1 ", nativeQuery = true)
    List<Task> findAllTask(Long userId);

    @Modifying
    @Transactional
    @Query(value = "update task t set t.title = ?1, t.description = ?2 where t.task_id = ?3",
            nativeQuery = true)
    void updateTask(String title, String description, Long taskId);


    @Modifying
    @Transactional
    @Query(value = "select * from task t where t.status = ?1 and t.user_id = ?2", nativeQuery = true)
    List<Task> findAllTaskByStatus(Status status, Long userId);

    @Modifying
    @Transactional
    @Query(value = "select * from task t where t.status = ?1 and t.user_id = ?2", nativeQuery = true)
    List<Task> findAllTaskByInProgress(Status status, Long userId);

    @Modifying
    @Transactional
    @Query(value = "select * from task t where t.status = ?1 and t.user_id = ?2", nativeQuery = true)
    List<Task> findAllTaskByCompleted(String status, Long userId);
}
