package com.assignment.recipe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(initialValue = 1, name = "id_seq", sequenceName = "id_sequence")
    private Long id;
    @CreationTimestamp()
    @DateTimeFormat(pattern="dd‐MM‐yyyy HH:mm")
    private LocalDateTime creationTimestamp;
    private boolean isVegetarian;
    private int numberOfPeople;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinTable(
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(
                    name = "recipe_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ingredient_id",
                    referencedColumnName = "id"
            )
    )
    private List<Ingredient> ingredients ;
    private String instruction;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="user_id", nullable=false, insertable=false, updatable=false)
    @JsonIgnore
    private User user;

    public Recipe() {}

    public Recipe(Long id, LocalDateTime creationTimestamp, boolean isVegetarian, int numberOfPeople, List<Ingredient> ingredients, String instruction) {
        this.id = id;
        this.creationTimestamp = creationTimestamp;
        this.isVegetarian = isVegetarian;
        this.numberOfPeople = numberOfPeople;
        this.ingredients = ingredients;
        this.instruction = instruction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
