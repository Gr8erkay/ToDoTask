package com.gr8erkay.todotask.dto;

import com.gr8erkay.todotask.enums.Status;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class RequestDto {

    private String emailAddress;
    private String password;

    private String firstName;

    private String lastName;

    private String gender;

    private String title;
    private String description;
    private LocalDate timeCreated;
    private LocalDate timeUpdated;
    private LocalDate timeCompleted;

    public LocalDate getFormattedCreatedTime() {
        return LocalDate.parse(timeCreated.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public LocalDate getFormattedUpdatedTime() {
        return LocalDate.parse(timeUpdated.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    public LocalDate getFormattedCompletedTime() {
        return LocalDate.parse(timeCompleted.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
