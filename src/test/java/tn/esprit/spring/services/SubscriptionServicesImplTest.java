package tn.esprit.spring.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubscriptionServicesImplTest {

    @Mock
    ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    SubscriptionServicesImpl subscriptionServices;

    Subscription subscription = new Subscription();



    @Test
    void addSubscription() {
        subscription.setNumSub(1L);
        subscription.setStartDate(java.time.LocalDate.now());
        subscription.setTypeSub(tn.esprit.spring.entities.TypeSubscription.ANNUAL);
        subscription.setPrice(100F);
        subscription.setEndDate(java.time.LocalDate.now().plusYears(1));
        Mockito.when(subscriptionRepository.save(subscription)).thenReturn(subscription);
        assertEquals(subscription, subscriptionServices.addSubscription(subscription));

    }

    @Test
    void updateSubscription() {
        subscription.setNumSub(1L);
        subscription.setStartDate(java.time.LocalDate.now());
        subscription.setTypeSub(tn.esprit.spring.entities.TypeSubscription.ANNUAL);
        subscription.setPrice(100F);
        subscription.setEndDate(java.time.LocalDate.now().plusYears(1));

        // Mocking the save method
        Mockito.when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        // Update subscription and assert
        Subscription updatedSubscription = subscriptionServices.updateSubscription(subscription);
        assertEquals(subscription, updatedSubscription);

        // Verify that save was called
        Mockito.verify(subscriptionRepository).save(subscription);
    }

    @Test
    void getSubscriptionByType() {
        Subscription subscription1 = new Subscription();
        subscription1.setNumSub(1L);
        subscription1.setStartDate(java.time.LocalDate.now());
        subscription1.setTypeSub(tn.esprit.spring.entities.TypeSubscription.ANNUAL);

        Subscription subscription2 = new Subscription();
        subscription2.setNumSub(2L);
        subscription2.setStartDate(java.time.LocalDate.now().plusMonths(1));
        subscription2.setTypeSub(tn.esprit.spring.entities.TypeSubscription.ANNUAL);

        // Mocking the repository method to return a set of subscriptions
        Mockito.when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(tn.esprit.spring.entities.TypeSubscription.ANNUAL))
                .thenReturn(java.util.Set.of(subscription1, subscription2));

        // Get subscriptions by type and assert
        java.util.Set<Subscription> subscriptions = subscriptionServices.getSubscriptionByType(tn.esprit.spring.entities.TypeSubscription.ANNUAL);
        assertEquals(2, subscriptions.size());

        // Verify that the correct repository method was called
        Mockito.verify(subscriptionRepository).findByTypeSubOrderByStartDateAsc(tn.esprit.spring.entities.TypeSubscription.ANNUAL);
    }

    @Test
    void retrieveSubscriptionById() {
        subscription.setNumSub(1L);
        subscription.setStartDate(java.time.LocalDate.now());
        subscription.setTypeSub(tn.esprit.spring.entities.TypeSubscription.ANNUAL);
        subscription.setPrice(100F);
        subscription.setEndDate(java.time.LocalDate.now().plusYears(1));

        // Mocking the findById method
        Mockito.when(subscriptionRepository.findById(1L)).thenReturn(java.util.Optional.of(subscription));

        // Retrieve subscription by ID and assert
        Subscription retrievedSubscription = subscriptionServices.retrieveSubscriptionById(1L);
        assertEquals(subscription, retrievedSubscription);

        // Verify that findById was called once
        Mockito.verify(subscriptionRepository).findById(1L);
    }

    @Test
    void retrieveSubscriptionsByDates() {
        Subscription subscription1 = new Subscription();
        subscription1.setNumSub(1L);
        subscription1.setStartDate(java.time.LocalDate.now());
        subscription1.setTypeSub(tn.esprit.spring.entities.TypeSubscription.ANNUAL);
        subscription1.setEndDate(java.time.LocalDate.now().plusYears(1));

        Subscription subscription2 = new Subscription();
        subscription2.setNumSub(2L);
        subscription2.setStartDate(java.time.LocalDate.now().plusMonths(1));
        subscription2.setTypeSub(tn.esprit.spring.entities.TypeSubscription.ANNUAL);
        subscription2.setEndDate(java.time.LocalDate.now().plusYears(1).plusMonths(1));

        // Mocking the repository method to return subscriptions between specific dates
        Mockito.when(subscriptionRepository.getSubscriptionsByStartDateBetween(
                        java.time.LocalDate.now().minusDays(1), java.time.LocalDate.now().plusMonths(2)))
                .thenReturn(java.util.List.of(subscription1, subscription2));

        // Get subscriptions by dates and assert
        java.util.List<Subscription> subscriptions = subscriptionServices.retrieveSubscriptionsByDates(
                java.time.LocalDate.now().minusDays(1), java.time.LocalDate.now().plusMonths(2));

        assertEquals(2, subscriptions.size());

        // Verify the repository method was called with the correct parameters
        Mockito.verify(subscriptionRepository).getSubscriptionsByStartDateBetween(
                java.time.LocalDate.now().minusDays(1), java.time.LocalDate.now().plusMonths(2));
    }



}