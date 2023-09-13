package com.scott.stock.stockdataetltool.job;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scott.stock.stockdataetltool.model.Stock;
import com.scott.stock.stockdataetltool.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@RequiredArgsConstructor
public class CollectStockListTask implements Job {
    private static final String TOKEN = "token";
    private static final String DATASET = "dataset";
    private static final String API_ENDPOINT = "https://api.finmindtrade.com/api/v4/data";

    private final WebClient webClient;

    private final StockRepository stockRepository;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String token = jobDataMap.getString(TOKEN);
        String dataset = jobDataMap.getString(DATASET);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
            .queryParam("dataset", dataset)
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
        }).subscribe();

    }

}