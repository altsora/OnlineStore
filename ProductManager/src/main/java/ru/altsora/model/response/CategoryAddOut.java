package ru.altsora.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@NotNull
public class CategoryAddOut {
    @NotNull
    @JsonProperty("id")
    @ApiModelProperty(name = "id", dataType = "java.lang.Long", value = "1", required = true, position = 1)
    private long id;

    @NotNull
    @NotBlank
    @JsonProperty("name")
    @ApiModelProperty(name = "name", dataType = "java.lang.String", value = "Техника", required = true, position = 1)
    private String name;
}
