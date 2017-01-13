package com.uhp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

/**
 * @author Bogdan Kovalev.
 */
@Data
public class Master {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String email;
}
