package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testGoogleIp() {
        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("209.185.108.134");
        assertEquals(geoIP, "<GeoIP><Country>US</Country><State>MO</State></GeoIP>");
    }

    @Test
    public void testInvalidIp() {
        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("209.185.108.xxx");
        assertEquals(geoIP, "<GeoIP><Country>US</Country><State>MO</State></GeoIP>");
    }
}
