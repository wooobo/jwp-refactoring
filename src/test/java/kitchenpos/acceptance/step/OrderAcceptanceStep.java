package kitchenpos.acceptance.step;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import kitchenpos.dto.order.OrderRequest;
import kitchenpos.dto.order.OrderResponse;
import kitchenpos.dto.order.OrderStatusRequest;
import org.springframework.http.MediaType;

public class OrderAcceptanceStep {

    private static final String API_URL = "/api/orders";

    private OrderAcceptanceStep() {
    }

    public static ExtractableResponse<Response> 주문_등록_요청(OrderRequest order) {
        return RestAssured
            .given().log().all()
            .body(order)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post(API_URL)
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 주문_목록조회_요청() {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get(API_URL)
            .then().log().all()
            .extract();
    }


    public static ExtractableResponse<Response> 주문_상태변경_요청(Long orderId,
        OrderStatusRequest orderStatusRequest) {
        return RestAssured
            .given().log().all()
            .body(orderStatusRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().put(API_URL + "/" + orderId + "/order-status")
            .then().log().all()
            .extract();
    }

    public static Long 주문_등록_검증(ExtractableResponse<Response> response) {
        OrderResponse 등록된_주문 = response.as(OrderResponse.class);

        assertThat(등록된_주문.getId()).isNotNull();
        return 등록된_주문.getId();
    }

    public static List<OrderResponse> 주문_목록조회_검증(ExtractableResponse<Response> response,
        Long expected) {
        List<OrderResponse> 조회된_주문_목록 = response.as(new TypeRef<List<OrderResponse>>() {
        });
        assertThat(조회된_주문_목록).extracting("id").contains(expected);

        return 조회된_주문_목록;
    }


    public static void 주문_상태변경_검증(ExtractableResponse<Response> response, String expected) {
        OrderResponse 변경된_주문 = response.as(OrderResponse.class);
        assertThat(변경된_주문.getOrderStatus()).isEqualTo(expected);
    }

}
