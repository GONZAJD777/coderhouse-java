package com.coderhouse.ProyectoFinal_PrimeraEntrega.controller;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartReducedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product.ProductDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket.TicketExtendedDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.exception.CustomException;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.handler.ErrorHandler;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.CartMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.mapper.ProductMapper;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Client;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.request.SellingApiRequest;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.response.ApiResponse;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.CartService;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.ClientService;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Selling", description = "Este apartado contiene el endpoint para la VENTA de productos")

@RequestMapping("/api/ventas")
public class SellingController {

    @Autowired
    TicketService mTicketService;
    @Autowired
    CartService mCartService;
    @Autowired
    ClientService mClientService;

    @Operation(summary = "Endpoint de Venta de productos"
            ,description = "Este endpoint toma el request con el formato especificado y sirviendose de la logica y validaciones de CartService y TicketServive, agrega los productos al carrito y luego genera el comprobante para la venta, realizando validaciones sobre Cliente, Producto, reduciendo Stock, registrando errores que puedan surgir e informando en la respuesta el resultado.<br>" +
                    "Como se menciono hace uso de servicios los cuales incluyes validaciones encapsuladas de cada entidad interviniente en el proceso, por lo que ante un error en la logica de negocio se registrara el error en al respuesta."
            ,requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(  content = @Content( schema = @Schema(implementation = SellingApiRequest.class)  ) )
            ,responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Pedido creado exitosamente"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Error en la request",content = @Content(schema = @Schema(implementation = ApiResponse.class)) ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error en el servicio",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @PostMapping
    public ResponseEntity<ApiResponse<TicketExtendedDTO>> createSale(@RequestBody Map<String, Object> pRequestBody) {
        try {

            ApiResponse<TicketExtendedDTO> mApiResponse = new ApiResponse<>();

            Long mClientId = ((Number) pRequestBody.get("cliente")).longValue();
            Object mCartDetailList = pRequestBody.get("detalleCarrito");
            Client mClient = mClientService.getClient(mClientId);

            if (!(mCartDetailList instanceof List<?>)) {
                throw new CustomException(ErrorType.CART_DETAIL_FORMAT_ERROR);
            } else {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> mCartDetailListRaw = (List<Map<String, Object>>) mCartDetailList;
                for (Map<String, Object> CartDetail : mCartDetailListRaw) {

                    Long mCartDetailProduct = ((Number) CartDetail.get("producto")).longValue();
                    int mCartDetailQuantity = (int) CartDetail.get("cantidad");
                    try {
                        CartMapper.toReducedDTO(mCartService.addProductToCart(mClient.getmClientCart().getmCartId(), mCartDetailProduct, mCartDetailQuantity));
                    } catch (CustomException ce){
                        mApiResponse.addError(ce.getCustomExceptionText());
                    }
                }
            }
            TicketExtendedDTO mTicketExtendedDTO = new TicketExtendedDTO();
            try {
                mTicketExtendedDTO = mTicketService.createTicket(mClientId);
            } catch (CustomException ce){
                mApiResponse.addError(ce.getCustomExceptionText());
            }
            mApiResponse.setSuccess(true);
            mApiResponse.setMessage("El Ticket se genero correctamente.");
            mApiResponse.setData(mTicketExtendedDTO);

            if(mTicketExtendedDTO.getmTicket()==null){
                mApiResponse.setSuccess(false);
                mApiResponse.setMessage("Ticket couldn't be generated, none of the items in the client's cart fulfill the requirements.");
            }
            if (mTicketExtendedDTO.getNotEnoughStockProducts() !=null){
                if (!mTicketExtendedDTO.getNotEnoughStockProducts().isEmpty()) {
                    mApiResponse.addError("Some items could not be sold because there is not enough stock. See notEnoughStockProducts");
                }}

            return ResponseEntity.status(HttpStatus.OK).body(mApiResponse);
        } catch (CustomException e) {
            return ResponseEntity.status(ErrorHandler.getStatus(e.getErrorType())).
                    body(new ApiResponse<>(false,e.getCustomExceptionText(),null,
                            List.of(ErrorHandler.getStatus(e.getErrorType()).toString(),e.getErrorType().toString())));
        }
    }
}