package com.uhp.tinytypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */
@RequiredArgsConstructor
@ToString
public class Email {
    @Getter
    public final String value;
}
