package ru.altsora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.altsora.util.JsonConverter;

import java.util.function.Function;

@AutoConfigureMockMvc
@SpringBootTest
public class AbstractWebController {
    protected final String RET_CODE = "$.retCode";
    protected final String ERROR_TYPE = "$.errorType";
    protected final Function<Integer, String> ERROR_MESSAGES = i -> "$.errorMessages[" + i + "]";
    protected final String ERROR_MESSAGES_LENGTH = "$.errorMessages.length()";
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected JsonConverter jsonConverter;
}
