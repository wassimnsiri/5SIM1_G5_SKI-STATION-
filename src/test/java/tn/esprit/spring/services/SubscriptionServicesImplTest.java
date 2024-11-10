package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServicesImplTest {


    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    SubscriptionServicesImpl subscriptionServices;

    private Subscription subscription;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        subscription.setPrice(100.0f);
        subscription.setTypeSub(TypeSubscription.valueOf("ANNUAL"));
    }

    @Test
    void addSubscription() {
        when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(subscription);
        Subscription subscription1 = subscriptionServices.addSubscription(subscription);
        assertThat(subscription1).isNotNull();
        verify(subscriptionRepository).save(Mockito.any(Subscription.class));
    }


}
