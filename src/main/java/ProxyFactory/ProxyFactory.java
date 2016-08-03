package ProxyFactory;

/**
 * Created by zenbox on 3/2/2016.
 */
public class ProxyFactory {
    public enum ProxyType {
        ANONYMOUS, TRANSPARENT,ELITE;
    }

    public Proxy getProxy(ProxyType proxyType) {
        if(proxyType == null)
            return null;

        switch(proxyType) {
            case ANONYMOUS:
                return new AnonymousProxy();
            case TRANSPARENT:
                return new TransparantProxy();
            case ELITE:
                return new EliteProxy();
        }
        return null;
    }
}
