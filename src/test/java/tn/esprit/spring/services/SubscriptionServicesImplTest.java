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
}