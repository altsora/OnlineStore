package ru.altsora.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NotNull
@ApiModel(description = "Результат обновление количества товара на складе")
public class ProductStorageUpdate {
    @JsonProperty("result")
    @ApiModelProperty(name = "result", dataType = "java.lang.Boolean", example = "true", value = "Результат обновления", required = true, position = 1)
    private boolean result;

    @JsonProperty("message")
    @ApiModelProperty(name = "message", dataType = "java.lang.String", example = "Товар добавлен", value = "Сообщение о результате операции", required = true, position = 2)
    private String message;
}
