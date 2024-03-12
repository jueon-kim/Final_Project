package com.hwagae.market.category;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "category")
public class CategoryEntity {

    @Id
    private Integer categoryNum;

    @Column
    private String categoryName;
}