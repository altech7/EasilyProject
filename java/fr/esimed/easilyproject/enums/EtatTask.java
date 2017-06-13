package fr.esimed.easilyproject.enums;

public enum EtatTask {

    UNSTART(0, "Non demarrée"), STARTED(50, "Demarrée"), FINISHED(100, "Terminée");

    private int code;
    private String state;

    EtatTask(int code, String state) {
        this.code = code;
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * Récupération des données.
     *
     * @param code
     * @return
     */
    public static EtatTask getEtatTask(Integer code) {

        EtatTask[] resources = EtatTask.values();

        for (EtatTask res : resources) {
            if (res.code == code) {
                return res;
            }
        }

        return UNSTART;
    }

}
