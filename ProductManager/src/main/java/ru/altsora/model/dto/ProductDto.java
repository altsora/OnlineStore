package ru.altsora.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @JsonProperty("id")
    @ApiModelProperty(name = "id", dataType = "java.lang.Long", value = "1", required = true, position = 1)
    private long id;
    @JsonProperty("name")
    @ApiModelProperty(name = "name", dataType = "java.lang.String", value = "Жёлтая машинка Х5", required = true, position = 2)
    private String name;
    @JsonProperty("price")
    @ApiModelProperty(name = "price", dataType = "java.lang.Double", value = "123.45", required = true, position = 3)
    private double price;
    @JsonProperty("isAvailable")
    @ApiModelProperty(name = "isAvailable", dataType = "java.lang.Boolean", value = "true", required = true, position = 4)
    private boolean isAvailable;
    @JsonProperty("description")
    @ApiModelProperty(name = "description", dataType = "java.lang.String", value = "Очень качественная и современная машинка", required = true, position = 5)
    private String description;
}
