package spring.currency.service.dto.currency;

import java.util.Objects;

public class CreateCurrencyDto {

    private String title;
    private String description;
    private String code;

    public CreateCurrencyDto() {
    }

     public CreateCurrencyDto(
            String title,
            String description,
            String code) {
        this.title = title;
        this.description = description;
        this.code = code;
    }

    public static CreateCurrencyDtoBuilder builder() {
        return new CreateCurrencyDtoBuilder();
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

    public static class CreateCurrencyDtoBuilder {
        private String title;
        private String description;
        private String code;

        CreateCurrencyDtoBuilder() {
        }

        public CreateCurrencyDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public CreateCurrencyDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateCurrencyDtoBuilder code(String code) {
            this.code = code;
            return this;
        }

        public CreateCurrencyDto build() {
            return new CreateCurrencyDto(title, description, code);
        }

        public String toString() {
            return "CreateCurrencyDto.CreateCurrencyDtoBuilder(title=" + this.title + ", description=" + this.description + ", code=" + this.code + ")";
        }
    }
}
