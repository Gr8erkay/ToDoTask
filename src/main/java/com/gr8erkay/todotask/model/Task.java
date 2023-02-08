package com.gr8erkay.todotask.model;

import com.gr8erkay.todotask.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tasks")
public class Task {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long taskId;

        @Enumerated(EnumType.STRING)
        private Status status;

        @ManyToOne()
        @JoinColumn(name = "userId")
        private User user;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false)
        private String description;

        @CreationTimestamp
        private LocalDate timeCreated;

        private LocalDate timeUpdated;

        private LocalDate timeCompleted;

//        @Column(nullable = false, columnDefinition = "TEXT")
//        private String commentBody;

        // Getters and setters

}
