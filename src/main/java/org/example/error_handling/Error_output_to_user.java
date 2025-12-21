package org.example.error_handling;

public class Error_output_to_user {
    private String error_message;
    private boolean error_yes_no = false;

    private static Error_output_to_user instance = new Error_output_to_user();

    private Error_output_to_user() {}

    public static Error_output_to_user getInstance() {
        return instance;
    }

    public boolean error_occurred() {
        return this.error_yes_no;
    }

    public String error_output() {
        this.error_yes_no = false;
        return error_message;
    }

    public void entering_error(String entering_error_message) {
        this.error_message = entering_error_message;
        this.error_yes_no = true;
    }
}
