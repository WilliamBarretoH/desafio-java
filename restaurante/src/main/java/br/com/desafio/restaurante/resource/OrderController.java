package br.com.desafio.restaurante.resource;

import br.com.desafio.restaurante.domain.entity.Order;
import br.com.desafio.restaurante.resource.dto.OrderCreateDto;
import br.com.desafio.restaurante.resource.dto.OrderDto;
import br.com.desafio.restaurante.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Busca os pedidos do restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso na consulta",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))})
    })
    @GetMapping
    public ResponseEntity<List<OrderDto>> listAllOrders() {
        return ResponseEntity.ok().body(orderService.getOpenOrders());
    }

    @Operation(summary = "Cria um pedido para o restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class))})
    })
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCreateDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza os produtos de um pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("{id}")
    public ResponseEntity updateOrder(@RequestBody List<String> products, @PathVariable Long id) {
        orderService.updateOrder(products, id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }




}
