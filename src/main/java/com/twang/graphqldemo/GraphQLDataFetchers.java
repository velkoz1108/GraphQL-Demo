package com.twang.graphqldemo;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {
    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1",
                    "ownerId", "owner-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2",
                    "ownerId", "owner-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3",
                    "ownerId", "owner-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );

    private static List<Map<String, Object>> owners = Arrays.asList(
            ImmutableMap.of("owner_id", "owner-1",
                    "name", "Twang",
                    "age", 29),
            ImmutableMap.of("owner_id", "owner-2",
                    "name", "Eden",
                    "age", 30),
            ImmutableMap.of("owner_id", "owner-3",
                    "name", "Test",
                    "age", 33)
    );

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getOwnerDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String ownerId = book.get("ownerId");
            System.out.println("ownerId = " + ownerId);
            return owners
                    .stream()
                    .filter(owner -> owner.get("owner_id").equals(ownerId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getOwnerByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String ownerId = dataFetchingEnvironment.getArgument("owner_id");
            return owners
                    .stream()
                    .filter(owner -> owner.get("owner_id").equals(ownerId))
                    .findFirst()
                    .orElse(null);
        };

    }
}
