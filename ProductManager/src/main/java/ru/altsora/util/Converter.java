package ru.altsora.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.altsora.exception.ConvertJsonException;

@Component
@RequiredArgsConstructor
public class Converter {
    private final ObjectMapper objectMapper;

    public String toJson(final Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException ex) {
            throw new ConvertJsonException(ex);
        }
    }

    public String toPrettyJson(final Object o) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException ex) {
            throw new ConvertJsonException(ex);
        }
    }

    public <T> T fromJson(final TypeReference<T> type, final String json) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException ex) {
            throw new ConvertJsonException(ex);
        }
    }
}
