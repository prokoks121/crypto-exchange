package com.fivedaysincloud.cryptoexchange.repository;

import com.fivedaysincloud.cryptoexchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
