package tugasakhir.library.model.enums;

public enum CompletionStatus {
    SUCCESS("Success"),
    BUSINESS_ERROR("Business Error"),
    SYSTEM_ERROR("System Error");

    public final String label;

    private CompletionStatus(String label) {
        this.label = label;
    }

}
