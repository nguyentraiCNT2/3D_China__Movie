package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.Orders_Entity;
import org.project4.backend.entity.User_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Orders_Repository extends JpaRepository<Orders_Entity, Long> {
    List<Orders_Entity> findByUserOrderByDateDesc(User_Entity userid, Pageable pageable);
    List<Orders_Entity>  findByUserAndMovie(User_Entity userid, Movie_Entity movieid);
    List<Orders_Entity> findByMovie(Movie_Entity movieid, Pageable pageable);
    @Query("SELECT MONTH(o.date) AS month, YEAR(o.date) AS year, SUM(o.point) AS totalPoints " +
            "FROM Orders_Entity o " +
            "WHERE o.status = 'COMPLETED' AND YEAR(o.date) = YEAR(CURRENT_DATE) " +
            "GROUP BY YEAR(o.date), MONTH(o.date) " +
            "ORDER BY YEAR(o.date) ASC, MONTH(o.date) ASC")
    List<Object[]> getMonthlyRevenue();

}
