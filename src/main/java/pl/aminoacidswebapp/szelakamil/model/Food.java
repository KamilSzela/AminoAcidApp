package pl.aminoacidswebapp.szelakamil.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name cannot be empty!")
    private String name;
    private Float protein;
    private Float energy;
    private Float carbs;
    private Float fat;
    private Float digestability;

    private Integer isoleucine;
    private Integer leucine;
    private Integer lysine;
    private Integer methionine;
    private Integer cysteine;
    private Integer phenylalanine;
    private Integer tyrosine;
    private Integer threonine;
    private Integer tryptophan;
    private Integer valine;
    private Integer histidine;

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public Float getCarbs() {
        return carbs;
    }

    public void setCarbs(Float carbs) {
        this.carbs = carbs;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public Float getDigestability() {
        return digestability;
    }

    public void setDigestability(Float digestability) {
        this.digestability = digestability;
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

    public Integer getPhenylalanine() {
        return phenylalanine;
    }

    public void setPhenylalanine(Integer phenylalanine) {
        this.phenylalanine = phenylalanine;
    }

    public Integer getTyrosine() {
        return tyrosine;
    }

    public void setTyrosine(Integer tyrosine) {
        this.tyrosine = tyrosine;
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
