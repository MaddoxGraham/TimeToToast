package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.GiftContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GiftContributionRepository extends JpaRepository<GiftContribution, Long> {
    void deleteGiftContributionByIdGiftContribution(Long idGiftContribution);
    Optional<GiftContribution> findGiftContributionByIdGiftContribution(Long idGiftContribution);
}
