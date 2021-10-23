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
@ApiModel(description = "Информация по товару на складе")
public class ProductStorageAvailable {
    @JsonProperty("isAvailable")
    @ApiModelProperty(name = "isAvailable", dataType = "java.lang.Boolean", example = "true", value = "Доступность товара на складе", required = true, position = 1)
    private boolean isAvailable;

    @JsonProperty("amount")
    @ApiModelProperty(name = "amount", dataType = "java.lang.Integer", example = "66", value = "Количество товара на складе", required = true, position = 2)
    private int amount;
}
