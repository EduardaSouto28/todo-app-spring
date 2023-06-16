package com.example.todoapp.repository;
import com.example.todoapp.model.User;
import com.example.todoapp.model.Lists;
import com.example.todoapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.name = :name AND u.password = :password")
    User findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Query("SELECT l FROM User u JOIN u.lists l WHERE l.id = :listId AND u.id = :userId")
    Lists findListByIdAndUserId(@Param("listId") Long listId, @Param("userId") Long userId);

    // @Query("SELECT i FROM User u JOIN u.lists l JOIN l.items i WHERE i.id = :itemId AND l.id = :listId AND u.id = :userId")
    // Item findItemByIdAndListIdAndUserId(@Param("itemId") Long itemId, @Param("listId") Long listId, @Param("userId") Long userId);
}
