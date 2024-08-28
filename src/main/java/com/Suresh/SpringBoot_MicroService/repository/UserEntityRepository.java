package com.Suresh.SpringBoot_MicroService.repository;

import com.Suresh.SpringBoot_MicroService.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long>
{
    List<UserEntity> findByUserName(String username);
}
