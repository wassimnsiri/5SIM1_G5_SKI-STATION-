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

    // Static variables for subscription durations
    private static final int MONTHLY_DURATION = 1;
    private static final int SEMESTRIAL_DURATION = 6;

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
    void addMonthlySubscription() {
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.MONTHLY);
        subscription.setStartDate(LocalDate.now());

        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription savedSubscription = subscriptionService.addSubscription(subscription);
        assertEquals(subscription.getStartDate().plusMonths(MONTHLY_DURATION), savedSubscription.getEndDate());

        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    void addSemestrialSubscription() {
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.SEMESTRIEL);
        subscription.setStartDate(LocalDate.now());

        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription savedSubscription = subscriptionService.addSubscription(subscription);
        assertEquals(subscription.getStartDate().plusMonths(SEMESTRIAL_DURATION), savedSubscription.getEndDate());

        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    void addAnnualSubscription() {
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());

        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription savedSubscription = subscriptionService.addSubscription(subscription);
        assertEquals(subscription.getStartDate().plusYears(1), savedSubscription.getEndDate());

        verify(subscriptionRepository, times(1)).save(subscription);
    }

}