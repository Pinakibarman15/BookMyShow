package com.restoreBookshow.demo.models;

import com.restoreBookshow.demo.enums.Languange;
import com.restoreBookshow.demo.enums.MovieFeature;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

//import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doctor_info",schema = "devdata")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long userid;
    private String name;
    private Double rating;
    @ManyToOne
    private Category category;

    @ElementCollection
    @Enumerated
    private  List<Languange> lang=new ArrayList<>();

    @ElementCollection
    @Enumerated
    private List<MovieFeature> features=new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<Shows> shows=new ArrayList<>();

}
