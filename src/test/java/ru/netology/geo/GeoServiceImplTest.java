package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    GeoService geoService = new GeoServiceImpl();
    String ip;

    @Test
    void byIpTest(){
        ip = "172.0.0.0";
        Assertions.assertEquals(Country.RUSSIA, geoService.byIp(ip).getCountry());
        ip = "96.0.0.0";
        Assertions.assertEquals(Country.USA, geoService.byIp(ip).getCountry());
    }
}