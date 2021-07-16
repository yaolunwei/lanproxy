package org.fengfei.lanparoxy.client.test;

import org.fengfei.lanproxy.common.Location;
import org.fengfei.lanproxy.common.LocationUtils;

public class TestMain {

    public static void main(String[] args) {
        //ContainerHelper.start(Arrays.asList(new Container[] { new ProxyClientContainer() }));

        Location l = LocationUtils.getLocation("117.187.194.248");
        System.out.println(l.getFullAddress());
    }
}
