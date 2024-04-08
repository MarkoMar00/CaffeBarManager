package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.service.OrderStatisticService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("statistic")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class StatistcsController {
    OrderStatisticService orderStatisticService;

    @GetMapping("/generatePdf")
    public ResponseEntity<byte[]> generateStatistics() {
        ByteArrayOutputStream pdfStream = orderStatisticService.generateStatisticReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order_statistics.pdf");
        headers.setContentLength(pdfStream.size());

        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }
}
