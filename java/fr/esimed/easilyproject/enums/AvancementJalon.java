package fr.esimed.easilyproject.enums;

public enum AvancementJalon {

    UNSTART(0, "unstart"), STARTED(50, "started"), FINISHED(100, "finished");

    private int avancement;
    private String state;

    AvancementJalon(int avancement, String state) {
        this.avancement = avancement;
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }

    public int getAvancement() {
        return avancement;
    }

    public void setAvancement(int avancement) {
        this.avancement = avancement;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
