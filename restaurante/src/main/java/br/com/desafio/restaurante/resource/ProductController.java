package br.com.desafio.restaurante.resource;

import br.com.desafio.restaurante.domain.enums.CategoryEnum;
import br.com.desafio.restaurante.resource.dto.ProductCreateDto;
import br.com.desafio.restaurante.resource.dto.ProductDto;
import br.com.desafio.restaurante.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Busca todos os produtos do restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso na consulta",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping
    public ResponseEntity<List<ProductDto>> listAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @Operation(summary = "Cria um produto para restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))})
    })
    @PostMapping("/{category}")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductCreateDto productDto,
                                                    @PathVariable("category") CategoryEnum category) {
        return new ResponseEntity<>(productService.createProduct(productDto, category.description), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um produto do restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Produto atualizado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductCreateDto productDto,
                                                    @PathVariable Long id) {
        return new ResponseEntity<>(productService.updateProduct(productDto, id), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Deleta um produto do restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Produto deletado com sucesso",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }





}
