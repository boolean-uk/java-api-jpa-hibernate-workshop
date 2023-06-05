package com.booleanuk.api.validation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public class NullChecker {
        public static boolean checkIfNullExists(Object target) {
            return Arrays.stream(target.getClass()
                            .getDeclaredFields())
                    .peek(f -> f.setAccessible(true))
                    .map(f -> getFieldValue(f, target))
                    .anyMatch(x-> Objects.isNull(x))
                    ;
        }

        private static Object getFieldValue(Field field, Object target) {
            try {
                return field.get(target);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }


}
