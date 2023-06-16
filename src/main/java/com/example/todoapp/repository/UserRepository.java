package com.example.todoapp.repository;
import com.example.todoapp.model.User;
import com.example.todoapp.model.Lists;
import com.example.todoapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.name = ?1 AND u.password = ?2")
    User findByNameAndPassword(String name, String password);

    @Query("SELECT l FROM User u JOIN u.lists l WHERE l.id = ?1 AND u.id = ?2")
    Lists findListByIdAndUserId(Long listId, Long userId);

//    @Query("SELECT i FROM User u JOIN u.lists l JOIN l.items i WHERE i.id = :itemId AND l.id = :listId AND u.id = :userId")
//    Item findItemByIdAndListIdAndUserId(Long itemId, Long listId, Long userId);

}
