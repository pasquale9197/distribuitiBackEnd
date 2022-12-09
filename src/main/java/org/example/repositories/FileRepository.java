package org.example.repositories;

import org.example.entity.File;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository<T extends File> extends JpaRepository<T, Long>
{
    boolean existsByTypefile(String type);
    boolean existsByTitolo(String titolo);
    List<File> findByTitolo(String titolo);
    File findByTypefile(String type);

    File findById(long id);

    List<User> findByListaUser();

}
