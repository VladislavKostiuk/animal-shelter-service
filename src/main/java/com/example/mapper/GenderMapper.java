package com.example.mapper;

import com.example.enums.Gender;
import org.mapstruct.Named;

public interface GenderMapper {
    @Named("genderName")
    default String genderToGenderName(Gender gender) {
        return gender.toString();
    }

    @Named("gender")
    default Gender genderNameToGender(String genderName) {
        return Gender.valueOf(genderName);
    }
}
