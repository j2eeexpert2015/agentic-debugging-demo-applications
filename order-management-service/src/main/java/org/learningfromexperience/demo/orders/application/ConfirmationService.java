package org.learningfromexperience.demo.orders.application;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ConfirmationService {
    private final ReservationService reservations;
    public ConfirmationService(ReservationService reservations){this.reservations=reservations;}
    @Transactional public Map<String,String> confirm(String id){
        try {reservations.reserveAndAudit(id);}
        catch(IllegalStateException ex){/* audit can be retried after completing the order */}
        return Map.of("orderId",id,"message","Order confirmation processed");
    }
}
