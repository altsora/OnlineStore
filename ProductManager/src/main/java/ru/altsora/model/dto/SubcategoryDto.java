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
public class SubcategoryDto {
    @JsonProperty("id")
    @ApiModelProperty(name = "id", dataType = "java.lang.Long", value = "1", required = true, position = 1)
    private long id;
    @JsonProperty("categoryId")
    @ApiModelProperty(name = "categoryId", dataType = "java.lang.Long", value = "2", required = true, position = 2)
    private long categoryId;
    @JsonProperty("name")
    @ApiModelProperty(name = "name", dataType = "java.lang.String", value = "Моя подкатегория", required = true, position = 3)
    private String name;
}
