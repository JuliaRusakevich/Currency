package com.gmail.juliarusakevich.currency.service.dto.currency;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
public class UpdateCurrencyDto {

    private Integer id;
    private String title;
    private String description;
    private String code;
    private LocalDateTime updateDate;

    public UpdateCurrencyDto() {
    }

    @JsonCreator
    public UpdateCurrencyDto(
            @JsonProperty(value = "id") Integer id,
            @JsonProperty(value = "title") String title,
            @JsonProperty(value = "description") String description,
            @JsonProperty(value = "code") String code,
            @JsonProperty(value = "update_version") LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.code = code;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateCurrencyDto)) return false;
        UpdateCurrencyDto that = (UpdateCurrencyDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCode(), that.getCode()) && Objects.equals(getUpdateDate(), that.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getCode(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "UpdateCurrencyDto{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", code='" + code + '\'' +
               ", updateDate=" + updateDate +
               '}';
    }
}
