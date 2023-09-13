package com.scott.stock.stockdataetltool.job;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scott.stock.stockdataetltool.StockDataEtlToolApplicationTests;
import com.scott.stock.stockdataetltool.model.Stock;
import com.scott.stock.stockdataetltool.repository.StockRepository;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JobTest extends StockDataEtlToolApplicationTests {
    @Autowired
    private WebClient webClient;
    @Autowired
    private StockRepository stockRepository;

    @Test
    public void webClientExceptionTest() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRlIjoiMjAyMy0wOS0xMyAxMDoxMzoyNCIsInVzZXJfaWQiOiJzY290dDAxMjciLCJpcCI6IjYwLjI1MC4xODguMjI0In0.RawLrF4xKO1Nv-XjezY0fESJTnrF4n5-EfSOmzF0Iuk";

        String API_ENDPOINT = "https://api.finmindtrade.com/api/v4/data";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
            .queryParam("dataset", "TaiwanStockInfo")
            .queryParam("token", token);
        URI uri = builder.build().encode().toUri();

        Mono<String> result = webClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class);

        result.flatMapMany((body) -> {
            Set<Stock> stockSet = new HashSet<>();

            JsonObject bodyJsonObject = JsonParser.parseString(body).getAsJsonObject();
            JsonArray dataList = bodyJsonObject.getAsJsonArray("data");
            dataList.iterator().forEachRemaining((data) -> {
                JsonObject dataJsonObject = data.getAsJsonObject();
                String industryCategory = dataJsonObject.get("industry_category").getAsString();
                String stockId = dataJsonObject.get("stock_id").getAsString();
                String stockName = dataJsonObject.get("stock_name").getAsString();
                String type = dataJsonObject.get("type").getAsString();

                Stock stock = new Stock(stockId, stockName, type, industryCategory);
                stockSet.add(stock);
            });

            return Flux.fromIterable(stockSet);
        }).map((stock) -> {
            stockRepository.save(stock);
            return Flux.empty();
        }).collectList().block();

        List<Stock> stockList = stockRepository.findAll();
        stockList.forEach(System.out::println);
    }

    @Test
    public void test() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRlIjoiMjAyMy0wOS0xMyAxMDoxMzoyNCIsInVzZXJfaWQiOiJzY290dDAxMjciLCJpcCI6IjYwLjI1MC4xODguMjI0In0.RawLrF4xKO1Nv-XjezY0fESJTnrF4n5-EfSOmzF0Iuk";

        WebClient client = WebClient.builder()
            .codecs(config -> config.defaultCodecs().maxInMemorySize(4 * 1024 * 1024))
            .filter((request, next) -> {
                URI uri = request.url();
                System.out.println("uri: " + uri);
                return next.exchange(request);
            })
            .build();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.finmindtrade.com/")
            .path("api/v4/data")
            .queryParam("dataset", "TaiwanStockInfo")
            .queryParam("token", token);
        URI uri = builder.build().encode().toUri();

        Mono<String> result = client.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, resp -> {
                System.out.println("code = " + resp.statusCode().value());
                return resp.bodyToMono(String.class).map(Exception::new);
            })
            .bodyToMono(String.class);

        String r = result.block();

        JsonElement jsonElement = JsonParser.parseString(r);
        JsonArray dataList = jsonElement.getAsJsonObject().getAsJsonArray("data");
        dataList.iterator().forEachRemaining((data) -> {
            JsonObject dataJsonObject = data.getAsJsonObject();
            String industryCategory = dataJsonObject.get("industry_category").getAsString();
            String stockId = dataJsonObject.get("stock_id").getAsString();
            String stockName = dataJsonObject.get("stock_name").getAsString();
            String type = dataJsonObject.get("type").getAsString();

            System.out.println(stockId);
        });
    }

}
