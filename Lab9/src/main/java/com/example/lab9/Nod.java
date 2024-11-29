package com.example.lab9;

import java.util.Objects;
public class Nod<E> {
    private E info;
    private Nod<E> stg=null;
    private Nod<E> dr=null;
    public Nod(E info) { this.info = info; }
    public E getInfo() { return info; }
    public void setInfo(E info) { this.info = info; }
    public Nod<E> getStg() { return stg; }
    public void setStg(Nod<E> stg) { this.stg = stg; }
    public Nod<E> getDr() { return dr; }
    public void setDr(Nod<E> dr) { this.dr = dr; }
    public boolean esteFrunza() { return dr == null && stg == null; }
    @Override
    public String toString () { return info.toString (); }
    @Override
    public boolean equals(Object x) {
        if (this.info == x) return true;
        if (x == null || !(x instanceof Nod<?>)) return false;
        return Objects.equals(info, ((Nod)x).getInfo());
    }
    @Override
    public int hashCode() { return Objects.hash(info); }
}
