package ru.aston.davydovskaya_av.task9.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.aston.davydovskaya_av.task9.convertors.ColorConvertor;

import javax.persistence.*;
import java.awt.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Product extends AbstractEntity {

    private String title;
    private BigDecimal price;
    @Convert(converter = ColorConvertor.class)
    private Color color;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
}
