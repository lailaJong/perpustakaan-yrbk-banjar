package tugasakhir.library.model.enums;

public enum Status {
    OK("ok"),
    FAILED("failed"),
    ERROR("error");

    public final String label;
    private Status(String label) {
        this.label = label;
    }
}
