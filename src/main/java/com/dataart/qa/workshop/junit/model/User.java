package com.dataart.qa.workshop.junit.model;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private String name;
    @NonNull private String surname;
    private int yearOfBirth;

    private static UserBuilder builder() {
        return new UserBuilder();
    }

    public static UserBuilder builder(String surname){
        return builder().surname(surname);
    }
}
