package org.fengfei.lanparoxy.client.test;

import org.fengfei.lanproxy.client.ProxyClientContainer;
import org.fengfei.lanproxy.common.container.Container;
import org.fengfei.lanproxy.common.container.ContainerHelper;

import java.util.Arrays;

public class TestMain {

    public static void main(String[] args) {
        ContainerHelper.start(Arrays.asList(new Container[] { new ProxyClientContainer() }));
    }
}
