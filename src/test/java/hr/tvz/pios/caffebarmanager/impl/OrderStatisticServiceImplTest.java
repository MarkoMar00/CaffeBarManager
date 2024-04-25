package hr.tvz.pios.caffebarmanager.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import hr.tvz.pios.caffebarmanager.domain.Article;
import hr.tvz.pios.caffebarmanager.domain.Order;
import hr.tvz.pios.caffebarmanager.domain.OrderArticle;
import hr.tvz.pios.caffebarmanager.domain.OrderStatistic;
import hr.tvz.pios.caffebarmanager.dto.OrderArticleDto;
import hr.tvz.pios.caffebarmanager.repository.OrderRepository;
import hr.tvz.pios.caffebarmanager.repository.OrderStatisticsRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderStatisticServiceImplTest {

    @Mock
    OrderStatisticsRepository orderStatisticsRepository;

    @Mock
    OrderRepository orderRepository;

    AutoCloseable autoCloseable;

    OrderStatisticServiceImpl orderStatisticService;

    OrderStatistic orderStatistic;
    Order order;
    Article article;
    OrderArticle orderArticle;
    OrderArticleDto orderArticleDto;
    List<OrderStatistic> orderStatistics;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        orderStatisticService = new OrderStatisticServiceImpl(orderStatisticsRepository, orderRepository);

        // Set up initial data
        order = new Order();
        order.setId(1L);
        order.setIssueTimeOfOrder(LocalDateTime.of(2024, 1, 1, 1, 1));
        order.setOrderStatus(1);
        order.setTableNumber(10);

        article = new Article();
        article.setId(3L);
        article.setName("Kava");
        article.setDescription("Obična normalna kava");
        article.setPrice(BigDecimal.valueOf(22));
        article.setAvailableAmount(99);

        orderArticle = new OrderArticle();
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

        orderStatistic = new OrderStatistic();
        orderStatistic.setId(1L);
        orderStatistic.setTotalPrice(BigDecimal.valueOf(12));
        orderStatistic.setDateAndTime(LocalDateTime.of(2024, 1, 1, 1, 1));
        orderStatistic.setOrder(order);

        orderStatistics = new ArrayList<>();
        orderStatistics.add(orderStatistic);
    }

    @Test
    void generateStatisticReport() throws IOException {
        // Mock repository methods
        when(orderStatisticsRepository.findAll()).thenReturn(orderStatistics);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        // Generate PDF content in memory
        ByteArrayOutputStream outputTest = orderStatisticService.generateStatisticReport();
        ByteArrayOutputStream outputBase = generateStatisticReportBase();

        // Extract text directly from in-memory PDFs
        String testText = extractTextFromPdf(outputTest);
        String baseText = extractTextFromPdf(outputBase);

        // Assertions and debug output
        assertEquals(testText, baseText);

    }

    // Helper method to extract text from in-memory PDF
    private String extractTextFromPdf(ByteArrayOutputStream pdfStream) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfStream.toByteArray());
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper textStripper = new PDFTextStripper();
            return textStripper.getText(document);
        }
    }

    // Method to generate base statistic report
    public ByteArrayOutputStream generateStatisticReportBase() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document statisticReport = new Document();
        try {
            PdfWriter.getInstance(statisticReport, byteArrayOutputStream);
            statisticReport.open();

            Font headlineFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.RED);
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            for (OrderStatistic orderStatistic : orderStatistics) {
                Chunk chunk = new Chunk("Vrijeme narudžbe: " + orderStatistic.getDateAndTime(), headlineFont);
                statisticReport.add(chunk);
                statisticReport.add(new Paragraph("\n"));

                chunk = new Chunk("Stol:" + order.getTableNumber(), font);
                statisticReport.add(chunk);
                statisticReport.add(new Paragraph("\n"));

                chunk = new Chunk("Ukupna cijena narudžbe: " + orderStatistic.getTotalPrice(), font);
                statisticReport.add(chunk);
                statisticReport.add(new Paragraph("\n"));
            }
            statisticReport.close();
        } catch (DocumentException e) {
            System.err.println("Error occurred while creating PDF");
        }

        return byteArrayOutputStream;
    }
}
