package com.gmail.juliarusakevich.currency.dao.model;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


/*
1. Справочник валют
   Наименование
   Описание
   Код
2. Сохранять дату создания дата/время
3. Сохранять дату обновления дата/время
 */
@Builder
@Entity
@Table(name = "currencies", schema = "currency_guide")

public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    //потом заменить на sequence
    private Integer id;
    private String title;
    private String description;
    private String code;
    @Column(name = "creation_version")
    private LocalDateTime createDate;
    @Column(name = "update_version")
    private LocalDateTime updateDate;

    public Currency() {
    }

    public Currency(Integer id, String title, String description, String code, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.code = code;
        this.createDate = createDate;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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
        if (!(o instanceof Currency)) return false;
        Currency currency = (Currency) o;
        return Objects.equals(getId(), currency.getId()) && Objects.equals(getTitle(), currency.getTitle()) && Objects.equals(getDescription(), currency.getDescription()) && Objects.equals(getCode(), currency.getCode()) && Objects.equals(getCreateDate(), currency.getCreateDate()) && Objects.equals(getUpdateDate(), currency.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getCode(), getCreateDate(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "Currency{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", code='" + code + '\'' +
               ", createDate=" + createDate +
               ", updateDate=" + updateDate +
               '}';
    }
}