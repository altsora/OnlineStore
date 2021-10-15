package ru.altsora.util;

/**
 * Класс для текстовых сообщений сервисов.
 */
public final class RetMessage {
    private RetMessage() {
        throw new IllegalStateException("Utility class");
    }

    public static final String NOT_FOUND_CATEGORY_ID = "Категория с ID '%s' не найдена";
    public static final String NOT_FOUND_CATEGORY_NAME = "Категория с именем '%s' не найдена";
}
