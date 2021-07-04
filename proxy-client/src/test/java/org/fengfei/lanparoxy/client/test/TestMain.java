package org.fengfei.lanparoxy.client.test;

import org.fengfei.lanproxy.client.ProxyClientContainer;
import org.fengfei.lanproxy.common.Location;
import org.fengfei.lanproxy.common.LocationUtils;
import org.fengfei.lanproxy.common.container.Container;
import org.fengfei.lanproxy.common.container.ContainerHelper;

import java.util.Arrays;

public class TestMain {

    public static void main(String[] args) {
        //ContainerHelper.start(Arrays.asList(new Container[] { new ProxyClientContainer() }));

        Location l = LocationUtils.getLocationAuto("117.187.194.248");
        System.out.println(l.getFullAddress());
    }
}
