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
@ApiModel(description = "Входные данные для создания подкатегории продукта")
public class SubcategoryAddIn {
    @NotNull(message = "Не указана категория")
    @JsonProperty("categoryId")
    @ApiModelProperty(name = "categoryId", dataType = "java.lang.Long", example = "1", required = true, position = 1)
    private Long categoryId;

    @NotNull(message = "Не указано название подкатегории")
    @NotBlank(message = "Название подкатегории не должно быть пустым")
    @JsonProperty("name")
    @ApiModelProperty(name = "name", dataType = "java.lang.String", example = "Моя подкатегория", required = true, position = 2)
    private String name;
}
