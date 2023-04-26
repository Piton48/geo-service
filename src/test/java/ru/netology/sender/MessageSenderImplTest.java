package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {
    MessageSender messageSender;
    LocalizationService localizationService;
    GeoService geoService;
    String testString;
    Map<String, String> headers;


    @BeforeEach
    void init() {
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        geoService = Mockito.mock(GeoServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
        headers = new HashMap<>();
    }

    @Test
    void send_Ru_Test() {
        testString = "Russia";
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn(testString);
        Mockito.when(geoService.byIp(headers.get(MessageSenderImpl.IP_ADDRESS_HEADER)))
                .thenReturn(new Location(null, Country.RUSSIA, null, 0));

        Assertions.assertEquals(testString, messageSender.send(headers));
    }

    @Test
    void send_Us_Test() {
        testString = "USA";
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.12.19");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn(testString);
        Mockito.when(geoService.byIp(headers.get(MessageSenderImpl.IP_ADDRESS_HEADER)))
                .thenReturn(new Location(null, Country.USA, null, 0));

        Assertions.assertEquals(testString, messageSender.send(headers));
    }

    @Test
    void send_Not_Ru_Test() {
        testString = "Russia";
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.12.19");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("USA");
        Mockito.when(geoService.byIp(headers.get(MessageSenderImpl.IP_ADDRESS_HEADER)))
                .thenReturn(new Location(null, Country.USA, null, 0));

        Assertions.assertNotEquals(testString, messageSender.send(headers));
    }

    @Test
    void send_Not_Us_Test() {
        testString = "USA";
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Russia");
        Mockito.when(geoService.byIp(headers.get(MessageSenderImpl.IP_ADDRESS_HEADER)))
                .thenReturn(new Location(null, Country.RUSSIA, null, 0));

        Assertions.assertNotEquals(testString, messageSender.send(headers));
    }

}