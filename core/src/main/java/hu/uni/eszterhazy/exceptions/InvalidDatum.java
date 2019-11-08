package hu.uni.eszterhazy.exceptions;

import java.time.LocalDate;

public class InvalidDatum extends Throwable {
    public InvalidDatum(LocalDate oltas_ideje) {
    }
}
