package com.gmail.juliarusakevich.currency.service.dto.currency;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.util.Objects;

@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateCurrencyDto {

    private String title;
    private String description;
    private String code;

    public CreateCurrencyDto() {
    }
    @JsonCreator
    public CreateCurrencyDto(
            @JsonProperty(value = "title") String title,
            @JsonProperty(value = "description") String description,
            @JsonProperty(value = "code") String code) {
        this.title = title;
        this.description = description;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateCurrencyDto)) return false;
        CreateCurrencyDto dto = (CreateCurrencyDto) o;
        return Objects.equals(title, dto.title) && Objects.equals(description, dto.description) && Objects.equals(code, dto.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, code);
    }

    @Override
    public String toString() {
        return "CreateCurrencyDto{" +
               "title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", code='" + code + '\'' +
               '}';
    }
}
