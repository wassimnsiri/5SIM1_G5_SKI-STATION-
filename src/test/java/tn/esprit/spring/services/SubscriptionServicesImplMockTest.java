package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServicesImplMockTest {

    @MockBean
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    private Subscription subscription;

    @BeforeEach
    public void setUp() {
        subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setStartDate(LocalDate.now());
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setEndDate(LocalDate.now().plusYears(1));
    }

    @Test
    public void testAddSubscription() {
        // Mock the behavior of the save method in ISubscriptionRepository
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        // Call the method we're testing
        Subscription result = subscriptionServices.addSubscription(subscription);

        // Assert that the returned subscription is the same as the mocked one
        assertEquals(subscription, result);
    }
}

