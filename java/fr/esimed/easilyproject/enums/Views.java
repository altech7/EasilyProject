package fr.esimed.easilyproject.enums;

public enum Views {

    INDEX("index"),

    PROJECTS_LIST("projects"),
    JALONS_LIST("jalons"),
    TASKS_LIST("tasks"),
    CLIENTS_LIST("client.list"),
    REQUIREMENT_TASKS_LIST("requirement.tasks.list"),
    JALONS_TASKS_LIST("jalon.tasks.list"),

    PROJECT_DETAILS("project.details"),
    PROJECT_TIMELINE("project.timeline"),

    PROJECT_FORM("project.form"),
    REQUIREMENT_FORM("requirement.form"),
    TASK_FORM("task.form"),
    CLIENT_FORM("client.form"),
    JALON_FORM("jalon.form"),
    JALON_DELIVER_FORM("jalon.deliver.form"),
    JALON_TASK_FORM("jalon.task.form"),

    CONTACT_FORM("contact.form"),

    SIGNIN("signin"),
    SIGNUP("signup"),
    LOGOUT("logout");

    private String view;

    Views(String view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return view;
    }
}
