package hu.uni.eszterhazy.model;

import hu.uni.eszterhazy.exceptions.InvalidChip;
import hu.uni.eszterhazy.exceptions.InvalidDatum;
import hu.uni.eszterhazy.exceptions.InvalidKor;

import java.time.LocalDate;
import java.util.Objects;

public class Kutya {

    private String nev;
    private String chip;
    private Fajta fajta;
    private Ivar ivar;
    private boolean ivartalanitva;
    private int kor=-1;
    private LocalDate oltas_ideje;
    private String tulaj_neve;

    public Kutya() {
    }

    public Kutya(String nev, String chip, Fajta fajta, Ivar ivar, boolean ivartalanitva, LocalDate oltas_ideje, int kor, String tulaj_neve) {
        this.nev = nev;
        this.chip = chip;
        this.fajta = fajta;
        this.ivar = ivar;
        this.ivartalanitva = ivartalanitva;
        this.oltas_ideje = oltas_ideje;
        this.kor = kor;
        this.tulaj_neve = tulaj_neve;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) throws InvalidChip {
        if(!chip.matches("[1-9]\\d{15}")){
            throw new InvalidChip(chip);

        }
        this.chip = chip;
    }

    public Fajta getFajta() {
        return fajta;
    }

    public void setFajta(Fajta fajta) {
        this.fajta = fajta;
    }

    public Ivar getIvar() {
        return ivar;
    }

    public void setIvar(Ivar ivar) {
        this.ivar = ivar;
    }

    public boolean isIvartalanitva() {
        return ivartalanitva;
    }

    public void setIvartalanitva(boolean ivartalanitva) {
        this.ivartalanitva = ivartalanitva;
    }

    public LocalDate getOltas_ideje() {
        return oltas_ideje;
    }

    public void setOltas_ideje(LocalDate oltas_ideje) throws InvalidKor, InvalidDatum {
        if(kor<0){
            throw new InvalidKor();
        }
        if(oltas_ideje.isAfter(LocalDate.now())  ||
                oltas_ideje.isBefore(LocalDate.now().minusYears(this.kor))){
            throw new InvalidDatum(oltas_ideje);
        }
        this.oltas_ideje = oltas_ideje;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) throws InvalidKor {
        if(kor<0 || kor > 30 ){
            throw new InvalidKor();
        }
        this.kor = kor;
    }

    public String getTulaj_neve() {
        return tulaj_neve;
    }

    public void setTulaj_neve(String tulaj_neve) {
        this.tulaj_neve = tulaj_neve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kutya kutya = (Kutya) o;
        return ivartalanitva == kutya.ivartalanitva &&
                kor == kutya.kor &&
                Objects.equals(nev, kutya.nev) &&
                Objects.equals(chip, kutya.chip) &&
                fajta == kutya.fajta &&
                ivar == kutya.ivar &&
                Objects.equals(oltas_ideje, kutya.oltas_ideje) &&
                Objects.equals(tulaj_neve, kutya.tulaj_neve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nev, chip, fajta, ivar, ivartalanitva, kor, oltas_ideje, tulaj_neve);
    }
}
