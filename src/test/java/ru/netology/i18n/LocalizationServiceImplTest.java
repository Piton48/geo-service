package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {
    LocalizationService locate = new LocalizationServiceImpl();

    @Test
    void localeTest() {
        Assertions.assertEquals("Добро пожаловать", locate.locale(Country.RUSSIA));
    }

}