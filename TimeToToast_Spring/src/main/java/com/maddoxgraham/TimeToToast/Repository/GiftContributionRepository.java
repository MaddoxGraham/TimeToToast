package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftContributionRepository extends JpaRepository<GiftContribution, Long> {
    void deleteGiftContributionByIdGiftContribution(Long idGiftContribution);
    Optional<GiftContribution> findGiftContributionByIdGiftContribution(Long idGiftContribution);
}
