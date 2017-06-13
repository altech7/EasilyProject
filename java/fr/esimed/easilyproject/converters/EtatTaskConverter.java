package fr.esimed.easilyproject.converters;

import fr.esimed.easilyproject.enums.EtatTask;

import javax.persistence.AttributeConverter;

public class EtatTaskConverter implements AttributeConverter<EtatTask, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EtatTask type) {
        return type != null ? type.getCode() : null;
    }

    @Override
    public EtatTask convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return EtatTask.getEtatTask(code);
    }

}
