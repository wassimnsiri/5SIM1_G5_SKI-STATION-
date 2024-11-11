package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServicesImplMockTest {

  // test get all
    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @BeforeEach
    public void setUp() {
        Subscription subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setStartDate(LocalDate.now());
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setEndDate(LocalDate.now().plusYears(1));
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);
        when(subscriptionRepository.findById(1L)).thenReturn(java.util.Optional.of(subscription));
    }

    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setStartDate(LocalDate.now());
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setEndDate(LocalDate.now().plusYears(1));
        Subscription savedSubscription = subscriptionServices.addSubscription(subscription);
        assertEquals(subscription, savedSubscription);
    }


    //test get all
    @Test
    public void testGetSubscriptionByType() {
        subscriptionServices.getSubscriptionByType(TypeSubscription.ANNUAL);
        verify(subscriptionRepository, times(1)).findByTypeSubOrderByStartDateAsc(TypeSubscription.ANNUAL);
    }

    @Test
    public void testRetrieveSubscriptionsByDates() {
        subscriptionServices.retrieveSubscriptionsByDates(LocalDate.now(), LocalDate.now().plusYears(1));
        verify(subscriptionRepository, times(1)).getSubscriptionsByStartDateBetween(LocalDate.now(), LocalDate.now().plusYears(1));
    }




}

