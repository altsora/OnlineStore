package ru.altsora.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.altsora.ProductManagerApplication;
import ru.altsora.exception.ConvertJsonException;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;

/**
 * Вспомогательный бин по работе с конвертацией объектов в JSON и обратно.
 */
@Component
@RequiredArgsConstructor
public class JsonConverter {
    private final ObjectMapper objectMapper;

    @Data
    @NoArgsConstructor
    public static class User {
        private String name;
    }

    /**
     * Конвертирует объект в JSON-строку.
     */
    public String toJson(final Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException ex) {
            throw new ConvertJsonException(ex);
        }
    }

    /**
     * Конвертирует объект в человекочитаемую JSON-строку.
     */
    public String toPrettyJson(final Object o) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException ex) {
            throw new ConvertJsonException(ex);
        }
    }

    /**
     * Конвертирует JSON-строку в объект.
     *
     * @param <T> тип целевого объекта
     */
    public <T> T fromJson(final TypeReference<T> type, final String json) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException ex) {
            throw new ConvertJsonException(ex);
        }
    }
}
