package ru.project.smartbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.smartbudget.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUserId(Long userId);
}
