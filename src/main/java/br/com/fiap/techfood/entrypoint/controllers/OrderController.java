package br.com.fiap.techfood.entrypoint.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.techfood.core.domain.enums.OrderStatusEnum;
import br.com.fiap.techfood.core.usecase.OrderUseCase;
import br.com.fiap.techfood.entrypoint.dtos.OrderCreateDTO;
import br.com.fiap.techfood.entrypoint.dtos.OrderDto;
import br.com.fiap.techfood.entrypoint.mappers.OrderMapper;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
    private OrderUseCase orderUseCase;
    
	@Autowired
	private OrderMapper orderMapper;
	
	
    @PostMapping
    public ResponseEntity<OrderDto>  makeOrder(@RequestBody @Validated OrderCreateDTO orderCreateDto) {
        var cartDomain = orderMapper.toOrderRequestDomain(orderCreateDto);
        var orderDomain = orderUseCase.save(cartDomain, orderCreateDto.getClientCpf());
        var responseDto = orderMapper.toOrderDto(orderDomain);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/awaiting-payment")
    public ResponseEntity<List<OrderDto>> findAllAwaitingPayment() {
        var orderDomainList = orderUseCase.findAllByStatus(OrderStatusEnum.AWAITING_PAYMENT);
        var orderDtoList = orderDomainList.stream().map(orderDomain -> orderMapper.toOrderDto(orderDomain)).toList();
        return ResponseEntity.ok().body(orderDtoList);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<OrderDto>> findAllApprovedOrders() {
        var orderDomainList = orderUseCase.findAllByStatus(OrderStatusEnum.PAYMENT_APPROVED);
        var orderDtoList = orderDomainList.stream().map(orderDomain -> orderMapper.toOrderDto(orderDomain)).toList();
        return ResponseEntity.ok().body(orderDtoList);
    }

    @GetMapping("/prepared")
    public ResponseEntity<List<OrderDto>> findAllPreparedOrders() {
        var orderDomainList = orderUseCase.findAllByStatus(OrderStatusEnum.PREPARED);
        var orderDtoList = orderDomainList.stream().map(orderDomain -> orderMapper.toOrderDto(orderDomain)).toList();
        return ResponseEntity.ok().body(orderDtoList);
    }

    @GetMapping("/finished")
    public ResponseEntity<List<OrderDto>> findAllFinishedOrders() {
        var orderDomainList = orderUseCase.findAllByStatus(OrderStatusEnum.FINISHED);
        var orderDtoList = orderDomainList.stream().map(orderDomain -> orderMapper.toOrderDto(orderDomain)).toList();
        return ResponseEntity.ok().body(orderDtoList);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
    	orderUseCase.delete(orderId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}/prepare")
    public ResponseEntity<Void> prepareOrder(@PathVariable UUID orderId) {
    	orderUseCase.prepareOrder(orderId);
    	return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}/finish")
    ResponseEntity<Void> finishOrder(@PathVariable UUID orderId) {
    	orderUseCase.finishOrder(orderId);
    	return ResponseEntity.noContent().build();
    }
    
}
