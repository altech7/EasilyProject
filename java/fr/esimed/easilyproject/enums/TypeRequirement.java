package fr.esimed.easilyproject.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure définissant les différents type d'une exigence.
 */
public enum TypeRequirement {

    DATA(0, "Données"),
    PERFORMANCE(1, "Performance"),
    IHM(2, "Interface"),
    QUALITE(3, "Qualité"),
    SERVICE(4, "Service");

    private int code = 0;
    private String libelle = "";

    private TypeRequirement(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    /**
     * Récupération des données.
     *
     * @param code
     * @return
     */
    public static TypeRequirement getTypeRequirement(Integer code) {

        TypeRequirement[] resources = TypeRequirement.values();

        for (TypeRequirement res : resources) {
            if (res.code == code) {
                return res;
            }
        }

        return DATA;
    }

    public int getCode() {
        return this.code;
    }

    public static List<TypeRequirement> findAll() {

        final List<TypeRequirement> typeRequirements = new ArrayList<TypeRequirement>();

        typeRequirements.add(TypeRequirement.DATA);
        typeRequirements.add(TypeRequirement.IHM);
        typeRequirements.add(TypeRequirement.PERFORMANCE);
        typeRequirements.add(TypeRequirement.QUALITE);
        typeRequirements.add(TypeRequirement.SERVICE);

        return typeRequirements;
    }

    public String getLibelle() {
        return this.libelle;
    }
};
