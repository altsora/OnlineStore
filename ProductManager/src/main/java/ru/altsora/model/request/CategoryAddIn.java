package ru.altsora.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Входные данные для создания категории продукта")
public class CategoryAddIn {
    @NotNull(message = "Не указано название категории")
    @NotBlank(message = "Название категории не должно быть пустым")
    @JsonProperty("name")
    @ApiModelProperty(name = "name", dataType = "java.lang.String", example = "Для дома", required = true, position = 1)
    private String name;
}
