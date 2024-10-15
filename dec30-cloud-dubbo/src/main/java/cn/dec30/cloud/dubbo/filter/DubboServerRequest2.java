package cn.dec30.cloud.dubbo.filter;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/10/15 15:48
 * @description todo
 */

import brave.Span;
import brave.dubbo.DubboRequest;
import brave.rpc.RpcServerRequest;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;

final class DubboServerRequest2 extends RpcServerRequest implements DubboRequest {
    final Invoker<?> invoker;
    final Invocation invocation;

    DubboServerRequest2(Invoker<?> invoker, Invocation invocation) {
        if (invoker == null) {
            throw new NullPointerException("invoker == null");
        } else if (invocation == null) {
            throw new NullPointerException("invocation == null");
        } else {
            this.invoker = invoker;
            this.invocation = invocation;
        }
    }

    public Invoker<?> invoker() {
        return this.invoker;
    }

    public Invocation invocation() {
        return this.invocation;
    }

    public Invocation unwrap() {
        return this.invocation;
    }

    public String method() {
        return DubboParser2.method(this.invocation);
    }

    public String service() {
        return DubboParser2.service(this.invoker);
    }

    public boolean parseRemoteIpAndPort(Span span) {
        return DubboParser2.parseRemoteIpAndPort(span);
    }

    protected String propagationField(String keyName) {
        return this.invocation.getAttachment(keyName);
    }
}
