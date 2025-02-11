package tech_cx.adi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech_cx.adi.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

}
