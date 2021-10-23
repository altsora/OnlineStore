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

    public static final String SUBCATEGORY_NOT_FOUND_ID = "Подкатегория c ID '%s' не найдена";
    public static final String SUBCATEGORY_NOT_FOUND = "Подкатегория с именем '%s' и ID категории '%s' не найдена";
    public static final String SUBCATEGORY_EMPTY_NAME = "Название подкатегории не может быть пустым";
    public static final String SUBCATEGORY_EXISTS = "Подкатегория с именем '%s' и ID категории '%s' уже существует";

    public static final String PRODUCT_NOT_FOUND_ID = "Товар с ID '%s' не найден";

    public static final String STORAGE_PRODUCT_NOT_FOUND_ID = "Товар с ID '%s' на складе не найден";
    public static final String STORAGE_PLUS_OK = "Количество товара успешно увеличено: %s -> %s";
    public static final String STORAGE_MINUS_OK = "Количество товара успешно уменьшено: %s -> %s";
    public static final String STORAGE_MINUS_BAD = "Не удалось уменьшить количество товара. Текущее значение: %s. Запрашиваемое: %s";
}
