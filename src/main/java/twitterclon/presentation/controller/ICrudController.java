package twitterclon.presentation.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICrudController<T, N, P> {

    ResponseEntity<List<T>> findAll();
    ResponseEntity<T> findById(N bookId);
    ResponseEntity<Void> deleteById(N bookId);
    ResponseEntity<T> create(P itemRequest);
    ResponseEntity<T> update(N id, P itemRequest);
}
