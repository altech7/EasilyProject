package fr.esimed.easilyproject.enums;

public enum EtatJalon {

    NOT_FINISH(0, "Pas démarré"),
    STARTED(50, "En cours"),
    FINISHED(100, "Terminé");

    private int code;
    private String state;

    EtatJalon(int code, String state) {
        this.code = code;
        this.state = state;
    }

    /**
     * Récupération des données.
     *
     * @param code
     * @return
     */
    public static EtatJalon getEtatJalon(Integer code) {

        EtatJalon[] resources = EtatJalon.values();

        for (EtatJalon res : resources) {
            if (res.code == code) {
                return res;
            }
        }

        return NOT_FINISH;
    }
    @Override
    public String toString() {
        return state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
