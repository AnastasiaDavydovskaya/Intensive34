package ru.aston.davydovskaya_av.task9.convertors;

import javax.persistence.AttributeConverter;
import java.awt.*;

public class ColorConvertor implements AttributeConverter<Color, String> {

    @Override
    public String convertToDatabaseColumn(Color attribute) {
        return "#"+Integer.toHexString(attribute.getRGB()).substring(2);
    }

    @Override
    public Color convertToEntityAttribute(String dbData) {
        return Color.decode(dbData);
    }
}
