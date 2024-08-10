package com.restoreBookshow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class City  extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Theatre> theaters=new ArrayList<>();
}
