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
import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Входные данные для обновления продукта")
public class ProductUpdateIn {
    @NotNull
    @JsonProperty("id")
    @ApiModelProperty(name = "id", dataType = "java.lang.Long", example = "1", required = true, position = 1)
    private Long id;

    @NotBlank(message = "Название продукта не должно быть пустым")
    @NotNull
    @JsonProperty("name")
    @ApiModelProperty(name = "name", dataType = "java.lang.String", example = "Жёлтая машинка Х5", required = true, position = 2)
    private String name;

    @NotNull
    @JsonProperty("categoryId")
    @ApiModelProperty(name = "categoryId", dataType = "java.lang.Long", example = "1", required = true, position = 3)
    private Long categoryId;

    @PositiveOrZero(message = "Цена товара не должна быть отрицательной")
    @NotNull
    @JsonProperty("price")
    @ApiModelProperty(name = "price", dataType = "java.lang.Double", example = "123.45", required = true, position = 4)
    private Double price;

    @NotNull
    @JsonProperty("isAvailable")
    @ApiModelProperty(name = "isAvailable", dataType = "java.lang.Boolean", example = "true", required = true, position = 5)
    private Boolean isAvailable;

    @NotBlank(message = "Описание товара не должно быть пустым")
    @NotNull
    @JsonProperty("description")
    @ApiModelProperty(name = "description", dataType = "java.lang.String", example = "Очень качественная и современная машинка", position = 6)
    private String description;

    @JsonProperty("subcategories")
    @ApiModelProperty(name = "subcategories", dataType = "[Ljava.lang.Long;", example = "[1, 2, 3]", position = 7)
    private Optional<Set<Long>> subcategories;
}
