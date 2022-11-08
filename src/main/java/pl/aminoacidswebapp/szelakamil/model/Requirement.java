package pl.aminoacidswebapp.szelakamil.model;

import javax.persistence.*;

@Entity
@Table(name = "requirements")
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer isoleucine;
    private Integer leucine;
    private Integer lysine;
    private Integer methionine;
    private Integer cysteine;
    private Integer phenylalanine_plus_tyrosine;
    private Integer threonine;
    private Integer tryptophan;
    private Integer valine;
    private Integer histidine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIsoleucine() {
        return isoleucine;
    }

    public void setIsoleucine(Integer isoleucine) {
        this.isoleucine = isoleucine;
    }

    public Integer getLeucine() {
        return leucine;
    }

    public void setLeucine(Integer leucine) {
        this.leucine = leucine;
    }

    public Integer getLysine() {
        return lysine;
    }

    public void setLysine(Integer lysine) {
        this.lysine = lysine;
    }

    public Integer getMethionine() {
        return methionine;
    }

    public void setMethionine(Integer methionine) {
        this.methionine = methionine;
    }

    public Integer getCysteine() {
        return cysteine;
    }

    public void setCysteine(Integer cysteine) {
        this.cysteine = cysteine;
    }

    public Integer getPhenylalanine_plus_tyrosine() {
        return phenylalanine_plus_tyrosine;
    }

    public void setPhenylalanine_plus_tyrosine(Integer phenylalanine_plus_tyrosine) {
        this.phenylalanine_plus_tyrosine = phenylalanine_plus_tyrosine;
    }

    public Integer getThreonine() {
        return threonine;
    }

    public void setThreonine(Integer threonine) {
        this.threonine = threonine;
    }

    public Integer getTryptophan() {
        return tryptophan;
    }

    public void setTryptophan(Integer tryptophan) {
        this.tryptophan = tryptophan;
    }

    public Integer getValine() {
        return valine;
    }

    public void setValine(Integer valine) {
        this.valine = valine;
    }

    public Integer getHistidine() {
        return histidine;
    }

    public void setHistidine(Integer histidine) {
        this.histidine = histidine;
    }
}
