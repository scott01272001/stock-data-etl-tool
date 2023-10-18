package com.scott.stock.stockdataetltool.domain.collectdata;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scott.stock.stockdataetltool.domain.analysis.entity.IndustryCategory;
import com.scott.stock.stockdataetltool.domain.analysis.entity.Stock;
import com.scott.stock.stockdataetltool.domain.collectdata.entity.CrawlerToken;
import com.scott.stock.stockdataetltool.infrastructure.databse.repository.CrawlerTokenRepository;
import com.scott.stock.stockdataetltool.infrastructure.databse.repository.IndustryCategoryRepository;
import com.scott.stock.stockdataetltool.infrastructure.databse.repository.StockRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
public class CollectStockInfo implements Job {

    private static final String DATASET = "TaiwanStockInfo";
    private static final String API_ENDPOINT = "https://api.finmindtrade.com/api/v4/data";

    private final WebClient webClient;

    private final StockRepository stockRepository;

    private final CrawlerTokenRepository crawlerTokenRepository;

    private final IndustryCategoryRepository industryCategoryRepository;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<CrawlerToken> tokens = crawlerTokenRepository.findAll();
        if (CollectionUtils.isEmpty(tokens)) {
            log.error("Can not find any available crawler token from database.");
            throw new JobExecutionException();
        }

        CrawlerToken token = tokens.get(0);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
            .queryParam("dataset", DATASET)
            .queryParam("token", token.getContent());
        URI uri = builder.build().encode().toUri();

        Mono<String> result = webClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class);

        result.subscribe((body) -> {
            JsonObject bodyJsonObject = JsonParser.parseString(body).getAsJsonObject();
            JsonArray dataList = bodyJsonObject.getAsJsonArray("data");
            dataList.iterator().forEachRemaining((data) -> {
                JsonObject dataJsonObject = data.getAsJsonObject();
                String industryCategory = dataJsonObject.get("industry_category").getAsString();
                String stockId = dataJsonObject.get("stock_id").getAsString();
                String stockName = dataJsonObject.get("stock_name").getAsString();
                String type = dataJsonObject.get("type").getAsString();

                Optional<IndustryCategory> op = industryCategoryRepository.findByType(
                    industryCategory);
                if (op.isEmpty()) {
                    IndustryCategory ic = IndustryCategory.builder().type(industryCategory).build();
                    industryCategoryRepository.save(ic);
                }

                Optional<Stock> stockOp = stockRepository.findByStockId(stockId);
                if (stockOp.isEmpty()) {
                    Stock stock = Stock.builder().stockId(stockId)
                        .industryCategory(industryCategory) //
                        .stockName(stockName).type(type).build();
                    stockRepository.save(stock);
                }
            });
        });

    }
}
