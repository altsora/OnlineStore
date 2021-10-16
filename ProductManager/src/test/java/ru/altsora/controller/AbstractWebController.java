package ru.altsora.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.altsora.util.Converter;

@AutoConfigureMockMvc
@SpringBootTest
public class AbstractWebController {
    protected final String RET_CODE = "$.retCode";
    protected final String RET_MESSAGE = "$.retMessage";
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected Converter converter;
}
