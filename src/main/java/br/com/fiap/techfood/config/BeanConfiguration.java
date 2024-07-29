package br.com.fiap.techfood.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.techfood.core.dataprovider.PaymentDataProvider;
import br.com.fiap.techfood.core.usecase.impl.ClientUseCaseImpl;
import br.com.fiap.techfood.core.usecase.impl.OrderUseCaseImpl;
import br.com.fiap.techfood.core.usecase.impl.PaymentUseCaseImpl;
import br.com.fiap.techfood.core.usecase.impl.ProductUseCaseImpl;
import br.com.fiap.techfood.dataprovider.ClientDataProviderImpl;
import br.com.fiap.techfood.dataprovider.OrderDataProviderImpl;
import br.com.fiap.techfood.dataprovider.ProductDataProviderImpl;

@Configuration
public class BeanConfiguration {

	@Bean
	ClientUseCaseImpl clientUseCase(ClientDataProviderImpl clientDataProviderImpl) {
		return new ClientUseCaseImpl(clientDataProviderImpl);
	}

	@Bean
	OrderUseCaseImpl orderUserCase(
			OrderDataProviderImpl orderDataProviderImpl,
			ProductDataProviderImpl productDataProviderImpl,
			ClientDataProviderImpl clientDataProviderImpl
			) {
		return new OrderUseCaseImpl(
				orderDataProviderImpl, 
				productDataProviderImpl, 
				clientDataProviderImpl
				);
	}

	@Bean
	ProductUseCaseImpl productUseCase(ProductDataProviderImpl productDataProviderImpl) {
		return new ProductUseCaseImpl(productDataProviderImpl);
	}

	@Bean
	PaymentUseCaseImpl paymentUseCase(OrderDataProviderImpl orderDataProviderImpl, List<PaymentDataProvider> paymentDataProviderList) {
		return new PaymentUseCaseImpl(orderDataProviderImpl, paymentDataProviderList);
	}

}
