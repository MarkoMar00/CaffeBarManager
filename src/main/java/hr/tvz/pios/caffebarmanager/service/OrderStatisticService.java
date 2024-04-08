package hr.tvz.pios.caffebarmanager.service;


import java.io.ByteArrayOutputStream;

public interface OrderStatisticService {
    ByteArrayOutputStream generateStatisticReport();

}
