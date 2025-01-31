package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartReducedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.handler.ErrorHandler;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.CartMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.request.SellingApiRequest;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.response.ApiResponse;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.CartService;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Cart", description = "Este apartado contiene los endpoint para consultar y agregar productos a los carritos")
@RequestMapping("/api/carts")
public class CartController {


    @Autowired
    private CartService mCartService;
    @Autowired
    private ClientService mClientService;

    public CartController(CartService pCartService, ClientService mClientService) {

        this.mCartService = pCartService;
        this.mClientService = mClientService;
    }
    @Operation(summary = "Devuelve un listado de TODOS los tickets carritos)",
            description = "Devuelve un listado de TODOS los tickets carritos que de clientes que no hayan sido borrados (IsActiveFlag=true en Cliente)"
            ,responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operacion ejecutada correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<CartDTO>>> getAllCarts() {
        try {
            List<CartDTO> mCartDTOList = CartMapper.toDTO(mCartService.listAll());
            ApiResponse<List<CartDTO>> mApiResponse = new ApiResponse<>(true,"Listado de TODOS los carritos.",mCartDTOList,null);
            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @Operation(summary = "Devuelve la informacion del carrito pasado como parametro)",
            description = "Devuelve la informacion del carrito pasado como parametro solo si corresponde a un cliente que no haya sido eliminado (IsActiveFlag=true en Cliente)"
            ,responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operacion ejecutada correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @GetMapping("/{pCartId}")
    public ResponseEntity<ApiResponse<CartDTO>> getCartById(@PathVariable Long pCartId){
        try {
            CartDTO mCartDTO = CartMapper.toDTO(mCartService.getCart(pCartId));
            ApiResponse<CartDTO> mApiResponse = new ApiResponse<>(true,"Este es el carrito que buscabas.",mCartDTO,null);
            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        } catch (CustomException e) {
        return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                        List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }

    @Operation(summary = "Agrega o quitar un item del carrito pasado como parametro",
            description = "Agregar un item al carrito pasado como parametro, el item agregado sera el indicado en el body por la cantidad especificada.<br>" +
                    "Solo se agregara si el producto existe y esta activo (IsActiveFlag=true) al igual que el carrito.<br>" +
                    "Si se introduce una cantidad negativa de productos intentara reducir la cantidad de dicho producto del carrito, siempre y cuando exista en el detalle.<br>" +
                    "Si la cantidad resutlante es negativa, eliminara el item y si no existiera en el carrito informara el error."
            ,requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(  content = @Content( schema = @Schema(implementation = SellingApiRequest.ItemDetalleCarrito.class)  ) )
            ,responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operacion ejecutada correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @PutMapping("/{pCartId}")
    public ResponseEntity<ApiResponse<CartDTO>> addProductToCart(@PathVariable Long pCartId, @RequestBody Map<String, Object> pRequestBody) {

       try {
           if (pRequestBody.get("producto") == null) {
               throw new CustomException(ErrorType.INPUT_ERROR, ErrorType.INPUT_ERROR.getFormattedMessage("producto"));
           }
           if (pRequestBody.get("cantidad") == null) {
               throw new CustomException(ErrorType.INPUT_ERROR, ErrorType.INPUT_ERROR.getFormattedMessage("cantidad"));
           }

           Long pProductId = ((Number) pRequestBody.get("producto")).longValue();
           int pItemQuantity = (int) pRequestBody.get("cantidad");


           CartDTO mCartDTO = CartMapper.toDTO(mCartService.addProductToCart(pCartId,pProductId,pItemQuantity));
           ApiResponse<CartDTO> mApiResponse = new ApiResponse<>(true,"Se modifico la cantidad de items del carrito.",mCartDTO,null);
           return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
       } catch (CustomException e) {
           return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                   body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                           List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
       }
    }

    @Operation(summary = "Modifica el detalle del carrito",
            description = "Modificara los items del carrito agregando o quitando las unidades especificadas de cada producto, siempre y cuando estos esten activos al igual que el carrito.<br>" +
                    "Si se introduce una cantidad negativa de productos intentara reducir la cantidad de dicho producto del carrito, siempre y cuando exista en el detalle.<br>" +
                    "<H2>Esta operacion pisara cualquier item que el cliente tenga el carrito con lo seteado en el request.</H2>"
            ,requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(  content = @Content( schema = @Schema(implementation = SellingApiRequest.class)  ) )
            ,responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operacion ejecutada correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @PostMapping("/llenarCarrito")
    public ResponseEntity<ApiResponse<CartReducedDTO>> updateCart(@RequestBody Map<String, Object> pRequestBody) {
        try {
            CartReducedDTO mCartDTO = new CartReducedDTO();
            ApiResponse<CartReducedDTO> mApiResponse = new ApiResponse<>();

            Long mClientId = ((Number) pRequestBody.get("cliente")).longValue();
            Object mCartDetailList = pRequestBody.get("detalleCarrito");
            Client mClient = mClientService.getClient(mClientId);
            mCartService.clearCart(mClient.getmClientCart());


            if (!(mCartDetailList instanceof List<?>)) {
                throw new CustomException(ErrorType.CART_DETAIL_FORMAT_ERROR);
            } else {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> mCartDetailListRaw = (List<Map<String, Object>>) mCartDetailList;
                for (Map<String, Object> CartDetail : mCartDetailListRaw) {

                    Long mCartDetailProduct = ((Number) CartDetail.get("producto")).longValue();
                    int mCartDetailQuantity = (int) CartDetail.get("cantidad");
                    try {
                        mCartDTO = CartMapper.toReducedDTO(mCartService.addProductToCart(mClient.getmClientCart().getmCartId(), mCartDetailProduct, mCartDetailQuantity));
                    } catch (CustomException ce){
                        mApiResponse.addError(ce.getCustomExceptionText());
                    }
                }
            }
            if (mCartDTO.getmCartDetailList()==null || mCartDTO.getmCartDetailList().isEmpty()){
                mApiResponse.setSuccess(false);
                mApiResponse.setMessage("Parece que no se pudieron agregar productos al carrito.");
                mApiResponse.setData(CartMapper.toReducedDTO(mCartService.getCart(mClient.getmClientCart().getmCartId())));
            } else {
                mApiResponse.setSuccess(true);
                mApiResponse.setMessage("El carrito ha sido modificado.");
                mApiResponse.setData(mCartDTO);
            }

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);

        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),
                                    e.getErrorType().toString())));
        }
    }

}
