package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServicesImplMockTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    private Subscription subscription;

    @BeforeEach
    public void setUp() {
        // Initialize a subscription for testing
        subscription = new Subscription();
        subscription.setStartDate(LocalDate.of(2024, 1, 1));  // Set the start date
        subscription.setTypeSub(TypeSubscription.ANNUAL);  // Set type as Annual
    }

    @Test
    public void testAddSubscriptionWithMock() {
        // Mock the repository behavior: when saving any subscription, return the subscription object
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        // Call the method we're testing
        Subscription savedSubscription = subscriptionServices.addSubscription(subscription);

        // Assert the correct end date for an annual subscription
        assertEquals(subscription.getStartDate().plusYears(1), savedSubscription.getEndDate(), "The end date should be 1 year after the start date.");

        // Verify that the save method was called exactly once
        verify(subscriptionRepository, times(1)).save(subscription);
    }
}
