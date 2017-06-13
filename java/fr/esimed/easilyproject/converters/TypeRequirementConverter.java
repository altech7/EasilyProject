package fr.esimed.easilyproject.converters;

import fr.esimed.easilyproject.enums.TypeRequirement;

import javax.persistence.AttributeConverter;

public class TypeRequirementConverter implements AttributeConverter<TypeRequirement, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TypeRequirement type) {
        return type != null ? type.getCode() : null;
    }

    @Override
    public TypeRequirement convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return TypeRequirement.getTypeRequirement(code);
    }

}
