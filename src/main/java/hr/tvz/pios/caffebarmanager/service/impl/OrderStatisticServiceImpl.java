package hr.tvz.pios.caffebarmanager.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import hr.tvz.pios.caffebarmanager.domain.Order;
import hr.tvz.pios.caffebarmanager.domain.OrderStatistic;
import hr.tvz.pios.caffebarmanager.repository.OrderRepository;
import hr.tvz.pios.caffebarmanager.repository.OrderStatisticsRepository;
import hr.tvz.pios.caffebarmanager.service.OrderStatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderStatisticServiceImpl implements OrderStatisticService {
    OrderStatisticsRepository orderStatisticsRepository;
    OrderRepository orderRepository;

    public ByteArrayOutputStream generateStatisticReport() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document statisticReport = new Document();
        try{
            List<OrderStatistic> orderStatistics = orderStatisticsRepository.findAll();
            PdfWriter.getInstance(statisticReport, byteArrayOutputStream);
            statisticReport.open();

            Font headlineFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.RED);
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            for (OrderStatistic orderStatistic : orderStatistics) {
                Order order = orderRepository.findById(orderStatistic.getOrder().getId()).orElseThrow();
                Chunk chunk = new Chunk("Vrijeme narudzbe: " + orderStatistic.getDateAndTime().toString(), headlineFont);
                statisticReport.add(chunk);
                statisticReport.add(new Paragraph("\n"));

                chunk = new Chunk("Stol:" + order.getTableNumber().toString(), font);
                statisticReport.add(chunk);
                statisticReport.add(new Paragraph("\n"));

                chunk = new Chunk("Ukupna cijena narudzbe: " + orderStatistic.getTotalPrice().toString(), font);
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
