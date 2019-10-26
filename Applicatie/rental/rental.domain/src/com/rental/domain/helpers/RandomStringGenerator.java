package com.rental.domain.helpers;

import org.apache.commons.lang3.RandomStringUtils;

//Used to create randomly generated strings
public class RandomStringGenerator {

    public static String GenerateRandomAlphanumericString(int characters) {
        return RandomStringUtils.randomAlphanumeric(characters);
    }
}
