package hu.uni.eszterhazy.exceptions;

public class InvalidChip extends Throwable {
    public InvalidChip(String chip) {
        super(chip);
    }
}
