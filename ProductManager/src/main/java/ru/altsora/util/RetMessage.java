package ru.altsora.util;

/**
 * Класс для текстовых сообщений сервисов.
 */
public final class RetMessage {
    private RetMessage() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_NOT_FOUND_ID = "Категория с ID '%s' не найдена";
    public static final String CATEGORY_NOT_FOUND_NAME = "Категория с именем '%s' не найдена";
    public static final String CATEGORY_EXISTS_NAME = "Категория с именем '%s' уже существует";
    public static final String CATEGORY_EMPTY_NAME = "Название категории не может быть пустым";
}
