package hr.tvz.pios.caffebarmanager.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import hr.tvz.pios.caffebarmanager.domain.Article;
import hr.tvz.pios.caffebarmanager.domain.Order;
import hr.tvz.pios.caffebarmanager.domain.OrderArticle;
import hr.tvz.pios.caffebarmanager.domain.OrderStatistic;
import hr.tvz.pios.caffebarmanager.dto.OrderArticleDto;
import hr.tvz.pios.caffebarmanager.service.OrderStatisticService;
import hr.tvz.pios.caffebarmanager.service.impl.OrderStatisticServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StatistcsControllerTest {
    @Mock
    private OrderStatisticService orderStatisticService;
    @InjectMocks
    private StatistcsController statistcsController;
    AutoCloseable autoCloseable;


    OrderStatistic orderStatistic;

    Order order;

    Article article;


    OrderArticle orderArticle;
    OrderArticleDto orderArticleDto;

    List<OrderStatistic> orderStatistics;

    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);

        order=new Order();
        order.setId(1L);
        order.setIssueTimeOfOrder(LocalDateTime.of(2024,1,1,1,1));
        order.setOrderStatus(1);
        order.setTableNumber(10);


        article=new Article();
        article.setId(3L);
        article.setName("Kava");
        article.setDescription("Obična normalna kava");
        article.setPrice(BigDecimal.valueOf(22));
        article.setAvailableAmount(99);

        orderArticle =new OrderArticle();

        orderArticle.setId(1L);
        orderArticle.setAmount(3);
        orderArticle.setPricePerUnit(BigDecimal.valueOf(12));
        orderArticle.setArticle(article);
        orderArticle.setOrder(order);

        order.setOrderArticles(List.of(orderArticle));

        orderArticleDto = new OrderArticleDto();
        orderArticleDto.setId(1L);
        orderArticleDto.setAmount(2);
        orderArticleDto.setPricePerUnit(BigDecimal.valueOf(22));
        orderArticleDto.setArticleId(article.getId());

        orderStatistic=new OrderStatistic();
        orderStatistic.setId(1L);
        orderStatistic.setTotalPrice(BigDecimal.valueOf(12));
        orderStatistic.setDateAndTime(LocalDateTime.of(2024,1,1,1,1));
        orderStatistic.setOrder(order);

        orderStatistics=new ArrayList<>();
        orderStatistics.add(orderStatistic);
    }

    @Test
    void generateStatistics() {
        ByteArrayOutputStream reportFromService=generateStatisticReport();
        when(orderStatisticService.generateStatisticReport()).thenReturn(reportFromService);
        ResponseEntity<byte[]> baseResponse=generateResponse(reportFromService);

        ResponseEntity<byte[]> testResponse= statistcsController.generateStatistics();

        assertEquals(testResponse,baseResponse);
    }


    private ResponseEntity<byte[]> generateResponse(ByteArrayOutputStream pdfStream){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order_statistics.pdf");
        headers.setContentLength(pdfStream.size());

        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }
    private ByteArrayOutputStream generateStatisticReport() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document statisticReport = new Document();
        try {
            PdfWriter.getInstance(statisticReport, byteArrayOutputStream);
            statisticReport.open();

            Font headlineFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.RED);
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            // Iteracija kroz sve statistike narudžbi i dodavanje informacija u PDF
            for (OrderStatistic orderStatistic : orderStatistics) {
                Chunk chunk = new Chunk("Vrijeme narudžbe: " + orderStatistic.getDateAndTime().toString(), headlineFont);
                statisticReport.add(chunk);
                statisticReport.add(new Paragraph("\n"));

                chunk = new Chunk("Stol:" + order.getTableNumber().toString(), font);
                statisticReport.add(chunk);
                statisticReport.add(new Paragraph("\n"));

                chunk = new Chunk("Ukupna cijena narudžbe: " + orderStatistic.getTotalPrice().toString(), font);
                statisticReport.add(chunk);
                statisticReport.add(new Paragraph("\n"));
            }
            statisticReport.close();

        } catch (DocumentException e) {
            System.err.println("Error occurred");
        }

        return byteArrayOutputStream;
    }
}