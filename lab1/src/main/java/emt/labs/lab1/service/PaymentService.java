package emt.labs.lab1.service;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import emt.labs.lab1.dto.ChargeRequest;

public interface PaymentService {
    Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException;
}
