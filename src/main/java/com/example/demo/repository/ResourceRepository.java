package com.example.demo.repository;

import com.example.demo.model.Resource;
import com.example.demo.model.Resource.ResourceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID> {

    List<Resource> findByOwnerUsername(String ownerUsername);

    Page<Resource> findByOwnerUsername(String ownerUsername, Pageable pageable);

    List<Resource> findByStatus(ResourceStatus status);

    Page<Resource> findByStatus(ResourceStatus status, Pageable pageable);

    @Query("SELECT r FROM Resource r WHERE r.ownerUsername = :username AND r.status = :status")
    List<Resource> findByOwnerAndStatus(@Param("username") String username, 
                                        @Param("status") ResourceStatus status);

    @Query("SELECT r FROM Resource r WHERE r.value >= :minValue AND r.value <= :maxValue")
    List<Resource> findByValueRange(@Param("minValue") BigDecimal minValue, 
                                    @Param("maxValue") BigDecimal maxValue);

    @Query("SELECT r FROM Resource r WHERE r.acquisitionDate >= :startDate AND r.acquisitionDate <= :endDate")
    List<Resource> findByAcquisitionDateBetween(@Param("startDate") LocalDate startDate, 
                                                 @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Resource r JOIN r.tags t WHERE t = :tag")
    List<Resource> findByTag(@Param("tag") String tag);

    @Query("SELECT r FROM Resource r WHERE r.ownerUsername = :username AND r.name LIKE %:namePart%")
    List<Resource> findByOwnerAndNameContaining(@Param("username") String username, 
                                                 @Param("namePart") String namePart);

    @Query("SELECT COUNT(r) FROM Resource r WHERE r.ownerUsername = :username")
    long countByOwner(@Param("username") String username);

    @Query("SELECT COUNT(r) FROM Resource r WHERE r.status = :status")
    long countByStatus(@Param("status") ResourceStatus status);

    @Query("SELECT SUM(r.value) FROM Resource r WHERE r.ownerUsername = :username")
    BigDecimal sumValueByOwner(@Param("username") String username);

    @Query("SELECT DISTINCT r.ownerUsername FROM Resource r")
    List<String> findDistinctOwners();

    @Query("SELECT r FROM Resource r WHERE r.status IN ('ACTIVE', 'AVAILABLE') ORDER BY r.createdAt DESC")
    List<Resource> findAllActive(Pageable pageable);

    @Query("SELECT r FROM Resource r WHERE r.ownerUsername = :owner AND r.status = :status ORDER BY r.value DESC")
    Page<Resource> findByOwnerAndStatusOrderedByValue(@Param("owner") String owner, 
                                                       @Param("status") ResourceStatus status,
                                                       Pageable pageable);

    boolean existsByNameAndOwnerUsername(String name, String ownerUsername);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Resource r WHERE r.name = :name AND r.ownerUsername = :owner")
    boolean checkExistsByNameAndOwner(@Param("name") String name, @Param("owner") String ownerUsername);

    List<Resource> findByNameContainingIgnoreCase(String name);

    List<Resource> findByValueGreaterThan(BigDecimal value);
}