package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SubscriptionServicesImplTest {


    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSubscription() {
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.MONTHLY);
        subscription.setStartDate(LocalDate.now());

        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription savedSubscription = subscriptionService.addSubscription(subscription);
        assertEquals(subscription.getStartDate().plusMonths(1), savedSubscription.getEndDate());

        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    void updateSubscription() {
    }

    @Test
    void retrieveSubscriptionById() {
    }

    @Test
    void getSubscriptionByType() {
    }

    @Test
    void retrieveSubscriptionsByDates() {
    }

    @Test
    void retrieveSubscriptions() {
    }

    @Test
    void showMonthlyRecurringRevenue() {
    }
}