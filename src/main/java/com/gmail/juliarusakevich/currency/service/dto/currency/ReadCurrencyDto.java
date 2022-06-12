package com.gmail.juliarusakevich.currency.service.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonDeserialize(builder = ReadCurrencyDto.ReadCurrencyDtoBuilder.class)
public class ReadCurrencyDto {

    private final Integer id;
    private final String title;
    private final String description;
    private final String code;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public ReadCurrencyDto(Integer id, String title, String description, String code, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.code = code;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static ReadCurrencyDtoBuilder builder() {
        return new ReadCurrencyDtoBuilder();
    }

    public Integer getId() {
        return id;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadCurrencyDto)) return false;
        ReadCurrencyDto that = (ReadCurrencyDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCode(), that.getCode()) && Objects.equals(getCreateDate(), that.getCreateDate()) && Objects.equals(getUpdateDate(), that.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getCode(), getCreateDate(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "ReadCurrencyDto{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", code='" + code + '\'' +
               ", createDate=" + createDate +
               ", updateDate=" + updateDate +
               '}';
    }

    public static class ReadCurrencyDtoBuilder {
        private Integer id;
        private String title;
        private String description;
        private String code;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        ReadCurrencyDtoBuilder() {
        }

        @JsonProperty(value = "id")
        public ReadCurrencyDtoBuilder id(Integer id) {
            this.id = id;
            return this;
        }

       // @JsonProperty(value = "title")
        public ReadCurrencyDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

      //  @JsonProperty(value = "description")
        public ReadCurrencyDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        //@JsonProperty(value = "code")
        public ReadCurrencyDtoBuilder code(String code) {
            this.code = code;
            return this;
        }

       // @JsonProperty(value = "creation_version")
        public ReadCurrencyDtoBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

       // @JsonProperty(value = "update_version")
        public ReadCurrencyDtoBuilder updateDate(LocalDateTime updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public ReadCurrencyDto build() {
            return new ReadCurrencyDto(id, title, description, code, createDate, updateDate);
        }

        public String toString() {
            return "ReadCurrencyDto.ReadCurrencyDtoBuilder(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", code=" + this.code + ", createDate=" + this.createDate + ", updateDate=" + this.updateDate + ")";
        }
    }
}
