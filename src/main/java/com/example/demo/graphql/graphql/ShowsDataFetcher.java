package com.example.demo.graphql.graphql;

import com.example.demo.graphql.graphql.types.Review;
import com.example.demo.graphql.graphql.types.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.stream.Collectors;

//После запуска сервиса мы можем перейти по адресу http://localhost:8080/graphiql
//и увидим graphql консоль
//Пример запроса
/*
{
  shows {
    title,
    reviews {
      starScore
    }
  }
}

С фильтром
{
  shows(titleFilter: "title_1") {
    title,
    reviews {
      starScore
    }
  }
}

 */

/**
 * Также есть такой подход, когда наружу для FE торчит только Federation Gateway, который знает инфу
 * о схемах всех микросервисов и предоставляет фронту единую точку входа и сбора респонсов
 * Даже филды одного объекта могут собираться из разных микросевисов, но фронт не будет знать об этом ничего
 */
@DgsComponent
public class ShowsDataFetcher {

    private static final List<Show> SHOWS = List.of(
            new Show("title_1", List.of(new Review(5))),
            new Show("title_2", List.of(new Review(4)))
    );

    @DgsData(parentType = "Query", field = "shows")
    //@DgsQuery - если parentType = "Query", а имя метода не отличается от field
    public List<Show> getShows(@InputArgument String titleFilter) {
        if (titleFilter == null) {
            return SHOWS;
        }
        return SHOWS.stream()
                .filter(show -> show.getTitle().equals(titleFilter))
                .collect(Collectors.toList());
    }
}
