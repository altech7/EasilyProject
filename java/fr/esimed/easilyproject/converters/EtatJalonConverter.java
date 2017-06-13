package fr.esimed.easilyproject.converters;

import fr.esimed.easilyproject.enums.EtatJalon;

import javax.persistence.AttributeConverter;

public class EtatJalonConverter implements AttributeConverter<EtatJalon, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EtatJalon type) {
        return type != null ? type.getCode() : null;
    }

    @Override
    public EtatJalon convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return EtatJalon.getEtatJalon(code);
    }

}
