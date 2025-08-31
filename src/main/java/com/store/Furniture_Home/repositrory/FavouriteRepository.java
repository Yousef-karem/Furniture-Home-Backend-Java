package com.store.Furniture_Home.repositrory;

import com.store.Furniture_Home.entites.Favourite;
import com.store.Furniture_Home.entites.User;
import com.store.Furniture_Home.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    List<Favourite> findByUser(User user);
    Optional<Favourite> findByUserAndProduct(User user, Product product);
    boolean existsByUserAndProduct(User user, Product product);
}
