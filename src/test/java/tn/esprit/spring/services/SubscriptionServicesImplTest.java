package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServicesImplTest {

    // Static variables for subscription durations
    private static final int MONTHLY_DURATION = 1;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionService;

    // Static monthly subscription for testing
    private final Subscription monthlySubscription = new Subscription(1L, LocalDate.now(), LocalDate.now().plusMonths(MONTHLY_DURATION), 100.0f, TypeSubscription.MONTHLY);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addMonthlySubscription() {
        // Mock repository save behavior
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(monthlySubscription);

        // Act
        Subscription savedSubscription = subscriptionService.addSubscription(monthlySubscription);

        // Assertions
        assertNotNull(savedSubscription);

        // Verify repository interaction
        verify(subscriptionRepository, times(1)).save(any(Subscription.class));
    }
}
