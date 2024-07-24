package br.com.fiap.techfood.dataprovider;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentStatus;

import br.com.fiap.techfood.core.dataprovider.PaymentDataProvider;
import br.com.fiap.techfood.core.domain.OrderDomain;
import br.com.fiap.techfood.core.domain.PaymentDomain;
import br.com.fiap.techfood.core.domain.enums.PaymentProviderEnum;
import br.com.fiap.techfood.core.domain.exceptions.PaymentCreateFailException;
import br.com.fiap.techfood.dataprovider.paymentclient.mapper.PaymentMercadoPagoMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class MercadoPagoPaymentDataProviderImpl implements PaymentDataProvider {

	@Value("dataprovider.payment.mercado-pago.access-token")
	private String accessToken;

	@Value("dataprovider.payment.mercado-pago.default-payer-email")
	private String defaultPayerEmail;

	@Autowired
	private PaymentMercadoPagoMapper paymentMercadoPagoMapper;

	@Override
	public PaymentProviderEnum getProviderCode() {
		return PaymentProviderEnum.MERCADO_PAGO;
	}

	@Override
	public PaymentDomain createPaymentOrder(OrderDomain orderDomain) throws PaymentCreateFailException {
		MercadoPagoConfig.setAccessToken(accessToken);
		MercadoPagoConfig.setLoggingLevel(Level.FINEST);

		PaymentClient paymentClient = new PaymentClient();

		Map<String, String> customHeaders = new HashMap<String, String>();
		customHeaders.put("x-idempotency-key", orderDomain.getId().toString());

		MPRequestOptions.builder()
		//.accessToken("custom_access_token")
		.connectionRequestTimeout(2000)
		.connectionTimeout(2000)
		.socketTimeout(2000)
		.customHeaders(customHeaders)
		.build();

		String payerEmail = null;

		if(orderDomain.getClient() != null && orderDomain.getClient().getEmail() != null) {
			payerEmail = orderDomain.getClient().getEmail();
		} else {
			payerEmail = this.defaultPayerEmail;
		}

		String notificationUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/mercado-pago/webhook";

		PaymentCreateRequest createRequest =
				PaymentCreateRequest.builder()
				.transactionAmount(new BigDecimal(1))
				.externalReference(orderDomain.getId().toString())
				.description("fiap-techfood")
				.installments(1)
				.paymentMethodId("pix")
				.notificationUrl(notificationUrl)
				.payer(PaymentPayerRequest.builder().email(payerEmail).build())
				.build();

		try {
			Payment paymentResponse = paymentClient.create(createRequest);
			if(PaymentStatus.PENDING.equals(paymentResponse.getStatus())) {
				PaymentDomain paymentDomain = this.paymentMercadoPagoMapper.toPaymentDomain(paymentResponse);
				return paymentDomain;
			} else {
				throw new PaymentCreateFailException("Falha para criar pagamento");
			}

		} catch (MPException | MPApiException e) {
			log.error("Error generate payment {}", e.getMessage());
			log.error(e.getMessage());
			throw new PaymentCreateFailException("Falha para criar pagamento");
		}
	}

	@Override
	public PaymentDomain checkPaymentStatus(String externalPaymentId) throws PaymentCreateFailException {
		MercadoPagoConfig.setAccessToken(accessToken);
		MercadoPagoConfig.setLoggingLevel(Level.FINEST);

		PaymentClient paymentClient = new PaymentClient();

		MPRequestOptions.builder()
		.connectionRequestTimeout(2000)
		.connectionTimeout(2000)
		.socketTimeout(2000)
		.build();


		try {
			Long paymentMpId = Long.parseLong(externalPaymentId);
			Payment paymentResponse = paymentClient.get(paymentMpId);

			PaymentDomain paymentDomain = this.paymentMercadoPagoMapper.toPaymentDomain(paymentResponse);
			return paymentDomain;
		} catch (NumberFormatException e) {
			log.error("Error to get payment: Invalid id given. Id: " + externalPaymentId);
			throw new PaymentCreateFailException("Error to get payment. Invalid Long Id: " + externalPaymentId);
		} catch (MPException | MPApiException e) {
			log.error("Error generate payment {}", e.getMessage());
			log.error(e.getMessage());
			throw new PaymentCreateFailException("Error to get payment.");
		}
	}

}
