package com.zenve.chat.repository;

import com.zenve.chat.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {


    @Query(value = "select * from group_info gi left join user_group ug on gi.id = ug.group_id where ug.user_id = ?1", nativeQuery = true)
    List<Group> findAllByUserId(Long userId);
}
